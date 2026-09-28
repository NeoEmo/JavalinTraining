package hexlet.code.CRUD.controller;


import hexlet.code.CRUD.dto.UserPage;
import hexlet.code.CRUD.dto.UsersPage;
import hexlet.code.CRUD.model.User;
import hexlet.code.CRUD.repository.UsersRepository;
import hexlet.code.CRUD.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.Map;

public class UsersController {
    public static void index(Context ctx) {
        var users = UsersRepository.getEntities();
        var page = new UsersPage(users);
        ctx.render("users/index.jte", Map.of("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UsersRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User with id = " + id + " not found"));
        var page = new UserPage(user);
        ctx.render("users/show.jte", Map.of("page", page));
    }

    public static void build(Context ctx) {
        ctx.render("users/build.jte");
    }

    public static void create(Context ctx) {
        var username = ctx.formParam("username");
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");

        var user = new User(username, email, password);
        UsersRepository.save(user);
        ctx.redirect(NamedRoutes.userPath());
    }

    public static void edit(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UsersRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User with id = " + id + " not found"));
        var page = new UserPage(user);
        ctx.render("users/edit.jte", Map.of("page", page));
    }

    public static void update(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var username = ctx.formParam("username");
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");

        var user = UsersRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User with id = " + id + " not found"));
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        UsersRepository.save(user);
        ctx.redirect(NamedRoutes.userPath());
    }

    public static void delete(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        UsersRepository.delete(id);
        ctx.redirect(NamedRoutes.userPath());
    }
}
