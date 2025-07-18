package org.design.userservicejuly2025.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequestDTO {

    private String username;
    private String password;
    private String email;
}
