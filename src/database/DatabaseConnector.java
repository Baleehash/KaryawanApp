package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
    public static Connection connectToDatabase(Properties config) throws SQLException {
        // Ambil URL, USER, dan PASSWORD dari config.properties
        String url = config.getProperty("DB_URL");
        String user = config.getProperty("DB_USER");
        String password = config.getProperty("DB_PASSWORD");

        // Pastikan driver MySQL sudah terdaftar di classpath
        return DriverManager.getConnection(url, user, password);
    }
}
