package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2Util {
    public static String url = "jdbc:h2:./h2/db";
    public static String username = "sa";
    public static String password = "sa";

    public static void createTables(){
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "CREATE TABLE ers_reimbursement_status(\n" +
                    "reimb_status_id int PRIMARY KEY,\n" +
                    "reimb_status varchar(10) DEFAULT 'Pending'\n" +
                    ");\n" +
                    "INSERT INTO ers_reimbursement_status VALUES (1, 'Pending');\n" +
                    "INSERT INTO ers_reimbursement_status VALUES (2, 'Approved');\n" +
                    "INSERT INTO ers_reimbursement_status VALUES (3, 'Denied');\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE ers_reimbursement_type(\n" +
                    "reimb_type_id int PRIMARY KEY,\n" +
                    "reimb_type varchar(10) NOT NULL\n" +
                    ");\n" +
                    "\n" +
                    "INSERT INTO ers_reimbursement_type VALUES (1, 'LODGING');\n" +
                    "INSERT INTO ers_reimbursement_type VALUES (2, 'TRAVEL');\n" +
                    "INSERT INTO ers_reimbursement_type VALUES (3, 'FOOD');\n" +
                    "INSERT INTO ers_reimbursement_type VALUES (4, 'OTHER');\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE ers_user_role(\n" +
                    "ers_user_role_id int PRIMARY KEY,\n" +
                    "ers_user_role varchar(10) DEFAULT 'Employee'\n" +
                    ");\n" +
                    "\n" +
                    "INSERT INTO ers_user_role VALUES (1, 'Employee');\n" +
                    "INSERT INTO ers_user_role VALUES (2, 'Manager');\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE ers_users(\n" +
                    "ers_user_id serial PRIMARY KEY,\n" +
                    "ers_username varchar(50)  NOT NULL UNIQUE,\n" +
                    "ers_password varchar(50) NOT NULL,\n" +
                    "user_first_name varchar(100) NOT NULL,\n" +
                    "user_last_name varchar(100) NOT NULL,\n" +
                    "user_email varchar(150) UNIQUE NOT NULL,\n" +
                    "user_role_id_fk int NOT NULL REFERENCES ers_user_role(ers_user_role_id)\n" +
                    ");\n" +
                    "\n" +
                    "INSERT INTO ers_users VALUES (DEFAULT, 'Bob', 'P4ssw0rd', 'Bob', 'Roberts', 'SecretFormula@email.com', 1);\n" +
                    "INSERT INTO ers_users VALUES (DEFAULT, 'MrKrabbs', 'P4ssw0rd', 'Eugeen', 'Krabbs', 'SsecretFormula@email.com', 2);\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE ers_reimbursement(\n" +
                    " \treimb_id serial PRIMARY KEY,\n" +
                    " \treimb_amount double PRECISION NOT NULL,\n" +
                    " \treimb_submitted timestamp,\n" +
                    " \treimb_resolves timestamp,\n" +
                    " \treimb_description varchar(250),\n" +
                    " \treimb_reciept bytea,\n" +
                    " \treimb_author_fk int NOT NULL REFERENCES ers_users(ers_user_id),\n" +
                    " \treimb_resolver_fk int NOT NULL REFERENCES ers_users(ers_user_id),\n" +
                    " \treimb_status_id_fk int NOT NULL REFERENCES ers_reimbursement_status(reimb_status_id),\n" +
                    " \treimb_type_id_fk int NOT NULL REFERENCES ers_reimbursement_type(reimb_type_id)\n" +
                    " \t);";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void dropTables(){
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            //DROP TABLE ers_reimbursement;\n" +
            //                    "\n" +

            String sql = "DROP TABLE ers_reimbursement ;\n" +
                    "\n" +
                    "DROP TABLE ers_reimbursement_status;\n" +
                    "\n" +
                    "DROP TABLE ers_reimbursement_type ;\n" +
                    "\n" +
                    "DROP TABLE ers_users ;\n" +
                    "\n" +
                    "DROP TABLE ers_user_role ;";



            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
