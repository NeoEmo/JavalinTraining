package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lesson {
    private Long id;
    private String name;
    private String description;

    public Lesson(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
