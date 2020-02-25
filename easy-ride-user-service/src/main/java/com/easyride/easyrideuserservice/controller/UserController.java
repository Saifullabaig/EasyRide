package com.easyride.easyrideuserservice.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easyride.easyrideuserservice.exception.UserNotFoundException;
import com.easyride.easyrideuserservice.model.User;
import com.easyride.easyrideuserservice.service.IGoogleService;
import com.easyride.easyrideuserservice.service.IUserService;
import com.easyride.easyrideuserservice.util.CookieUtil;
import com.easyride.easyrideuserservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Value("${google.base.url}")
    private String baseUrl;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    String redirectUrl;

    @Value("${bookup.client.redirectUrl}")
    String clientdashboardredirectUrl;

    @Value("${Domain}")
    String domain;

    @Autowired
    private IUserService userService;

    @Autowired
    private IGoogleService googleService;

    @Autowired
    private User user;

    private static final String jwtTokenCookieName = "EZ_R";

    @GetMapping("/googlelogin")
    public RedirectView login() {
        RedirectView redirectView = new RedirectView();
        String url = baseUrl + "?client_id=" + this.clientId + "&redirect_uri=" + this.redirectUrl
                + "&response_type=code&scope=profile+email";
        redirectView.setUrl(url);
        return redirectView;
    }

    @GetMapping("/search")
    public RedirectView getUserDetails(@RequestParam("code") String code, HttpServletResponse httpServletResponse)
            throws IOException, UserNotFoundException {
        String accessToken = googleService.getAccessToken(code);
        user = googleService.getUserProfile(accessToken);
        String jwtToken = JwtUtil.addToken(httpServletResponse, user);
        Cookie cookie = CookieUtil.create(httpServletResponse, jwtTokenCookieName, jwtToken, false, -1, domain);
        RedirectView redirectview = new RedirectView();
        redirectview.setUrl(clientdashboardredirectUrl);
        return redirectview;
    }

    // For logging out the user form the system
    @GetMapping(value = "/userlogout")
    public void googleLogout(HttpServletResponse response) {
        String cookiename = jwtTokenCookieName;
        CookieUtil.clearCookie(response, cookiename);
    }
}
