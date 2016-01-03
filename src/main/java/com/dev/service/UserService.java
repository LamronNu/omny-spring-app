package com.dev.service;

import com.dev.model.User;
import com.dev.repository.UserRepository;
import org.apache.log4j.Logger;
import org.hsqldb.util.CSVWriter;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author OlgaPrylypko
 *         date: 30.12.2015
 */

@Service
public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class);
    private static final String FILE_NAME_PATTERN = "users_created_%s_days_ago.csv";
    private static final String DATE_TIME_PATTERN = "dd.MM.yyyy";

    @Autowired
    private UserRepository userRepository;

    public File getCsvFile(Integer daysCount) {
        List<User> users = getUsers(daysCount);
        if (users.size() == 0) {
            return null;
        }
        String fileName = String.format(FILE_NAME_PATTERN, daysCount);
        File file = new File(fileName);
        try {
            CSVWriter csvWriter = new CSVWriter(file, "UTF-8");
            String[] headers = { "id", "firstName", "lastName", "homeAddress", "createdDate" };
            csvWriter.writeHeader(headers);
            for (User user : users) {
                String[] data = new String[5];
                data[0] = "" + user.getId();
                data[1] = user.getFirstName();
                data[2] = user.getLastName();
                data[3] = user.getHomeAddress();
                data[4] = user.getCreatedDate().toString(DATE_TIME_PATTERN);
                csvWriter.writeData(data);
            }
            csvWriter.close();
        } catch (IOException e) {
            LOG.error("cant create csv-file!", e);
        }
        return file;
    }

    public List<User> getUsers(Integer daysCount) {
        LOG.info("daysCount=" + daysCount);
        DateTime dateTime = DateTime.now().minusDays(daysCount).withTimeAtStartOfDay();
        LOG.info("find on date=" + dateTime.toString(DATE_TIME_PATTERN));
        List<User> users = userRepository.findByCreatedDate(dateTime);
        LOG.info(String.format("find %s users", users.size()));
        return users;
    }
}
