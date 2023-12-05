package com.example.demo.Model.Car;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity @Table
public class Car {

    @Id @NotNull
    private String id;

    @NotNull
    private String carPlate;

    @NotNull
    @ManyToOne
    @JoinColumn (name = "carModelId")
    private CarModel carModel;


    @NotNull
    @ManyToOne
    @JoinColumn (name = "carStatusId")
    private CarStatus carStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }


    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

}

