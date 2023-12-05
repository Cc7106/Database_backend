package com.example.demo.Dao;

import com.example.demo.Model.Car.CarMake;
import com.example.demo.Model.Car.CarModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface CarModelRepository extends CrudRepository<CarModel, String> {

    @Query("select c from CarModel c where c.carMake.name = :name")
    ArrayList<CarModel> findCarModelByCarMakeName(String name);

    @Query("select c from CarModel c where c.carCategory.type = :type")
    ArrayList<CarModel> findCarModelByCarCategoryType(String type);

    @Query("select c from CarModel c where c.fuelSource.type = :fuelSource")
    ArrayList<CarModel> findCarModelByFuelSourceType(String fuelSource);

    @Query("select c from CarModel c where c.name LIKE %:name%")
    ArrayList<CarModel> findCarModelByName(String name);

    @Query("select c from CarModel c where c.fuelSource.type = :fuelSource AND c.carMake.name =:carMake")
    ArrayList<CarModel> findCarModelByCMnFS(String carMake, String fuelSource);

    @Query("select c from CarModel c where c.carMake.name =:carMake AND c.name LIKE %:name%")
    ArrayList<CarModel> findCarModelByCMnName(String carMake, String name);

    @Query("select c from CarModel c where c.fuelSource.type = :fuelSource AND c.name LIKE %:name%")
    ArrayList<CarModel> findCarModelByFSnName(String fuelSource, String name);

    @Query("select c from CarModel c where  c.carMake.name =:carMake AND " +
            "c.fuelSource.type = :fuelSource AND c.name LIKE %:name%")
    ArrayList<CarModel> findCarModelByCMnFSnName(String carMake, String fuelSource, String name);


}
