package org.tropia.urlshortener.service;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.tropia.urlshortener.entity.Url;
import org.tropia.urlshortener.entity.dto.UrlCreateDto;
import org.tropia.urlshortener.entity.dto.UrlReadDto;
import org.tropia.urlshortener.repository.UrlRepository;
import org.tropia.urlshortener.util.mapper.UrlCreateMapper;
import org.tropia.urlshortener.util.mapper.UrlReadMapper;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;
    private final UrlReadMapper urlReadMapper;
    private final UrlCreateMapper urlCreateMapper;

    @Transactional
    public UrlReadDto save(UrlCreateDto dto) {
        Url url = urlCreateMapper.map(dto);
        url.setShortUrl(generateShortUrl());
        url.setCreatedAt(Instant.now());
        urlRepository.save(url);
        return urlReadMapper.map(url);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "url", key = "#shortUrl")
    public UrlReadDto getByShortUrl(String shortUrl) {
        return urlReadMapper.map(urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found")));
    }

    private String generateShortUrl() {
        return UUID.randomUUID().toString().substring(0, 9);
    }
}
