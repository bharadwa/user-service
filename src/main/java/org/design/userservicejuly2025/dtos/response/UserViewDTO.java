package org.design.userservicejuly2025.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.design.userservicejuly2025.models.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserViewDTO {

    private String email;

    private String username;

    private List<String> roles;
    public static UserViewDTO from(User user) {
        if(user==null) {
            return null;
        }
        UserViewDTO userViewDTO = new UserViewDTO();
        userViewDTO.setEmail(user.getEmail());
        userViewDTO.setUsername(user.getName());
        if(user.getRoles()!=null) {
            userViewDTO.setRoles(new ArrayList<>());
            user.getRoles().stream().forEach(role -> {
                        userViewDTO.getRoles().add(role.getValue());
                    }
            );
        }
        return userViewDTO;
    }

}
