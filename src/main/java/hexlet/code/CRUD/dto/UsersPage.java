package hexlet.code.CRUD.dto;

import hexlet.code.CRUD.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UsersPage {
    private List<User> users;
}
