package com.dev.batch;

import com.dev.model.BatchJob;
import com.dev.model.User;
import com.dev.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author OlgaPrylypko
 *         date: 02.01.2016
 */

@Component
public class UserProcessor implements ItemProcessor<BatchJob, List<User>> {

    private static final Logger LOG = Logger.getLogger(UserProcessor.class);

    @Autowired
    private UserService userService;

    @Override
    public List<User> process(final BatchJob job) throws Exception {
        LOG.info("Run batch job");
        return userService.getUsers(job.getDaysCount());
    }
}
