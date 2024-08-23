package com.example.OAuthAndOpenIDConnectLab.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;


@Controller
public class HomeController {
    @GetMapping({"", "/"})
    public String home(@AuthenticationPrincipal OAuth2User principal, Model model) {
        System.out.println(principal.getAttributes());
        String username = principal.getAttribute("login");
        String image = principal.getAttribute("avatar_url");

        model.addAttribute("username", username);
        model.addAttribute("imageUrl", image);

        return "home";
    }
}
