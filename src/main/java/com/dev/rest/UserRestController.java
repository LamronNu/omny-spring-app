package com.dev.rest;

import com.dev.model.User;
import com.dev.repository.UserRepository;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author OlgaPrylypko
 *         date: 30.12.2015
 */
@RestController
@RequestMapping("/rest")
public class UserRestController {
    private static final Logger LOG = Logger.getLogger(UserRestController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getById(@PathVariable Long id) {
        LOG.info("user rest service. getById");
        return userRepository.findOne(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAll() {
        LOG.info("user rest service. getAll");
        return (List<User>) userRepository.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User setUser(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String homeAddress) {
        LOG.info("user rest service. setUser");
        User user = id != null ? userRepository.findOne(id) : null;
        if (user == null) {
            if (firstName != null || lastName != null) {
                LOG.info("add new user");
                user = new User();
                user.setCreatedDate(new DateTime().withTimeAtStartOfDay());
            } else {
                throw new IllegalArgumentException(
                        "Incorrect input data! Required at least 1 parameter, first or last name!");
            }
        } else {
            LOG.info("update user with id=" + id);
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setHomeAddress(homeAddress);
        userRepository.save(user);
        return user;
    }
}
