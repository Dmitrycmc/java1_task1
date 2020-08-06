package server;

import java.io.Closeable;
import java.sql.*;

public class JdbcClass implements Closeable {
    /*
        CREATE TABLE user (
            id       INTEGER PRIMARY KEY AUTOINCREMENT
                             NOT NULL,
            username STRING  NOT NULL
                             UNIQUE,
            password STRING  NOT NULL
        );
     */
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatementInsert;
    private PreparedStatement preparedStatementUsernameUpdate;
    private PreparedStatement preparedStatementCheckExist;

    private void initPreparedStatements() throws SQLException {
        preparedStatementInsert = connection.prepareStatement("INSERT INTO user (username, password) VALUES (?, ?)");
        preparedStatementUsernameUpdate = connection.prepareStatement("UPDATE user SET username = ? WHERE username = ?");
        preparedStatementCheckExist = connection.prepareStatement("SELECT COUNT(*) FROM user WHERE username = ? AND password = ?");
    }

    public JdbcClass() {
        try {
            connect();
            initPreparedStatements();
        } catch (Exception e) {
            e.printStackTrace();
            close();
        }
    }

    void clearUsers() throws SQLException {
        statement.executeUpdate("DELETE FROM user");
    }

    void insertUser(String username, String password) throws SQLException {
        preparedStatementInsert.setString(1, username);
        preparedStatementInsert.setString(2, password);
        preparedStatementInsert.execute();
    }

    void updateUsername(String oldUsername, String newUsername) throws SQLException {
        System.out.println(oldUsername + " " + newUsername);
        preparedStatementUsernameUpdate.setString(1, newUsername);
        preparedStatementUsernameUpdate.setString(2, oldUsername);
        preparedStatementUsernameUpdate.execute();
    }

    boolean authUser(String username, String password) throws SQLException {
        preparedStatementCheckExist.setString(1, username);
        preparedStatementCheckExist.setString(2, password);
        ResultSet rs = preparedStatementCheckExist.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        return count == 1;
    }

    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:src\\main\\java\\ru\\gb\\chat_app\\src\\main\\resources\\main.db");
        statement = connection.createStatement();
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
