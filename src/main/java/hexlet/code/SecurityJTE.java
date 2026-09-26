package hexlet.code;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
import java.util.Map;

@Setter
@Getter
public class SecurityJTE {
    String userId;

    static Javalin getApp() {
        var templateEngine = TemplateEngine.create(
          new DirectoryCodeResolver(Path.of("src/main/jte/securityJTE")),
          ContentType.Html
        );

        return Javalin.create( config -> {
            config.fileRenderer(new JavalinJte(templateEngine));
            config.bundledPlugins.enableDevLogging();
            // не защищённый
            config.routes.get("/users/{id}", ctx -> {
                var id = ctx.pathParam("id");
                ctx.contentType("html");
                ctx.result("<h1>" + id + "</h1>");
            });
            config.routes.get("/newUsers/{id}", ctx -> {
                var id = ctx.pathParam("id");
                var escapedId = id.replace("&", "&amp;")
                        .replace("<", "&lt;")
                        .replace(">", "&gt;");
                ctx.contentType("text/html");
                ctx.result("<h1>" + escapedId + "</h1>");
            });

            config.routes.get("/usersJTE/{id}", ctx -> {
                var id = ctx.pathParam("id");
                SecurityJTE securityJTE = new SecurityJTE();
                securityJTE.setUserId(id);
                ctx.render("index.jte", Map.of("security", securityJTE));
            });
        });
    }

    static void main(String[] args) {
        Javalin app = getApp();
        app.start(8080);
    }
}
