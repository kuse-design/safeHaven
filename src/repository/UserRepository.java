package repository;

import Entity.User;
import data.db.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public ResultSet createUsersTable() throws SQLException {
        String username = "root";
        String password = "Kuse";
        String url = "jdbc:mysql://localhost:3306/safe_haven_db?createDatabaseIfNotExist=true";


        String sql = """
                CREATE TABLE users (
                    id int AUTO_INCREMENT,
                    name varchar(255),
                    PRIMARY KEY (`id`)
                );""";

        try (Connection connection = DatabaseConnectionManager.connectToDatabase(username, password, url);){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            return readFromUsersTable( connection);

        }

    }

    private ResultSet readFromUsersTable( Connection connection) throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //preparedStatement.setString(1, tableName);
        return preparedStatement.executeQuery();
    }

    public User save(User user) throws SQLException {
        String username = "root";
        String password = "Kuse";
        String url = "jdbc:mysql://localhost:3306/safe_haven_db?createDatabaseIfNotExist=true";
        try(Connection connection = DatabaseConnectionManager.connectToDatabase(username, password, url)) {
            String sql = "INSERT into users (id, name) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = readFromUsersTable(connection);
            User savedUser = new User();
            while (resultSet.next()) {
                if (resultSet.isLast()){
                    savedUser.setId(resultSet.getInt("id"));
                    savedUser.setName(resultSet.getString("name"));
                }
            }
            return savedUser;
        }
    }
}