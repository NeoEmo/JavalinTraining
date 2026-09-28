package hexlet.code.CRUD.dto;

import hexlet.code.CRUD.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserPage {
    private User user;
}
