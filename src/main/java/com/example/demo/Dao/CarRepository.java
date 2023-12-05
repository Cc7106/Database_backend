package com.example.demo.Dao;

import com.example.demo.Model.Car.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, String> {
}
