package be.jidoka.employee.domain.role;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Role {

    @Id
    private String name;

    private Role() { }

    private Role(String name) {
        this.name = name;
    }

    public static Role of(String name) {
        return new Role(name);
    }

    public String getName() {
        return name;
    }

}
