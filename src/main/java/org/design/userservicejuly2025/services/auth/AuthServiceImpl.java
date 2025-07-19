package org.design.userservicejuly2025.services.auth;

import org.design.userservicejuly2025.dtos.response.UserViewDTO;
import org.design.userservicejuly2025.models.Session;
import org.design.userservicejuly2025.models.User;
import org.design.userservicejuly2025.repositories.session.SessionRepository;
import org.design.userservicejuly2025.repositories.tokens.TokenRepository;
import org.design.userservicejuly2025.repositories.users.UserRepository;
import org.design.userservicejuly2025.utils.DateUtils;
import org.design.userservicejuly2025.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {


    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final DateUtils dateUtils;
    private final JWTUtils jwtUtils;

    @Value("${jwt.expiration.time}")
    private long expirationTime;

    public AuthServiceImpl(BCryptPasswordEncoder passwordEncoder,
                               UserRepository userRepository,
                           SessionRepository sessionRepository, DateUtils dateUtils,
                           JWTUtils jwtUtils) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.dateUtils=dateUtils;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserViewDTO signUp(String username, String email, String password) {
        Optional<User> userOptional=userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            throw new RuntimeException("user already exists with email: " + email);
        }
        User user = User.from(username, email, passwordEncoder.encode(password));
        userRepository.save(user);
        return UserViewDTO.from(user);
    }

    @Override
    public String login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with email: " + email);
        }
        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password for user: " + email);
        }
        // Here you would typically create a session or token for the user
        // For simplicity, we are not implementing that part in this example

        Date expirationDate=new Date(System.currentTimeMillis() + expirationTime);
        Session session =new Session();
        session.setDeleted(false);
        session.setToken(jwtUtils.generateToken(user,expirationDate));
        session.setExpirationDate(expirationDate);
        session.setUser(user);
        sessionRepository.save(session);
        return session.getToken();
    }

    @Override
    public UserViewDTO validateToken(String token) {
        Optional<Session> sessionOptional = sessionRepository.findByToken(token);
        if (sessionOptional.isEmpty()) {
            throw new RuntimeException("Invalid token: " + token);
        }
        Session session = sessionOptional.get();
        if (session.getDeleted() || session.getExpirationDate().before(new Date())) {
            throw new RuntimeException("Token is expired or deleted: " + token);
        }
        if (!jwtUtils.validateToken(token)) {
            throw new RuntimeException("Invalid token for user: " + session.getUser().getEmail());
        }
        return UserViewDTO.from(session.getUser());
    }

    @Override
    public UserViewDTO getUserById(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with email: " + email);
        }
        return UserViewDTO.from(userOptional.get());
    }

    @Override
    public void logout(String token) {

    }
}
