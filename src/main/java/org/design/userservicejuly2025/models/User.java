package org.design.userservicejuly2025.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity(name ="users")
public class User extends BaseModel {

    private String name;
    private String email;
    private String password;

    @ManyToMany
    private List<Role> roles;

    public static User from(String name,String email,String password) {
        User user =new User();
        user.email=email;
        user.password=password;
        user.name=name;
        return user;
    }
}
