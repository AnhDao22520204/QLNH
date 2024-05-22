///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package JDBC;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
///**
// *
// * @author DELL
// */
//public class ConnectionJDBC {
//    private static ConnectionJDBC instance;
//    private Connection connection;
//
//    public static ConnectionJDBC getInstance() {
//        if (instance == null) {
//            instance = new ConnectionJDBC();
//        }
//        return instance;
//    }
//    
//    public static Connection connect() {
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Không thể tìm thấy driver JDBC của Oracle!");
//            e.printStackTrace();
//            return null;
//        }
//        // Thông tin kết nối
//        //create user c##HQT identified by hqt;
//        //grant connect, resource, dba to c##HQT;
//        String url = "jdbc:oracle:thin:@localhost:1521:orcl21";
//        String username = "C##JAVA";
//        String password = "1";
//        // Thử kết nối và trả về đối tượng Connection
//        //
//        try {
//            Connection connection = DriverManager.getConnection(url, username, password);
//            System.out.println("Kiem tra kết noi!");
//            // Kiểm tra kết nối
//            if (connection != null) {
//                System.out.println("Kết nối thành công!");
//            } else {
//                System.out.println("Kết nối không thành công!");
//            }
//            return connection;
//
//        } catch (SQLException e) {
//            System.out.println("Kết nối thất bại!");
//            e.printStackTrace();
//            return null;
//        }
//    }
//    
//    public static void closeconect(Connection connection)
//    {
//            try {
//                // Đóng kết nối sau khi sử dụng xong
//                if (connection != null) {
//                    connection.close();
//                    System.out.println("Đã đóng kết nối đến cơ sở dữ liệu Oracle!");
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//    }
//
//    public Connection getConnection() {
//        return connection;
//    }
//}



package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    private static ConnectionJDBC instance;
    private Connection connection;

    private ConnectionJDBC() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@//localhost:1521/orcl21"; // Use service name if applicable
            String username = "C##JAVA";
            String password = "1";
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Kết nối thành công!");
        } catch (ClassNotFoundException e) {
            System.err.println("Không thể tìm thấy driver JDBC của Oracle: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Kết nối thất bại: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static ConnectionJDBC getInstance() {
        if (instance == null) {
            synchronized (ConnectionJDBC.class) {
                if (instance == null) {
                    instance = new ConnectionJDBC();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        if (instance != null && instance.connection != null) {
            try {
                instance.connection.close();
                System.out.println("Đã đóng kết nối đến cơ sở dữ liệu Oracle!");
            } catch (SQLException e) {
                System.err.println("Đóng kết nối thất bại: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
