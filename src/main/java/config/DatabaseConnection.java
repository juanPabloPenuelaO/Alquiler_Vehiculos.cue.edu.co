package config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String url = "jdbc:mysql://localhost:3306/alquiler_vehiculos";
    private static String user = "root";
    private static String password = "admin";
    private static Connection connection;
    public static Connection getInstance() throws SQLException {
        if(connection==null){
            connection = DriverManager.getConnection(url,user,password);
        }
        return connection;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }

}