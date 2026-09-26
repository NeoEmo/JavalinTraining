package hexlet.code;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import hexlet.code.dto.CoursesPage;
import hexlet.code.model.Course;
import hexlet.code.model.Lesson;
import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class CourseJTE {
    public static void main(String[] args) {
        var templateEngine = TemplateEngine.create(
                new DirectoryCodeResolver(Path.of("src/main/jte")),
                ContentType.Html
        );

        var courses =
                List.of(
                        new Course(1L, "Java", "Основы Java"),
                        new Course(2L, "PHP", "Основы PHP"));

        var lessonsByCourse = Map.of(
                1L, List.of(
                        new Lesson(1L, "Введение", "Что такое Java"),
                        new Lesson(2L, "Привет, мир", "Пишем первую программу"),
                        new Lesson(3L, "Комментарии", "И их виды"),
                        new Lesson(4L, "Инструкции", "Порядок имеет значение"),
                        new Lesson(5L, "Проверка решений", "понятия expected/actual")),
                2L, List.of(
                        new Lesson(1L, "Введение", "Что такое PHP"),
                        new Lesson(2L, "Привет, мир", "Пишем первую программу"),
                        new Lesson(3L, "Комментарии", "И их виды"),
                        new Lesson(4L, "Инструкции", "Порядок имеет значение"),
                        new Lesson(5L, "Проверка решений", "понятия expected/actual")));

        var app =
                Javalin.create(
                        config -> {
                            config.bundledPlugins.enableDevLogging();
                            config.fileRenderer(new JavalinJte(templateEngine));
                            config.routes.get("/", ctx -> ctx.redirect("/courses"));

                            config.routes.get(
                                    "/courses",
                                    ctx -> {
                                        var header = "Курсы по программированию";
                                        var page = new CoursesPage(courses, header);
                                        ctx.contentType("text/html; charset=utf-8");
                                        ctx.render("courses/index.jte", Map.of("page", page));
                                    });
                            config.routes.get("/courses/{id}", ctx -> {
                                Long id = Long.valueOf(ctx.pathParam("id"));
                                var course = courses.stream()
                                        .filter(c -> c.getId().equals(id))
                                        .findFirst()
                                        .orElseThrow(NotFoundResponse::new);
                                var lessons = lessonsByCourse.getOrDefault(id, List.of());
                                ctx.contentType("text/html; charset=utf-8");
                                ctx.render("courses/show.jte", Map.of(
                                        "course", course,
                                        "lessons", lessons));
                            });
                        });

        app.start(7070);
    }
}
