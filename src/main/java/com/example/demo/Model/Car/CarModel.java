package com.example.demo.Model.Car;

import com.example.demo.Model.Booking.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
import java.util.Objects;

@Entity @Table
public class CarModel {

    @Id @NotNull
    private String id;
    @NotNull
    private String name;

    @NotNull
    private int quantity;

    @NotNull
    @ManyToOne
    @JoinColumn (name = "carMakeId")
    private CarMake carMake;

    @NotNull
    @ManyToOne
    @JoinColumn (name = "carCategoryId")
    private CarCategory carCategory;

    @NotNull
    @ManyToOne
    @JoinColumn (name = "fuelSourceId")
    private FuelSource fuelSource;

    @NotNull
    private float pricePerDay;

    private Boolean availableNow = true;


    public void setPricePerDay(float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Boolean getAvailableNow() {
        return availableNow;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void minusOne() {
        this.quantity--;
    }

    public CarMake getCarMake() {
        return carMake;
    }

    public void setCarMake(CarMake carMake) {
        this.carMake = carMake;
    }

    public CarCategory getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(CarCategory carCategory) {
        this.carCategory = carCategory;
    }

    public FuelSource getFuelSource() {
        return fuelSource;
    }

    public void setFuelSource(FuelSource fuelSource) {
        this.fuelSource = fuelSource;
    }

    public float getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Long pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", carMake=" + carMake.getName() +
                ", carCategory=" + carCategory.getType() +
                ", fuelSource=" + fuelSource.getType() +
                ", pricePerDay=" + pricePerDay +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CarModel) {
            if (this.id.equals(((CarModel)o).getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, carMake, carCategory, fuelSource, pricePerDay);
    }

}
