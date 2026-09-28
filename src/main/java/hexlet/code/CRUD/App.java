package hexlet.code.CRUD;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import hexlet.code.CRUD.controller.UsersController;
import hexlet.code.CRUD.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import java.nio.file.Path;

public class App {
    public static Javalin getApp() {
        var templateEngine = TemplateEngine.create(
                new DirectoryCodeResolver(Path.of("src/main/jte/CRUD")),
                ContentType.Html
        );

        return Javalin.create(config -> {
            config.fileRenderer(new JavalinJte(templateEngine));
            config.bundledPlugins.enableDevLogging();

            config.routes.get(NamedRoutes.userPath(), UsersController::index);
            config.routes.get(NamedRoutes.userBuildPath(), UsersController::build);
            config.routes.get(NamedRoutes.userIDPath(), UsersController::show);
            config.routes.post(NamedRoutes.userPath(), UsersController::create);
            config.routes.get(NamedRoutes.userIDEditPath(), UsersController::edit);
            config.routes.patch(NamedRoutes.userIDPath(), UsersController::update);
            config.routes.delete(NamedRoutes.userIDPath(), UsersController::delete);
        });
    }

    static void main(String[] args) {
        Javalin app = getApp();
        app.start(8080);
    }
}
