package org.design.userservicejuly2025.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.design.userservicejuly2025.models.enums.SessionStatus;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Date;

@Getter
@Setter
@Entity(name = "session")
public class Session extends BaseModel {
    @Lob
    private String token;
    private Date expirationDate;
    @ManyToOne
    private User user;
    private SessionStatus status;
}
