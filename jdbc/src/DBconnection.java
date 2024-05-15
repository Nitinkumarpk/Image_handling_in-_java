import java.sql.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBconnection {
    public static void main(String args[]) throws ClassNotFoundException {

            String url = "jdbc:mysql://localhost:3306/nk";
            String username = "root";
            String password = "Nitin@stark";
            String query = "SELECT * FROM student";
    
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("JDBC Drivers loaded successfully");
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
    
            try (Connection con = DriverManager.getConnection(url, username, password);
                 Statement stat = con.createStatement();
                 ResultSet res = stat.executeQuery(query)) {
    
                System.out.println("Connection established successfully");
    
                while (res.next()) {
    
                    int id = res.getInt("StudentID");
                    String Fname = res.getString("FirstName");
                    String Lname = res.getString("LastName");
                    String email = res.getString("Email");
                    int enroll = res.getInt("EnrollmentYear");
    
                    System.out.println("_____________________________________________");
    
                    System.out.println("ID " + id);
                    System.out.println("Name = " + Fname + " " + Lname);
                    System.out.println("Email " + email);
                    System.out.println("enroll = " + enroll);
                }
    
            } catch (SQLException e) {
                System.out.println(e.getMessage());
        }
    }

    

}
