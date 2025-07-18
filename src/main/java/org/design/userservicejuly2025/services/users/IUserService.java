package org.design.userservicejuly2025.services.users;

import org.design.userservicejuly2025.dtos.response.TokenViewDTO;
import org.design.userservicejuly2025.dtos.response.UserViewDTO;

public interface IUserService {

    public UserViewDTO signUp(String userName,String email, String password);

    public TokenViewDTO signIn(String email, String password);

    public UserViewDTO validateToken(String token);

    public UserViewDTO getUserById(String email);

    public void logout(String token);

}
