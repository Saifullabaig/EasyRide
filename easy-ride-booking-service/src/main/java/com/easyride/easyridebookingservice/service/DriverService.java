package com.easyride.easyridebookingservice.service;


import java.time.LocalDateTime;
import java.util.ArrayList;

import com.easyride.easyridebookingservice.model.Driver;
import com.easyride.easyridebookingservice.repo.DriverRepo;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class DriverService {

    @Autowired
    private DriverRepo repo;


    public void saveDriver(Driver driver) {
        repo.save(driver);
    }

    public ArrayList<Driver> getAllDriver(){
        return (ArrayList<Driver>) repo.findAll();
    }

    public Driver getDriver(int id) { return repo.findById(id).get(); }

    public String loadByUsername(HttpServletRequest request) {
        if (request.getCookies() != null || request.getCookies().length>0) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("EZ_R")) {
                    String user;
                    try {
                        user = Jwts.parser().setSigningKey("EASYRIDE").parseClaimsJws(cookie.getValue()).getBody()
                                .get("em", String.class);
                    } catch (ExpiredJwtException exception) {
                        return null;
                    }
                    if (!user.isEmpty()) {
                        return user;
                    }
                    else{
                        System.out.println("in else 1");
                        return "no_user";
                    }
                } else{
                    System.out.println("in else 2");
                    return "no_user";
                }
            }
        } else {
            System.out.println("in else 3");
            return "no_user;";
        }
        return "nothing";
    }
}
