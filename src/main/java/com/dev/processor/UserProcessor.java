package com.dev.processor;

import com.dev.model.User;
import com.dev.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author OlgaPrylypko
 *         date: 02.01.2016
 */

@Component
public class UserProcessor implements ItemProcessor<Long, User> {

    private static final Logger LOG = Logger.getLogger(UserProcessor.class);

    @Autowired
    private UserService userService;

    @Override
    public User process(final Long id) throws Exception {

        return null;
    }
}
