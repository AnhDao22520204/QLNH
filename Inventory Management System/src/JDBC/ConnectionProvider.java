/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JDBC;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class ConnectionProvider {
    public static Connection getCon() {
        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connection URL
            String url = "jdbc:oracle:thin:@//localhost:1521/orcl21"; // Replace 'xe' with your SID if different
            String username = "C##JAVA";
            String password = "1";

            // Establish and return the connection
            Connection con = DriverManager.getConnection(url, username, password);
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}