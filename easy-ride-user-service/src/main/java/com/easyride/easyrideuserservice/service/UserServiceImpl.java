package com.easyride.easyrideuserservice.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.easyride.easyrideuserservice.exception.DatabaseEmptyException;
import com.easyride.easyrideuserservice.model.User;
import com.easyride.easyrideuserservice.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@Service
public class UserServiceImpl implements IUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserRepository userRepo;

    static String SIGNINGKEY;

    @Value("${SIGNING_KEY}")
    public void setSigningkey(String signingkey) {
        SIGNINGKEY = signingkey;
    }

    // For getting the user profile using username from the database
    public User loadByUsername(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("EZ_R")) {
                    String user;
                    try {
                        user = Jwts.parser().setSigningKey(SIGNINGKEY).parseClaimsJws(cookie.getValue()).getBody()
                                .get("em", String.class);
                    } catch (ExpiredJwtException exception) {
                        logger.error("In loadByUsername method" + LocalDateTime.now() + " " + exception.getMessage());
                        return null;
                    }
                    if (user != null) {
                        return this.userRepo.findByEmail(user);
                    }
                }
            }
        }
        return null;
    }

    // For getting user by user email id
    public User findByUserEmail(String email) {
        return userRepo.findByEmail(email);
    }

    // For getting all the client profile data from the database
    public List<User> findAllUsers() throws DatabaseEmptyException {
        if (userRepo.findAll().size() == 0) {
            throw new DatabaseEmptyException("Database is Empty");
        } else {
            return userRepo.findAll();
        }
    }

}
