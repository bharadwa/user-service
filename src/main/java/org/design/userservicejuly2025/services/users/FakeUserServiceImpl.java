package org.design.userservicejuly2025.services.users;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.design.userservicejuly2025.dtos.response.TokenViewDTO;
import org.design.userservicejuly2025.dtos.response.UserViewDTO;
import org.design.userservicejuly2025.models.Token;
import org.design.userservicejuly2025.models.User;
import org.design.userservicejuly2025.repositories.tokens.TokenRepository;
import org.design.userservicejuly2025.repositories.users.UserRepository;
import org.design.userservicejuly2025.utils.DateUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@Qualifier("fakeUserServiceImpl")
public class FakeUserServiceImpl implements  IUserService{

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final DateUtils dateUtils;

    public FakeUserServiceImpl(BCryptPasswordEncoder passwordEncoder,
                               UserRepository userRepository,
                               TokenRepository tokenRepository, DateUtils dateUtils) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.dateUtils=dateUtils;
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
    public TokenViewDTO signIn(String email, String password) {
        Optional<User> optionalUser =userRepository.findByEmail(email);
        if(!optionalUser.isPresent()){
            throw new RuntimeException("user does not exists with email: " + email);
        }
        if(!passwordEncoder.matches(password, optionalUser.get().getPassword())){
            throw new RuntimeException("password does not match");
        }

        Token token =new Token();
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        token.setExpirationDate(dateUtils.getTokenExpiration());
        token.setUser(optionalUser.get());
        return TokenViewDTO.from(tokenRepository.save(token));

    }

    @Override
    public UserViewDTO validateToken(String token) {
        return UserViewDTO.from(tokenRepository.findByValueAndDeletedFalse(token)
                .orElseThrow(() -> new RuntimeException("token not found or deleted")).getUser());
    }

    @Override
    public UserViewDTO getUserById(String email) {
        return UserViewDTO.from(userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found with email: " + email)));
    }

    @Override
    public void logout(String token) {
       Optional<Token> optionalToken =tokenRepository.findByValueAndDeletedFalse(token);
       if(optionalToken.isPresent()){
          Token savedToken =optionalToken.get();
          savedToken.setDeleted(true);
          tokenRepository.save(savedToken);
       }else {
           throw new RuntimeException("token not found");
       }
    }
}
