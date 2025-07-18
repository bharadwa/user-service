package org.design.userservicejuly2025.repositories.users;

import org.design.userservicejuly2025.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User save(User entity);

    Optional<User> findByEmail(String  email);
}
