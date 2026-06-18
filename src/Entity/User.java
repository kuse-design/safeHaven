package Entity;

import data.db.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    private String Id;
    private String Name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public User save(User user) {
        Connection connection = DatabaseConnectionManager.connectToDatabase(username, password, url);
        String sql = "INSERT into users (id, name) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        PreparedStatement.setInt(1, user.getId());
        PreparedStatement.setString(2, user.getName());
        PreparedStatement.executeUpdate();
        ResultSet resultSet = readFromUserTable(connection);
        while (resultSet. next()){
            if(resultSet.isLast()){
                savedUser.setId(resultSet.getInt("id"));
                savedUser.setName(resultSet)
            }
        return null;

        }
    }
}
