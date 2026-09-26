package hexlet.code;

import hexlet.code.data.DAO.PostDAO;
import hexlet.code.data.DAO.PostUserDAO;
import hexlet.code.data.DAO.UserDAO;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson3;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.json.JsonMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class App {
    private static final String URL  = "jdbc:postgresql://localhost:5432/hexlet_db";
    private static final String USER = "lunev";
    private static final String PASS = System.getenv("DB_PASSWORD");

    private static Connection openConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }


    public static Javalin getApp() {
        // BEGIN (write your solution here)
        var mapper = JsonMapper.builder().disable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS).build();

        return Javalin.create(config -> {
                    config.jsonMapper(new JavalinJackson3(mapper, false));
                    config.bundledPlugins.enableDevLogging();
                    config.routes.get("/", ctx -> ctx.redirect("/welcome"));
                    config.routes.get("/welcome", ctx -> ctx.result("Welcome to Hexlet"));
                    config.routes.get("/hello", ctx -> {
                       var name = ctx.queryParam("name");
                       if (name == null) {
                           ctx.result("Hello World");
                       } else  {
                           ctx.result("Hello " + name);
                       }
                    });
                    // Убер самостоятельная работа хекслета + своя БД и подключение
                    config.routes.get("/users", ctx -> {
                        try (var connection = openConnection()) {
                            var dao = new UserDAO(connection);
                            ctx.json(dao.findAll());
                        }
                    });

                    config.routes.get("/users/posts", ctx -> {
                        try (var connection = openConnection()) {
                            var dao = new PostDAO(connection);
                            ctx.json(dao.findAll());
                        }
                    });

                    config.routes.get("/users/{id}",  ctx -> {
                        long id = Long.parseLong(ctx.pathParam("id"));
                        try (var connection = openConnection()) {
                            var dao = new UserDAO(connection);
                            var user = dao.find(id);
                            if (user.isEmpty()) {
                                ctx.status(404).result("User not found");
                            } else {
                                ctx.json(user.get());
                            }
                        }
                    });

                    config.routes.get("/users/{id}/post",  ctx -> {
                        long id = Long.parseLong(ctx.pathParam("id"));
                        try (var connection = openConnection()) {
                            var dao = new PostUserDAO(connection);
                            ctx.json(dao.findAllPostsUser(id));
                        }
                    });

                    config.routes.get("/users/{id}/post/{post_id}",  ctx -> {
                        long post_id = Long.parseLong(ctx.pathParam("post_id"));
                        long user_id = Long.parseLong(ctx.pathParam("id"));
                        try (var connection = openConnection()) {
                            var dao = new PostDAO(connection);
                            var post = dao.findByUserAndId(user_id, post_id);
                            if (post.isEmpty()) {
                                ctx.status(404).result("Post not found");
                            } else  {
                                ctx.json(post.get());
                            }
                        }
                    });


                });
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(8080);
    }
}
