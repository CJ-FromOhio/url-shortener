package org.tropia.urlshortener.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tropia.urlshortener.entity.Url;
import org.tropia.urlshortener.entity.dto.UrlCreateDto;
import org.tropia.urlshortener.entity.dto.UrlReadDto;
import org.tropia.urlshortener.repository.UrlRepository;
import org.tropia.urlshortener.util.mapper.UrlCreateMapper;
import org.tropia.urlshortener.util.mapper.UrlReadMapper;

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
        urlRepository.save(url);
        return urlReadMapper.map(url);
    }
    @Transactional
    public UrlReadDto getByShortUrl(String shortUrl) {
        return urlReadMapper.map(urlRepository.findByShortUrl(shortUrl)
                .orElse(null));
    }


    public String generateShortUrl() {
        return UUID.randomUUID().toString().substring(0, 9);
    }
}
