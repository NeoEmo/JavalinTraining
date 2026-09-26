package hexlet.code;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import java.nio.file.Path;

public class JTEApp {
    public static Javalin getApp() {
        var templateEngine = TemplateEngine.create(
                new DirectoryCodeResolver(Path.of("src/main/jte")),
                ContentType.Html
        );

        return Javalin.create(config -> {
            config.fileRenderer(new JavalinJte(templateEngine));
            config.bundledPlugins.enableDevLogging();
            config.routes.get("/", ctx -> ctx.render("index.html"));

        });
    }

    static void main(String[] args) {
        Javalin app = JTEApp.getApp();
        app.start(8080);
    }
}
