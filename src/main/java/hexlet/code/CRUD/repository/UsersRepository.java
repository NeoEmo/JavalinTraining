package hexlet.code.CRUD.repository;

import hexlet.code.CRUD.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepository {
    private static List<User> users = new ArrayList<>();

    public static void save(User user) {
        user.setId((long) users.size() + 1);
        users.add(user);
    }

    public static List<User> search(String term) {
        return users.stream()
                .filter(user -> user.getUsername().startsWith(term))
                .toList();
    }

    public static Optional<User> find(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findAny();
    }

    public static void delete(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    public static List<User> getEntities() {
        return users;
    }
}
