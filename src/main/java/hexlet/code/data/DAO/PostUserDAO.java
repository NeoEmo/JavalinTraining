package hexlet.code.data.DAO;

import hexlet.code.data.Post;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostUserDAO {
    private final Connection connection;

    public PostUserDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Post> findAllPostsUser(Long userId) throws SQLException {
        List<Post> posts = new ArrayList<>();
        var sql = "SELECT * FROM posts WHERE user_id = ?";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var id = resultSet.getLong("id");
                    var postDate = resultSet.getObject("post_date", LocalDate.class);
                    var title = resultSet.getString("title");
                    var content = resultSet.getString("content");
                    Post post = new Post(postDate, title, content);
                    post.setUserId(userId);
                    post.setId(id);
                    posts.add(post);
                }
            }
        }
        return posts;
    }
}