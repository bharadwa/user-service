package org.design.userservicejuly2025.repositories.tokens;

import org.design.userservicejuly2025.models.BaseModel;
import org.design.userservicejuly2025.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {

    Token save(Token entity);
     @Override
    Optional<Token> findById(Long id);

    Optional<Token> findByValueAndDeletedFalse(String token);

    Optional<Token> findByValue(String token);
}
