package org.example.urlshortener.controller;

import org.example.urlshortener.model.Url;
import org.example.urlshortener.service.UrlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/api/shorten")
    public String shortenUrl(@RequestBody String originalUrl) {
        Url savedUrl = urlService.shortenUrl(originalUrl);
        return "http://localhost:8080/" + savedUrl.getShortCode();
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortCode) {
        Optional<Url> urlOptional = urlService.getOriginalUrl(shortCode);

        if (urlOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(urlOptional.get().getOriginalUrl()));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}