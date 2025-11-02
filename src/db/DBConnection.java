package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bloodbank"; 
    private static final String USER = "root";   // your MySQL username
    private static final String PASSWORD = "admin";   // your MySQL password (empty if none)

    private static Connection con = null;

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                // Load driver (optional for new JDBC but safe)
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database Connected Successfully!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Database Connection Failed!");
            e.printStackTrace();
        }
        return con;
    }

    public static void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("🔒 Database Connection Closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}