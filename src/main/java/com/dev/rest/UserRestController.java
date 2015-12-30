package com.dev.rest;

import com.dev.model.User;
import com.dev.repository.UserRepository;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author OlgaPrylypko
 *         date: 30.12.2015
 */
@RestController
@RequestMapping("/rest/user")
public class UserRestController {
    private static final Logger LOG = Logger.getLogger(UserRestController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/{id}")
    public User getById(@PathVariable Long id) {
        //        String name = principal.getName();
        LOG.info("user rest page");

        User user = new User();
        user.setId(111L);
        user.setFirstName("Tom");
        user.setLastName("Jonson");
        user.setCreatedDate(new DateTime());
        user.setHomeAddress("London");
        return user;/*userRepository.findOne(id)*/
    }
}
