package model;
import java.sql.*;

/**
 * This Class is used to link the connection
 * between the program code and the database system.
 * @author Mohamed Essam
 */
public class DataBaseSystem {
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:./Toffee-Project-DataBase/ToffeeDataBase.db";
    private static Connection connection;

    /**
     * It Checks if the database file is accessable or not.<br>
     * Then link a connection between the program and the database system.
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
