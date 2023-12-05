package com.example.demo.Dao;

import com.example.demo.Model.Car.CarCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CarCategoryRepository extends CrudRepository<CarCategory, String> {

    @Query("select c.id from CarCategory c where c.type = :carCategory")
    String findByString(String carCategory);
}
