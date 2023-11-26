package com.example.demo.Dao;

import com.example.demo.Model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    @Query("SELECT r from Role r where r.roleName = :name")
    Role findByName(@Param("name") String name);
}
