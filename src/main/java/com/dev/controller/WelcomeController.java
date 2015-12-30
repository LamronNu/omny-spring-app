package com.dev.controller;

import com.dev.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author OlgaPrylypko
 *         date: 30.12.2015
 */

@Controller
public class WelcomeController {
    private static final Logger LOG = Logger.getLogger(WelcomeController.class);

    @Value("${application.message}")
    private String message;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        LOG.info("welcome page");
        model.put("message", message);
        model.put("users", userRepository.findAll());
        return "welcome";
    }

}
