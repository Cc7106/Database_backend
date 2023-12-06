package com.example.demo.Dao;

import com.example.demo.Model.Car.CarStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CarStatusRepository extends CrudRepository<CarStatus, Integer> {

    @Query("select c from CarStatus c where c.status = :status")
    CarStatus findCarStatusByString(String status);
}
