package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profile extends AbstractEntity {
    private String name;
    private String surname;
    private String email;
    private String gender;
    private int age;

    public Profile() {
        super();
    }
    public Profile(String name, String surname, String email, String gender) {
        super();
        setName(name);
        setSurname(surname);
        setEmail(email);
        setGender(gender);
    }
}
