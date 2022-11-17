package DataBase;

import java.sql.*;

public class DataBase {
    private static DataBase dbConnection;
    private Connection connection;

    private DataBase() {

        String userName = "root";
        String password = "1022";
        String connectionURL = "jdbc:mysql://localhost:3306/ammunitionstore";

        try {
            connection = DriverManager.getConnection(connectionURL,userName,password);

        } catch (SQLException e) {
            System.out.println("Can't connect to database due to restricted access or null url.");
            throw new RuntimeException(e);
        }

    }

    public static DataBase getInstance() {
        if (dbConnection == null) {
            dbConnection = new DataBase();
        }
        return dbConnection;
    }


    @Override
    public String toString() {
        return "DBConnection{connection=" + connection + '}';
    }

    public void close() throws SQLException {
        connection.close();
        dbConnection = null;
    }


    public Statement createStatement() {
        try {
            return connection.createStatement();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public PreparedStatement prepareStatement(String query) {
        try {

            return connection.prepareStatement(query);
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
