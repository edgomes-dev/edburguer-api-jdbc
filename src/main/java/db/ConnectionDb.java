package db;

import java.sql.*;
import org.postgresql.Driver;

public class ConnectionDb {
    private static String url = "jdbc:postgresql://localhost:5432/edburguer";

    private static Connection conn = null;

    static {
        connect();
    }

    public ConnectionDb() throws SQLException {
        connect();
    }

    private static void connect() {
        try {
            if (conn == null) {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(url, "ed", "senha123");
                conn.setAutoCommit(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}
