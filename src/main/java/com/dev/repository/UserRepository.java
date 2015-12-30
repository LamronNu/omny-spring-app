package com.dev.repository;

import com.dev.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author OlgaPrylypko
 * date: 29.12.2015
 */

public interface UserRepository extends CrudRepository<User, Long> {
    User findByFirstNameAndLastName(String firstName, String lastName);
}
