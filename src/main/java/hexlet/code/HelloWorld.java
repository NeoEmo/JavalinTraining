package hexlet.code;

import io.javalin.Javalin;

public class HelloWorld {
    static void main(String[] args) {
        var app = Javalin.create(
                config -> {
                    config.bundledPlugins.enableDevLogging();
                    config.routes.get("/", ctx -> ctx.result("Hello World!"));
                });
        app.start(7070);
    }
}
