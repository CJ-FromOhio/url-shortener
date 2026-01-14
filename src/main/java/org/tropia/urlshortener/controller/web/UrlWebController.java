package org.tropia.urlshortener.controller.web;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tropia.urlshortener.entity.dto.UrlCreateDto;
import org.tropia.urlshortener.entity.dto.UrlReadDto;
import org.tropia.urlshortener.service.UrlService;

import java.net.URI;

@Controller
@RequiredArgsConstructor
public class UrlWebController {
    private final UrlService urlService;
    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("createDto",new UrlCreateDto());
        return "main";
    }
    @PostMapping
    public String postUrl(@ModelAttribute UrlCreateDto urlCreateDto,
                          Model model) {
        UrlReadDto dto = urlService.save(urlCreateDto);
        model.addAttribute("url","localhost:8080/"+dto.getShortUrl());
        return "shortener";
    }
    @GetMapping("{shortUrl}")
    public ResponseEntity<Void> viewUrl(@PathVariable String shortUrl, Model model) {
        UrlReadDto url = urlService.getByShortUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url.getOriginalUrl()))
                .build();
    }
}
