package com.mullen.commander.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/github")
public class GithubController {

    @GetMapping("/repos")
    public ResponseEntity<String> getRepos(@RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient client) {
        String accessToken = client.getAccessToken().getTokenValue();

        WebClient webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .build();

        String repos = webClient.get()
                .uri("https://api.github.com/user/repos")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(repos);
    }
}
