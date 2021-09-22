package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion_BD {
    private static final String  DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_CONNECTION = "jdbc:sqlserver://localhost:1433;databaseName=Encomiendas";
    private static final String DB_USER = "Naika";
    private static final String DB_PASSWORD = "123123";
    private static Connection dbConnection = null;

    public static Connection getDBConnection() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dbConnection;

    }

}
