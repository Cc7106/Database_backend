package com.example.demo.Dao;

import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query ("SELECT u from User u where u.email = :email")
    User findUserByEmail(@Param("email")String email);
}
