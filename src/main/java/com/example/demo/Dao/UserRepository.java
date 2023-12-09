package com.example.demo.Dao;

import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query ("SELECT u from User u where u.email = :email")
    User findUserByEmail(@Param("email")String email);

    @Modifying @Transactional
    @Query(value = "ALTER TABLE user ADD CONSTRAINT PhoneNumberCheck CHECK(LENGTH(phone_number) = 11)" , nativeQuery = true)
    void addConstraintForPhoneNumber();

    @Modifying @Transactional
    @Query(value = "ALTER TABLE user ADD CONSTRAINT PasswordCheck CHECK(LENGTH(password) >= 8 AND LENGTH(password) <= 20)" , nativeQuery = true)
    void addConstraintForPassword();


    @Modifying @Transactional
    @Query(value = "ALTER TABLE user ADD CONSTRAINT EmailFormatCheck CHECK (email REGEXP '^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$')", nativeQuery = true)
    void addConstraintForEmail();
}
