package hexlet.code;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import hexlet.code.searchFormJTE.model.User;
import hexlet.code.searchFormJTE.model.UsersList;
import hexlet.code.searchFormJTE.model.Validator;
import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinJte;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SearchFormJTE {
    /**
     * <p>
     *     Самостоятельная работа к теории "поисковые формы", так как достаточно уже усложнена, некоторые функции
     *     не сделаны
     * </p>
     * <p>
     *  Что сделано:
     * </p>
     * <ol>
     *     <li>
     *         <p>
     *             Классы Error (Ошибки в процессе регистрации) Validation (Валидация полей регистрации)
     *             User (класс пользователя) UserList (класс-коллекция пользователей, чтобы не подключать СУБД)
     *         </p>
     *     </li>
     *     <li>
     *         <p>
     *              JTE файлы Form (форма регистрации), login (форма авторизации), profile (профиль пользователя)
     *         </p>
     *     </li>
     *     <li>
     *         <p>
     *              А так же form.css для всех JTE классов, найдена стоковая фотка для всех профилей и сам
     *              Javalin-класс SearchFormJTE
     *         </p>
     *     </li>
     * </ol>
     *
     * <p>
     *  Ввиду того, что уже достаточно много сделано, не сделаны некоторые функции:
     * </p>
     *
     * <ol>
     *     <li>
     *         <p>
     *             Не сделана переадресация с логина на регистрацию и наоборот
     *         </p>
     *     </li>
     *     <li>
     *         <p>
     *             Не сделан index.jte, где показывались бы профили "всех" пользователей, и на которые можно пройти
     *         </p>
     *     </li>
     *     <li>
     *         <p>
     *             Нет кнопки выхода из сессии
     *         </p>
     *     </li>
     * </ol>
     *
     * <p>
     *     Но если не брать во внимание все эти косяки, то данная работа вполне себе подходит под самостоятельную урока
     * </p>
     * **/
    private static final UsersList USERS = new UsersList();

    static Javalin getApp() {
        var templateEngine = TemplateEngine.create(
          new DirectoryCodeResolver(Path.of("src/main/jte/SearchFormJTE")),
          ContentType.Html
        );

        return Javalin.create(config -> {
            config.fileRenderer(new JavalinJte(templateEngine));
            config.bundledPlugins.enableDevLogging();
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/styles";
                staticFileConfig.directory = "SearchFormJTE/styles";
                staticFileConfig.location = Location.CLASSPATH;
            });
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/assets";
                staticFileConfig.directory = "SearchFormJTE/assets";
                staticFileConfig.location = Location.CLASSPATH;
            });

            config.routes.get("/", ctx -> {
                ctx.redirect("/registration");
            });
            config.routes.get("/registration", ctx -> {
                ctx.render("form.jte", Map.of(
                        "errors", List.of(),
                        "username", "",
                        "email", ""
                ));
            });
            config.routes.post("/registration", ctx -> {
                var username = Objects.requireNonNull(ctx.formParam("username")).trim();
                var password = Objects.requireNonNull(ctx.formParam("password"));
                var email = Objects.requireNonNull(ctx.formParam("email")).trim().toLowerCase();
                Validator validator = new Validator();
                var errors = validator.SimpleValidate(username, email, password);
                if (!errors.isEmpty()) {
                    ctx.render("form.jte", Map.of(
                            "errors", errors,
                            "username", username,
                            "email", email
                    ));
                    return;
                }
                var user = new User(username, password, email);
                USERS.addUser(user);

                ctx.redirect("/login");
            });
            config.routes.get("/login", ctx -> {
               ctx.render("login.jte", Map.of(
                       "error", ""
               ));
            });
            config.routes.post("/login", ctx -> {
                var username = Objects.requireNonNull(ctx.formParam("username")).trim();
                var password = Objects.requireNonNull(ctx.formParam("password")).trim();

                var verify = USERS.findUser(username, password);
                if (!verify) {
                    String error = "Invalid username or password";
                    ctx.render("login.jte",  Map.of(
                            "error", error,
                            "username", username
                    ));
                    return;
                }

                ctx.sessionAttribute("username", username);
                ctx.redirect("/profile");
            });
            config.routes.get("/profile", ctx -> {
                String username = ctx.sessionAttribute("username");
                if (username == null) {
                    ctx.redirect("/login");
                    return;
                }
                var user = USERS.getUser(username).orElseThrow(() -> new NotFoundResponse("User not found"));

                ctx.render("profile.jte", Map.of(
                        "username", username,
                        "email", user.getEmail()
                ));
            });
        });
    }

    static void main(String[] args) {
        Javalin app = getApp();
        app.start(8080);
    }
}
