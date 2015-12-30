package com.dev.controller;

import com.dev.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author OlgaPrylypko
 *         date: 29.12.2015
 */

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/{id}")
    public String user(@PathVariable Long id, Model model) {
        LOG.info("user page");
        model.addAttribute("user", userRepository.findOne(id));
        LOG.info("find user by id={id}: " + userRepository.findOne(id));
        return "user";
    }
}
