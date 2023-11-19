package school.hei;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
    private DB_Connection() {}
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        // Récupérer les informations d'environnement
        String jdbcUrl = System.getenv("JDBC_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        connection = DriverManager.getConnection(jdbcUrl, user, password);
        return connection;
    }
}
