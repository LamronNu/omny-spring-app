package com.dev.service;

import com.dev.model.User;
import com.dev.repository.UserRepository;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author OlgaPrylypko
 *         date: 30.12.2015
 */
@Service
public class InitDbService {

    private static final Logger LOG = Logger.getLogger(InitDbService.class);

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        LOG.info("init db");
        if (userRepository.findByFirstNameAndLastName("Tom", "Jonson") == null) {
            LOG.info("add user Tom Jonson");
            User user = new User();
            user.setFirstName("Tom");
            user.setLastName("Jonson");
            user.setCreatedDate(new DateTime(2015, 12, 6, 0, 0));
            user.setHomeAddress("London");
            userRepository.save(user);
        }
        if (userRepository.findByFirstNameAndLastName("Kate", "Mayson") == null) {
            LOG.info("add user Kate Mayson");
            User user = new User();
            user.setFirstName("Kate");
            user.setLastName("Mayson");
            user.setCreatedDate(new DateTime(2015, 12, 7, 0, 0));
            user.setHomeAddress("New York");
            userRepository.save(user);
        }
    }
}
