import java.sql.*;

public class MainJdbcClass {
    /*
        CREATE TABLE user (
            id       INTEGER PRIMARY KEY AUTOINCREMENT
                             NOT NULL,
            username STRING  NOT NULL
                             UNIQUE,
            password STRING  NOT NULL
        );
     */
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatementInsert;

    public static void main(String[] args) {
        try {
            connect();
            initPreparedStatements();

            clearUsers();
            insertUser("Dima", "777");
            insertUser("Sasha", "989");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void initPreparedStatements() throws SQLException {
        preparedStatementInsert = connection.prepareStatement("INSERT INTO user (username, password) VALUES (?, ?)");
    }

    private static void clearUsers() throws SQLException {
        statement.executeUpdate("DELETE FROM user");
    }

    private static void insertUser(String username, String password) throws SQLException {
        preparedStatementInsert.setString(1, username);
        preparedStatementInsert.setString(2, password);
        preparedStatementInsert.execute();
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:src\\main\\java\\ru\\gb\\java3\\lesson2\\src\\main\\resources\\main.db");
        statement = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
