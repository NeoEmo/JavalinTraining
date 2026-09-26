package hexlet.code.data.DAO;

import hexlet.code.data.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostDAO {
    private final Connection connection;

    public PostDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Post post) throws SQLException {
        if (post.getId() == null) {
            var sql = "INSERT INTO posts(user_id, post_date, title, content) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, post.getUserId());
                statement.setObject(2, post.getPostDate());
                statement.setString(3, post.getTitle());
                statement.setString(4, post.getContent());
                statement.executeUpdate();
                try (var generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        post.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Database did not return an id.");
                    }
                }
            }
        } else {
            var sql = "UPDATE posts SET user_id = ?, post_date = ?, title = ?, content = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, post.getUserId());
                statement.setObject(2, post.getPostDate());
                statement.setString(3, post.getTitle());
                statement.setString(4, post.getContent());
                statement.setLong(5, post.getId());
                if (statement.executeUpdate() != 1) {
                    throw new SQLException("Post not found " + post.getId());
                }
            }
        }
    }

    public Optional<Post> find(Long id) throws SQLException {
        var sql = "SELECT * FROM posts WHERE id = ?";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    var userId = resultSet.getLong("user_id");
                    var postDate = resultSet.getObject("post_date", LocalDate.class);
                    var title = resultSet.getString("title");
                    var content = resultSet.getString("content");
                    var post = new Post(userId, postDate, title, content);
                    post.setId(id);
                    return Optional.of(post);
                }
            }
        }
        return Optional.empty();
    }

    public boolean delete(Long id) throws SQLException {
        if (id == null) {
            return false;
        }
        var sql = "DELETE FROM posts WHERE id = ?";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        }
    }

    public List<Post> findAll() throws SQLException {
        List<Post> posts = new ArrayList<>();
        var sql = "SELECT * FROM posts";
        try (var statement = connection.prepareStatement(sql)) {
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var id = resultSet.getLong("id");
                    var userId = resultSet.getLong("user_id");
                    var postDate = resultSet.getObject("post_date", LocalDate.class);
                    var title = resultSet.getString("title");
                    var content = resultSet.getString("content");
                    Post post = new Post(userId, postDate, title, content);
                    post.setId(id);
                    posts.add(post);
                }
            }
        }
        return posts;
    }

    public Optional<Post> findByUserAndId(Long userId, Long postId) throws SQLException {
        var sql = "SELECT * FROM posts WHERE id = ? AND user_id = ?";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, postId);
            statement.setLong(2, userId);
            try (var rs = statement.executeQuery()) {
                if (rs.next()) {
                    var postDate = rs.getObject("post_date", LocalDate.class);
                    var title = rs.getString("title");
                    var content = rs.getString("content");
                    var post = new Post(userId, postDate, title, content);
                    post.setId(postId);
                    return Optional.of(post);
                }
            }
        }
        return Optional.empty();
    }
}
