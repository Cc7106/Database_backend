package com.example.demo.Dao;

import com.example.demo.Model.Car.FuelSource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FuelSourceRepository extends CrudRepository<FuelSource, Integer> {

    @Query("select f.id from FuelSource f where f.type = :fuelSource")
    Integer findByString(String fuelSource);

}
