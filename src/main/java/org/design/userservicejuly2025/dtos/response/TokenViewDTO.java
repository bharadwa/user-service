package org.design.userservicejuly2025.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.design.userservicejuly2025.models.Token;

import java.util.Date;

@Getter
@Setter
public class TokenViewDTO {

    private String value;
    private Date expirationDate;
    private boolean active;
    private UserViewDTO user;

    public static TokenViewDTO from(Token token){
        TokenViewDTO tokenViewDTO = new TokenViewDTO();
        tokenViewDTO.setValue(token.getValue());
        tokenViewDTO.setExpirationDate(token.getExpirationDate());
        UserViewDTO userViewDTO = UserViewDTO.from(token.getUser());
        tokenViewDTO.setUser(userViewDTO);
        tokenViewDTO.setActive(token.getDeleted());
        return tokenViewDTO;
    }

}
