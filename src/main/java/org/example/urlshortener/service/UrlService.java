package org.example.urlshortener.service;
import org.example.urlshortener.model.Url;
import org.example.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int CODE_LENGTH = 6;

    public UrlService(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }

    public Url shortenUrl(String originalUrl){
        String shortCode = generateShortCode();
        Url url = new Url(originalUrl, shortCode, LocalDateTime.now());
        return urlRepository.save(url);
    }

    public Optional<Url> getOriginalUrl(String shortCode){
        return urlRepository.findByShortCode(shortCode);
    }

    private String generateShortCode(){
        Random random = new Random();
        String code;
        do{
            char[] buffer = new char[CODE_LENGTH];
            for(int i = 0; i< CODE_LENGTH; i++){
                int randomIndex = random.nextInt(CHARACTERS.length());
                buffer[i] = CHARACTERS.charAt(randomIndex);
            }
            code = new String(buffer);
        }while(urlRepository.findByShortCode(code).isPresent());
        return code;
    }
}
