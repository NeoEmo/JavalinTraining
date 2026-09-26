package hexlet.code;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinJte;

import java.nio.file.Path;

public class projectTeamJTE {
    static Javalin getApp() {
        var templateEngine = TemplateEngine.create(
                new DirectoryCodeResolver(Path.of("src/main/jte/projectTeam")),
                ContentType.Html
        );

        return Javalin.create(config -> {
            config.fileRenderer(new JavalinJte(templateEngine));
            config.bundledPlugins.enableDevLogging();
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/styles";
                staticFileConfig.directory = "/styles";
                staticFileConfig.location = Location.CLASSPATH;
            });


            config.routes.get("/", ctx -> ctx.render("index.jte"));
            config.routes.get("/project", ctx -> ctx.redirect("/"));
            config.routes.get("/team", ctx -> ctx.render("teamProjectTeam.jte"));
            config.routes.get("/reviews", ctx -> ctx.render("reviews.jte"));
            config.routes.get("/aboutUs", ctx -> ctx.render("aboutUs.jte"));
        });
    }

    static void main(String[] args) {
        Javalin app = getApp();
        app.start(8080);
    }
}
