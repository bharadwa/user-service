package org.design.userservicejuly2025.services.users;

import jakarta.transaction.Transactional;
import org.design.userservicejuly2025.dtos.response.CustomerUserViewDTO;
import org.design.userservicejuly2025.models.User;
import org.design.userservicejuly2025.repositories.users.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public CustomerUserViewDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user= userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User with email " + username + " not found");
        }
        User foundUser = user.get();
        return new CustomerUserViewDTO(foundUser);
    }
}
