package hexlet.code.CRUD.model;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
public class User {
    private  Long id;
    private String username;
    private String password;
    private String email;

    public User(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
