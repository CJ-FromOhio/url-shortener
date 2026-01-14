package org.tropia.urlshortener.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String shortUrl;

    private String originalUrl;
    private Instant createdAt;

    @PostConstruct
    public void init() {
        this.createdAt = Instant.now();
    }
}
