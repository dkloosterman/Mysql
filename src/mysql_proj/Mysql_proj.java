/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql_proj;

// Import required packages
import java.sql.*;

//import java.lang.*;
//import java.awt.*;
import java.util.*;
import java.text.*;

/**
 *
 * @author David Kloosterman
 */
public class Mysql_proj {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/sensodx_sql_db?useSSL=false";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "rootMysql151";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello mysql!");

        Connection conn = null;
        String sql = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            System.out.println("Processing Instrument Manufactured table...");
            sql = "SELECT * FROM Instrument_Manufactured";
            rs = stmt.executeQuery(sql);
            System.out.println("Instruments Manufactured");
            while (rs.next()) {
                String instrumentID = rs.getString("instrument_id");
                java.sql.Timestamp mfg_time = rs.getTimestamp("manufactured_timestamp");
                String location = rs.getString("manufactured_location");
                String sub1 = rs.getString("subsystem_1_id");
                String sub2 = rs.getString("subsystem_2_id");
                String sub3 = rs.getString("subsystem_3_id");
                System.out.println("\t ID: " + instrumentID
                        + "\t at: " + mfg_time
                        + "\t from: " + location
                        + "\t sub1: " + sub1
                        + "\t sub2: " + sub2
                        + "\t sub3: " + sub3);
            } // end while (rs.next())

            System.out.println("Processing Instrument Deployed table...");
            sql = "SELECT * FROM Instrument_Deployed";
            rs = stmt.executeQuery(sql);
            System.out.println("Instruments Deployd");
            while (rs.next()) {
                String instrumentID = rs.getString("instrument_id");
                java.sql.Timestamp time = rs.getTimestamp("installation_timestamp");
                String customer_id = rs.getString("customer_id");
                String customer_name = rs.getString("customer_name");
                String customer_location = rs.getString("customer_location");
                String contact_name = rs.getString("contact_name");
                String contact_telephone = rs.getString("contact_telephone");
                String contact_email = rs.getString("contact_email");
                java.sql.Date customer_since = rs.getDate("customer_since");
                String assay_types_enabled = rs.getString("assay_types_enabled");

                System.out.println("\t ID: " + instrumentID
                        + "\t installed: " + time
                        + "\n\t customer id: " + customer_id
                        + "\t customer name: " + customer_name
                        + "\t customer location: " + customer_location
                        + "\n\t contact name: " + contact_name
                        + "\t contact phone: " + contact_telephone
                        + "\t contact email: " + contact_email
                        + "\t customer since: " + customer_since
                        + "\n\t assays enabled: " + assay_types_enabled + "\n");
            } // end while (rs.next())

            System.out.println("Processing Instrument Error table...");
            sql = "SELECT * FROM Instrument_Error";
            rs = stmt.executeQuery(sql);
            System.out.println("Instrument Errors");
            while (rs.next()) {
                long error_counter = rs.getLong("error_counter");
                String instrumentID = rs.getString("instrument_id");
                String errorCode = rs.getString("instrument_error_code");
                java.sql.Timestamp time = rs.getTimestamp("instrument_error_timestamp");
                System.out.println("\t index: " + error_counter
                        + "\t ID: " + instrumentID
                        + "\t error Code: " + errorCode
                        + "\t at: " + time);
            } // end while (rs.next())

            System.out.println("Processing Service Engineer table...");
            sql = "SELECT * FROM Service_Engineer";
            rs = stmt.executeQuery(sql);
            System.out.println("Service Engineers");
            while (rs.next()) {
                String service_engineer_id = rs.getString("service_engineer_id");
                String service_engineer_location = rs.getString("service_engineer_location");
                String service_engineer_name = rs.getString("service_engineer_name");
                String service_engineer_telephone = rs.getString("service_engineer_telephone");
                String service_engineer_email = rs.getString("service_engineer_email");
                System.out.println("\t Service Engineer ID: " + service_engineer_id
                        + "\t Location: " + service_engineer_location
                        + "\n\t Name: " + service_engineer_name
                        + "\t Phone: " + service_engineer_telephone
                        + "\t email: " + service_engineer_email + "\n");
            } // end while (rs.next())

            System.out.println("Processing Service Jobs table...");
            sql = "SELECT * FROM Service_Job";
            rs = stmt.executeQuery(sql);
            System.out.println("Service Jobs");
            while (rs.next()) {
                long service_job_counter = rs.getInt("service_job_counter");
                String instrument_id = rs.getString("instrument_id");
                String service_categories = rs.getString("service_categories");
                String comment = rs.getString("comment");
                String service_engineer_id = rs.getString("service_engineer_id");
                java.sql.Timestamp service_complete_timestamp = rs.getTimestamp("service_complete_timestamp");
                System.out.println("\t Job Counter: " + service_job_counter
                        + "\t Instr. ID: " + instrument_id
                        + "\n\t Service Categories: " + service_categories
                        + "\t Comment: " + comment
                        + "\n\t Service Engineer ID: " + service_engineer_id
                        + "\t Timestamp: " + service_complete_timestamp + "\n");
            } // end while (rs.next())

            System.out.println("Processing Cartridge Manufactured table...");
            sql = "SELECT * FROM Cartridge_Manufactured";
            rs = stmt.executeQuery(sql);
            System.out.println("Cartridges Manufactured");
            while (rs.next()) {
                String cartridge_id = rs.getString("cartridge_id");
                java.sql.Timestamp manufactured_timestamp = rs.getTimestamp("manufactured_timestamp");
                String manufactured_location = rs.getString("manufactured_location");
                String assay_type = rs.getString("assay_type");
                String subsystem_1_id = rs.getString("subsystem_1_id");
                String subsystem_2_id = rs.getString("subsystem_2_id");
                String subsystem_3_id = rs.getString("subsystem_3_id");
                System.out.println("\t Cartridge ID: " + cartridge_id
                        + "\t Cartridge Assay Type: " + assay_type
                        + "\n\t Sub 1 ID: " + subsystem_1_id
                        + "\t Sub 2 ID:" + subsystem_2_id
                        + "\t Sub 3 ID:: " + subsystem_3_id
                        + "\n\t Mfg. Timestamp: " + manufactured_timestamp + "\n");
            } // end while (rs.next())

            System.out.println("Processing Patient Info table...");
            sql = "SELECT * FROM Patient_Info ";
            rs = stmt.executeQuery(sql);
            System.out.println("Patient Information");
            while (rs.next()) {
                String patient_id = rs.getString("patient_id");
                java.sql.Date patient_dob = rs.getDate("patient_dob");
                String patient_gender = rs.getString("patient_gender");
                String patient_race = rs.getString("patient_race");
                String patient_location = rs.getString("patient_location");
                System.out.println("\t Patient ID: " + patient_id
                        + "\t Patient Date of Birth: " + patient_dob
                        + "\n\t Patient Gender: " + patient_gender
                        + "\t Patient Race: " + patient_race
                        + "\t Patient Location: " + patient_location + "\n");
            } // end while (rs.next())

            System.out.println("Clinical Test Instance table...");
            sql = "SELECT * FROM Clinical_Test_Instance ";
            rs = stmt.executeQuery(sql);
            System.out.println("Clinical Tests");
            while (rs.next()) {
                long clinical_test_counter = rs.getLong("clinical_test_counter");
                String cartridge_id = rs.getString("cartridge_id");
                String instrument_id = rs.getString("instrument_id");
                String patient_id = rs.getString("patient_id");
                String technician_id = rs.getString("technician_id");
                String doctor_id = rs.getString("doctor_id");
                String raw_assay_data = rs.getString("raw_assay_data");
                String analysis_result = rs.getString("analysis_result");
                java.sql.Timestamp clinical_test_timestamp
                        = rs.getTimestamp("clinical_test_timestamp");
                System.out.println("\t clinical test counter: " + clinical_test_counter
                        + "\n\t cartrdige id: " + cartridge_id
                        + "\t instrument id: " + instrument_id
                        + "\n\t patient id: " + patient_id
                        + "\t technician id: " + technician_id
                        + "\t doctor id: " + doctor_id
                        + "\n\t raw assay data: " + raw_assay_data
                        + "\t analysis result: " + analysis_result
                        + "\t at: " + clinical_test_timestamp + "\n"
                        );
            } // end while (rs.next())

            System.out.println("Processing Patient Ground Truth table...");
            sql = "SELECT * FROM Patient_Ground_Truth";
            rs = stmt.executeQuery(sql);
            System.out.println("Patient Ground Truth");
            while (rs.next()) {
                long ground_truth_counter = rs.getInt("ground_truth_counter");
                String patient_id = rs.getString("patient_id");
                String clinical_description = rs.getString("clinical_description");
                String clinical_state = rs.getString("clinical_state");
                java.sql.Timestamp ground_truth_timestamp
                        = rs.getTimestamp("ground_truth_timestamp");
                System.out.println("\t Record Counter: " + ground_truth_counter
                        + "\t Patient ID: " + patient_id
                        + "\t Clinical Description: " + clinical_description
                        + "\n\t Clinical Code: " + clinical_state
                        + "\t Timestamp: " + ground_truth_timestamp + "\n");
            } // end while (rs.next())

            System.out.println("Join tables");
            sql = "SELECT Instrument_Manufactured.manufactured_timestamp, "
                    + "Instrument_Error.instrument_error_code "
                    + "FROM  Instrument_Manufactured JOIN Instrument_Error "
                    + "ON Instrument_Manufactured.instrument_id = "
                    + "Instrument_Error.instrument_id ";
            rs = stmt.executeQuery(sql);
            System.out.println("Join to get Mfg timestamp, Inst Error Code");
            while (rs.next()) {
                String errorCode = rs.getString("Instrument_Error.instrument_error_code");
                java.sql.Timestamp time
                        = rs.getTimestamp("Instrument_Manufactured.manufactured_timestamp");
                System.out.println("\t Instr. error Code: " + errorCode
                        + "\t Instr. mfged at: " + time);
            } // end while (rs.next())

            sql = "SELECT Patient_Info.patient_id, "
                    + "Patient_Ground_Truth.clinical_description "
                    + "FROM  Patient_Info JOIN Patient_Ground_Truth "
                    + "ON Patient_Info.patient_id = "
                    + "Patient_Ground_Truth.patient_id ";
            rs = stmt.executeQuery(sql);
            System.out.println("Join to get Patient ID, Clinical Description");
            while (rs.next()) {
                String patient_id = rs.getString("Patient_Info.patient_id");
                String clinical_description = rs.getString("Patient_Ground_Truth.clinical_description");
                System.out.println("\t Patient ID: " + patient_id
                        + "\t Clinical Description: " + clinical_description + "\n");
            } // end while (rs.next())

            sql = "SELECT Clinical_Test_Instance.patient_id, "
                    + "Cartridge_Manufactured.assay_type "
                    + "FROM  Clinical_Test_Instance JOIN Cartridge_Manufactured "
                    + "ON Clinical_Test_Instance.cartridge_id = "
                    + "Cartridge_Manufactured.cartridge_id ";
            rs = stmt.executeQuery(sql);
            System.out.println("Join to get Patient ID, Assay Test");
            while (rs.next()) {
                String patient_id = rs.getString("Clinical_Test_Instance.patient_id");
                String assay_type = rs.getString("Cartridge_Manufactured.assay_type");
                System.out.println("\t Patient ID: " + patient_id
                        + "\t Assay Test Type: " + assay_type + "\n");
            } // end while (rs.next())
        } // end try 
        catch (ClassNotFoundException e) {
            // handle the error
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (SQLException e) {
            // handle the error
            System.out.println(e.getMessage());
            System.exit(0);
        } finally {
            //finally block used to close resources

        }//end finally try

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("Local: " + timestamp);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        java.util.Date date = new java.util.Date();
        String s = sdf.format(date);
        System.out.println("GMT: " + s);

        System.out.println("Goodbye mysql!");
    } // end main
} // end public class Mysql_proj 
