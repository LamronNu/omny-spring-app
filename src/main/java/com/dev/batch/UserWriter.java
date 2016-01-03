package com.dev.batch;

import com.dev.model.User;
import org.apache.log4j.Logger;
import org.hsqldb.util.CSVWriter;
import org.springframework.batch.item.ItemWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author OlgaPrylypko
 *         date: 03.01.2016
 */
public class UserWriter implements ItemWriter<List<User>> {
    private static final Logger LOG = Logger.getLogger(UserWriter.class);
    private static final String FILE_NAME_PATTERN = "users_createdOn_%s.csv";
    private static final String DATE_TIME_PATTERN = "dd.MM.yyyy";

    @Override
    public void write(List<? extends List<User>> list) throws Exception {
        LOG.info("start write result");
        String fileName;
        File file;
        String createdDate;
        for (List<User> users : list) {
            if (users.size() == 0) {
                LOG.info("result is empty");
                continue;
            }
            createdDate = users.get(0).getCreatedDate().toString(DATE_TIME_PATTERN);
            fileName = String.format(FILE_NAME_PATTERN, createdDate);
            file = new File(fileName);
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
                    data[4] = createdDate;
                    csvWriter.writeData(data);
                }
                csvWriter.close();
                LOG.info("successfully create file " + fileName);
            } catch (IOException e) {
                LOG.error("cant create csv-file!", e);
            }
        }
    }
}
