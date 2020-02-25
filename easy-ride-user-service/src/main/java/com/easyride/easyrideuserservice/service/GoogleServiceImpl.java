package com.easyride.easyrideuserservice.service;

import com.easyride.easyrideuserservice.exception.UserNotFoundException;
import com.easyride.easyrideuserservice.model.User;
import com.easyride.easyrideuserservice.repository.IUserRepository;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GoogleServiceImpl implements IGoogleService {

    @Value("${google.base.url}")
    private String baseUrl;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    String redirectUrl;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    User user;

    @Value("${google.access.token.url}")
    String googleAccessTokenUrl;

    @Value("${google.userdetails.url}")
    String googleProfileUrl;

    @Autowired
    private IUserRepository userRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // For posting the user data into the user database
    @Override
    public void addUserData(User user) throws DuplicateKeyException {
        if (userRepo.findByEmail(user.getEmail()) == null) {
            userRepo.save(user);
        } else {
            logger.error("User Already exists with Email " + user.getEmail());
        }
    }

    public String getAccessToken(String code) throws UserNotFoundException {
        String url = googleAccessTokenUrl;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "application/json");
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url)
                .queryParam("clientId", this.clientId).queryParam("client_secret", this.clientSecret)
                .queryParam("code", code).queryParam("grant_type", "authorization_code")
                .queryParam("redirect_uri", this.redirectUrl);
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<JSONObject> result = this.restTemplate.exchange(uriComponentsBuilder.toUriString(), HttpMethod.POST, request,
                JSONObject.class);
        if (result.getBody().get("access_token").toString().isEmpty())
            throw new UserNotFoundException("There is some problwm with the generation of the access token");
        return (String) result.getBody().get("access_token");
    }

    public User getUserProfile(String accessToken) throws UserNotFoundException {
        String url = googleProfileUrl;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		httpHeaders.set("Accept", "application/json");
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url).queryParam("access_token",
                accessToken);
        ResponseEntity<JSONObject> result = this.restTemplate.getForEntity(uriComponentsBuilder.toUriString(),
                JSONObject.class);
        JSONObject userDetails = result.getBody();
        user.setAvatarURL((String) userDetails.get("picture"));
        user.setEmail((String) userDetails.get("email"));
        user.setName((String) userDetails.get("given_name"));
        user.setLoggedIn(true);
        this.addUserData(user);
        return user;
    }

}
