package org.tropia.urlshortener.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tropia.urlshortener.entity.dto.UrlCreateDto;
import org.tropia.urlshortener.entity.dto.UrlReadDto;
import org.tropia.urlshortener.service.UrlService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class UrlRestController {
    private final UrlService service;

    @GetMapping("{shortUrl}")
    public ResponseEntity<Void> getUrl(@PathVariable String shortUrl) {
        UrlReadDto url = service.getByShortUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url.getOriginalUrl()))
                .build();
    }
    @PostMapping()
    public ResponseEntity<String> createUrl(@RequestBody UrlCreateDto dto) {
        UrlReadDto url = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(url.getShortUrl());
    }
}
