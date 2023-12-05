package com.example.demo.Dao;

import com.example.demo.Model.Car.CarStatus;
import org.springframework.data.repository.CrudRepository;

public interface CarStatusRepository extends CrudRepository<CarStatus, Integer> {
}
