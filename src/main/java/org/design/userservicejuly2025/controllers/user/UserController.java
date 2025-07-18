package org.design.userservicejuly2025.controllers.user;

import org.design.userservicejuly2025.dtos.request.LoginRequestDTO;
import org.design.userservicejuly2025.dtos.request.LogoutRequestDTO;
import org.design.userservicejuly2025.dtos.request.SignUpRequestDTO;
import org.design.userservicejuly2025.dtos.response.TokenViewDTO;
import org.design.userservicejuly2025.dtos.response.UserViewDTO;
import org.design.userservicejuly2025.services.users.IUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {


    private final IUserService userService;

    public UserController(@Qualifier("fakeUserServiceImpl") IUserService userService) {
        this.userService = userService;
    }

    // Add methods to handle user-related requests here

    @PostMapping("/signup")
    public UserViewDTO signUp (@RequestBody SignUpRequestDTO request) {
      return userService.signUp(request.getUsername(),request.getEmail(),request.getPassword());

    }
    @PostMapping("/login")
    public TokenViewDTO signIn(@RequestBody LoginRequestDTO requestDTO) {
        return userService.signIn(requestDTO.getEmail(), requestDTO.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDTO requestDTO) {
        userService.logout(requestDTO.getToken());
        return ResponseEntity.ok().build();
    }
    @GetMapping("/validate/{token}")
    public UserViewDTO validateToken(@PathVariable("token") String token) {
       return userService.validateToken(token);
    }

}
