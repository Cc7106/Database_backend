package com.example.demo.Dao;

import com.example.demo.Model.Car.Car;
import com.example.demo.Model.Car.CarModel;
import com.example.demo.Model.Car.CarStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CarRepository extends CrudRepository<Car, String> {

    @Query("select  c from Car c where c.carModel.id = :modelId")
    Iterable<Car> findCarsByModelId(String modelId);

    @Modifying
    @Transactional
    @Query ("update Car c set c.carStatus = :carStatus where c.id = :carId")
    void updateCarStatus(String carId, CarStatus carStatus);
}
