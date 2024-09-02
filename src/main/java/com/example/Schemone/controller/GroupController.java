package com.example.Schemone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * ランダムリンクを生成するコントローラクラス.
 *
 * @author tuguk
 */
@Controller
@RequestMapping("/")
public class GroupController {

    @GetMapping
    public RedirectView redrectToRandomLink() {
        String randomLink = generateRandomLink();
        return new RedirectView("/" + randomLink + "/top");
    }

    private String generateRandomLink() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    @GetMapping("/{randomLink}/top")
    public ModelAndView showTopPage(@PathVariable("randomLink") String randomLink) {
        ModelAndView modelAndView = new ModelAndView("top/top");
        modelAndView.addObject("randomLink", randomLink);
        // 必要な処理はここより下
        return modelAndView;
    }

}
