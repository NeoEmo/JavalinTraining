package hexlet.code.data.DAO;

import hexlet.code.data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection conn) {
        connection = conn;
    }

    public void save(User user) throws SQLException {
        if (user.getId() == null) {
            var sql = "INSERT INTO users(username, email) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getEmail());
                statement.executeUpdate();
                try (var generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Database did not return an id");
                    }
                }
            }
        } else {
            var sql = "UPDATE users SET username = ?, email = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getEmail());
                statement.setLong(3, user.getId());
                if (statement.executeUpdate() != 1) {
                    throw new SQLException("User not found " + user.getId());
                }
            }
        }
    }

    public Optional<User> find(Long id) throws SQLException {
        var sql = "SELECT * FROM users WHERE id = ?";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    var username = resultSet.getString("username");
                    var email = resultSet.getString("email");
                    User user = new User(username, email);
                    user.setId(id);
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }

    public boolean delete(Long id) throws SQLException {
        if (id == null ) {
            return false;
        }
        var sql = "DELETE FROM users WHERE id = ?";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        }
    }

    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        var sql = "SELECT * FROM users";
        try (var statement = connection.prepareStatement(sql)) {
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var id = resultSet.getLong("id");
                    var username = resultSet.getString("username");
                    var email = resultSet.getString("email");
                    User user = new User(username, email);
                    user.setId(id);
                    users.add(user);
                }
            }
        }
        return users;
    }
}
