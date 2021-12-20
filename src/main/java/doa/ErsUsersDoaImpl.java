package doa;

import models.ErsReimbursement;
import models.ErsUsers;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ErsUsersDoaImpl implements ErsUsersDoa{

    String url;
    String username ;
    String password ;

    Logger logger = Logger.getLogger(ErsReimbursementDoaImpl.class);

    public ErsUsersDoaImpl(){
        this.url = "jdbc:postgresql://"+ System.getenv("AWS_RDS_ENDPOINT")+ "/project1";
        this.username = System.getenv("RDS_USERNAME");
        this.password = System.getenv("RDS_PASSWORD");
    }

    public ErsUsersDoaImpl(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }


    @Override
    public ErsUsers getAUser(String loggedInUsername, String loggedInPassword) {
        ErsUsers user = new ErsUsers();

        try(Connection conn = DriverManager.getConnection(url, username, password);) {

            // sql that we will be executing
            String sql = "SELECT * FROM ers_users WHERE ers_username = ? AND ers_password = ? ;";
            PreparedStatement ps = conn.prepareStatement(sql);

            /*ps.setString(1, usernameInDb);
            ps.setString(2, passwordInDb);*/

            ps.setString(1, loggedInUsername);
            ps.setString(2, loggedInPassword);



            //execute the aql statement and return the result set
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user = new ErsUsers(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5),rs.getString(6), rs.getInt(7));
            }



        }catch (SQLException e){
            logger.error(e);
        }

        return user;
    }

    @Override
    public void isUserValid(ErsUsers user) {

    }
}
