package com.example.demo.Dao;

import com.example.demo.Model.Car.CarMake;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CarMakeRepository extends CrudRepository<CarMake, String> {

    @Query("select c.id from CarMake c where c.name = :carMake")
    String findByString(String carMake);

}
