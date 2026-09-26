package hexlet.code.searchFormJTE.model;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String EMAIL = "email";

    public List<Error> SimpleValidate(String username, String email, String password) {
        List<Error> errors = new ArrayList<>();

        if (username == null || username.isBlank()) {
            String error = "Username is blank";
            errors.add(new Error(error, USERNAME));
        }
        if (email == null || email.isBlank()) {
            String error = "Email is blank";
            errors.add(new Error(error, EMAIL));
        }
        if (password == null || password.isBlank()) {
            String error = "Password is blank";
            errors.add(new Error(error, PASSWORD));
        }

        if (errors.isEmpty()) {
            if (!AdvancedUsernameValidate(username)) {
                errors.add(new Error("Username is invalid", USERNAME));
            }
            if (!AdvancedEmailValidate(email)) {
                errors.add(new Error("Email is invalid", EMAIL));
            }
            if (!AdvancedPasswordValidate(password)) {
                errors.add(new Error("Password is too weak", PASSWORD));
            }
        }
        return errors;
    }

    public boolean AdvancedUsernameValidate (String username) {
        return username.length() >= 3 && username.length() <= 20;
    }

    public boolean AdvancedEmailValidate (String email) {
        return email.contains("@") &&  email.contains(".");
    }

    public boolean AdvancedPasswordValidate (String password) {
        return password.length() >= 6;
    }
}
