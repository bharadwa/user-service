package org.design.userservicejuly2025.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.websocket.server.ServerEndpoint;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name="tokens")
public class Token extends BaseModel {

    private String value;
    private Date expirationDate;

    @ManyToOne
    private User user;
}
