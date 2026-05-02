package com.library.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home controller to handle the root URL and redirect to a welcome page.
 */
@Controller
public class HomeController {

    /**
     * Redirect root URL to the home page.
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
