package org.design.userservicejuly2025.controllers.user;

import org.design.userservicejuly2025.dtos.request.LoginRequestDTO;
import org.design.userservicejuly2025.dtos.request.LogoutRequestDTO;
import org.design.userservicejuly2025.dtos.request.SignUpRequestDTO;
import org.design.userservicejuly2025.dtos.response.TokenViewDTO;
import org.design.userservicejuly2025.dtos.response.UserViewDTO;
import org.design.userservicejuly2025.services.auth.AuthService;
import org.design.userservicejuly2025.services.users.IUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Add methods to handle user-related requests here

    @PostMapping("/signup")
    public UserViewDTO signUp(@RequestBody SignUpRequestDTO request) {
        return authService.signUp(request.getUsername(), request.getEmail(), request.getPassword());

    }

    @PostMapping("/login")
    public ResponseEntity<Void> signIn(@RequestBody LoginRequestDTO requestDTO) {
        String token = authService.login(requestDTO.getEmail(), requestDTO.getPassword());
        if (token == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        } else {
            return ResponseEntity.ok().header("Authorization", token).build();
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDTO requestDTO) {
        authService.logout(requestDTO.getToken());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/validate/{token}")
    public UserViewDTO validateToken(@PathVariable("token") String token) {

        return authService.validateToken(token);
    }


}
