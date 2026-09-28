package hexlet.code.CRUD.util;

public class NamedRoutes {
    public static String userPath() {
        return "/users";
    }

    public static String userBuildPath() {
        return "/users/build";
    }

    public static String userIDPath() {
        return "/users/{id}";
    }

    public static String userIDPath (Long id) {
        return "/users/" + id;
    }

    public static String userIDEditPath() {
        return "/users/{id}/edit";
    }

}
