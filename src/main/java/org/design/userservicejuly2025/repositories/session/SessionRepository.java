package org.design.userservicejuly2025.repositories.session;

import org.design.userservicejuly2025.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    Session save(Session entity);

    Optional<Session> findByToken(String token);
}
