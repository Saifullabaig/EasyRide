package com.easyride.easyrideuserservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easyride.easyrideuserservice.model.User;
import com.easyride.easyrideuserservice.service.IUserService;
import com.easyride.easyrideuserservice.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    private IUserService userService;

    // For providing user google profile
    @GetMapping("/userprofile")
    public User getUserProfile(HttpServletRequest request) {
        User user = userService.loadByUsername(request);
        return user;
    }
    // for logout
    @GetMapping(value = "/logout")
    public void userLogout(HttpServletResponse response) {
        String cookiename = "BOOK_UP";
        CookieUtil.clearCookie(response, cookiename);
    }

}
