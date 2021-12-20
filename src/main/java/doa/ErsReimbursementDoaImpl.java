package doa;

import models.ErsReimbursement;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ErsReimbursementDoaImpl implements ErsReimbursementDoa {

    String url;
    String username ;
    String password ;

    Logger logger = Logger.getLogger(ErsReimbursementDoaImpl.class);

    public ErsReimbursementDoaImpl(){
        this.url = "jdbc:postgresql://"+ System.getenv("AWS_RDS_ENDPOINT")+ "/project1";
        this.username = System.getenv("RDS_USERNAME");
        this.password = System.getenv("RDS_PASSWORD");
    }

    public ErsReimbursementDoaImpl(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }


    @Override
    public List<ErsReimbursement> getAllReimbursements() {

        List<ErsReimbursement> reimbursements = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url, username, password);) {

            // sql that we will be executing
            String sql = "SELECT * FROM ers_reimbursement ORDER BY reimb_id;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //execute the aql statement and return the result set
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reimbursements.add(new ErsReimbursement(rs.getInt(1),rs.getDouble(2),rs.getTimestamp(3),rs.getTimestamp(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
            }



        }catch (SQLException e){
            logger.error(e);
        }

        return reimbursements;
    }

    @Override
    public List<ErsReimbursement> getAllReumbursementsOfAStatus(Integer reimbStatusId) {

        List<ErsReimbursement> reimbursements = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url, username, password);) {

            // sql that we will be executing
            String sql = "SELECT * FROM ers_reimbursement WHERE reimb_status_id_fk = ? ORDER BY reimb_id;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,reimbStatusId);

            //execute the aql statement and return the result set
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reimbursements.add(new ErsReimbursement(rs.getInt(1),rs.getDouble(2),rs.getTimestamp(3),rs.getTimestamp(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
            }



        }catch (SQLException e){
            logger.error(e);
        }

        return reimbursements;



    }

    @Override
    public List<ErsReimbursement> getAllReimbursementsForAnEmployee(Integer reimbAuthor) {
        List<ErsReimbursement> reimbursements = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url, username, password);) {

            // sql that we will be executing
            String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author_fk = ? ORDER BY reimb_id ASC;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,reimbAuthor);

            //execute the aql statement and return the result set
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reimbursements.add(new ErsReimbursement(rs.getInt(1),rs.getDouble(2),rs.getTimestamp(3),rs.getTimestamp(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
            }



        }catch (SQLException e){
            logger.error(e);
        }

        return reimbursements;
    }

    @Override
    public void submitReimbursement(ErsReimbursement reimbursement) {

        try(Connection conn = DriverManager.getConnection(url, username, password);) {
            //generate out connection
            // Connection conn = DriverManager.getConnection(url, username, password);

            // sql that we will be executing
            String sql = "INSERT INTO ers_reimbursement VALUES (DEFAULT, ?, DEFAULT, DEFAULT, ?, NULL, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            // set value of question mark the parameter is which question mark you want to asain
            ps.setDouble(1, reimbursement.getReimbAmount());
            ps.setString(2,reimbursement.getReimbDescrition());
            ps.setInt(3,reimbursement.getReimbAuthor());
            ps.setInt(4,reimbursement.getReimbResolver());
            ps.setInt(5,reimbursement.getReimbStatusId());
            ps.setInt(6,reimbursement.getReimbType());

            //execute the update
            ps.executeUpdate();



        }catch (SQLException e){
            e.printStackTrace();
        }



    }

    @Override
    public void resolveReimbursement(Integer reimbId, Integer reimbStatusId) {

        System.out.println(reimbId + " " + reimbStatusId);

        try(Connection conn = DriverManager.getConnection(url, username, password);) {
            //generate out connection
            // Connection conn = DriverManager.getConnection(url, username, password);

            // sql that we will be executing
            String sql = "UPDATE ers_reimbursement SET reimb_status_id_fk = ? WHERE reimb_id = ?;\n" +
                    "UPDATE ers_reimbursement SET reimb_resolves = now() WHERE reimb_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reimbStatusId);
            ps.setInt(2, reimbId);
            ps.setInt(3,reimbId);

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }


}
