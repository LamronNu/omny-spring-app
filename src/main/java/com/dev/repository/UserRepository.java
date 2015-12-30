package com.dev.repository;

import com.dev.model.User;
import org.joda.time.DateTime;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author OlgaPrylypko
 * date: 29.12.2015
 */

public interface UserRepository extends CrudRepository<User, Long> {
    User findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByCreatedDate(DateTime createdDate);
}
