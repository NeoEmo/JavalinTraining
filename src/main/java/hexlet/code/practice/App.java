package hexlet.code.practice;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

// BEGIN (write your solution here)
import io.javalin.http.NotFoundResponse;
// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();

            // BEGIN (write your solution here)
            config.routes.get("/companies/{id}", ctx -> {
                String id = ctx.pathParam("id");
                var company = COMPANIES.stream()
                        .filter(c -> c.get("id").equals(id))
                        .findFirst()
                        .orElseThrow(NotFoundResponse::new);
                ctx.json(company);
            });
            // END

            config.routes.get("/companies", ctx -> {
                ctx.json(COMPANIES);
            });

            config.routes.get("/", ctx -> {
                ctx.result("open something like (you can change id): /companies/5");
            });
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start("0.0.0.0", 8080);

        // Хук для корректного завершения
        Runtime.getRuntime().addShutdownHook(new Thread(app::stop));
    }
}

