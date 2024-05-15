
import java.io.*;
import java.sql.*;
public class Main {
    public static void main(String[] args) throws ClassNotFoundException{
        System.out.println("Hello world!");


        String url = "jdbc:mysql://localhost:3306/nk";
        String username = "root";
        String password = "Nitin@stark";
//        String image_path = "C:\\Users\\hp\\Desktop\\imp\\Photos\\IMG20220415155257.jpg"
        String folderpath = "C:\\Users\\hp\\Desktop\\imp\\Photos\\";
        String query = "SELECT image_data from image_table where image_id = (?)";
//        String query = "INSERT INTO employees(id, name, job_title, salary) VALUES (3, 'aru', 'java developer', 45000.0);";
//
//        String query = "DELETE from employees where id = 4";

//        String query = "update employees set salary = 10000.0 where id = 3";
            try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Drivers loaded Sucessfully");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url, username, password);

            System.out.println("connection established sucessfullly");

//            FileInputStream fileInputStream = new FileInputStream(image_path);
//            byte[] imageData = new byte[fileInputStream.available()];
//            fileInputStream.read(imageData);
//
////            Statement stat = con.createStatement();
//            PreparedStatement preparedStatement = con.prepareStatement(query);
//            preparedStatement.setBytes(1, imageData);
//
//            int rowsAf = preparedStatement.executeUpdate();
//
//            if(rowsAf > 0) {
//                System.out.println("Insert sucessful"+ rowsAf);
//            }else{
//                System.out.println("Failed");
//            }
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                byte[] imageData = resultSet.getBytes("image_data");
                String image_path = folderpath+ "extractedimage.jpg";
                OutputStream outputStream = new FileOutputStream(image_path);
                outputStream.write(imageData);
                System.out.println(resultSet.getString("image_data"));
            }else{
                System.out.println("No data found");
            }

/*
            if(rowsAf > 0) {
                System.out.println("Delection sucessful "+ rowsAf+ "Rows affected");
            }else{
                System.out.println("Delection failed");
            }
            if(rowsAf > 0) {
                System.out.println("Updataion sucessful "+ rowsAf+ "Rows affected");
            }else{
                System.out.println("updataion failed");
            }

            res.close();
            stat.close();
*/


            preparedStatement.close();
            con.close();

        }catch (SQLException | FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}