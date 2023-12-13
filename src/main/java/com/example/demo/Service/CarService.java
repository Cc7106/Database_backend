package com.example.demo.Service;

import com.example.demo.Dao.*;
import com.example.demo.Exception.NoAvailableCarException;
import com.example.demo.Model.Car.Car;
import com.example.demo.Model.Car.CarMake;
import com.example.demo.Model.Car.CarModel;
import com.example.demo.Model.Car.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    @Autowired
    private CarMakeRepository carMakeRepository;
    @Autowired
    private CarModelRepository carModelRepository;
    @Autowired
    private CarCategoryRepository carCategoryRepository;
    @Autowired
    private CarStatusRepository carStatusRepository;
    @Autowired
    private FuelSourceRepository fuelSourceRepository;
    @Autowired
    private CarRepository carRepository;

    public Iterable<CarModel> getCars(String carMake, String fuelSource, String carModelName) {
        ArrayList<CarModel> list1, list2, list3;
        if (carMake.equals("All")) {
            list1 = (ArrayList<CarModel>) carModelRepository.findAll();
        } else {
            list1 = carModelRepository.findCarModelByCarMakeName(carMake);
        }

        if (fuelSource.equals("All")) {
            list2 = (ArrayList<CarModel>) carModelRepository.findAll();
        } else {
            list2 = carModelRepository.findCarModelByFuelSourceType(fuelSource);
        }

        if (carModelName.isEmpty()) {
            list3 = (ArrayList<CarModel>) carModelRepository.findAll();
        } else {
            list3 = carModelRepository.findCarModelByName(carModelName);
        }

        ArrayList<CarModel> finalList = new ArrayList<>();

        for (int i = 0; i < list1.size(); i++) {
            CarModel car = list1.get(i);
            if (list2.contains(car) && list3.contains(car)) {
                finalList.add(car);
            }
        }
        return finalList;
    }

    public Iterable<CarModel> getCars2(String carMake, String fuelSource, String carModelName) {
        if (carMake.equals("All") && fuelSource.equals("All") && carModelName.isEmpty()) {
            //没有任何filter
            return carModelRepository.findAll();
        }

        if (carMake.equals("All")) {
            if (fuelSource.equals("All")) {
                //只filter name
                return carModelRepository.findCarModelByName(carModelName);
            } else {
                if (carModelName.isEmpty()) {
                    //只filter fuelsource
                    return carModelRepository.findCarModelByFuelSourceType(fuelSource);
                } else {
                    //filter fuelSource n name
                    return carModelRepository.findCarModelByFSnName(fuelSource, carModelName);
                }
            }
        } else {
            if (fuelSource.equals("All")) {
                if (carModelName.isEmpty()) {
                    //只filter carMake
                    return carModelRepository.findCarModelByCarMakeName(carMake);
                } else {
                    //filter carMake n name
                    return carModelRepository.findCarModelByCMnName(carMake, carModelName);
                }
            } else {
                if (carModelName.isEmpty()) {
                    //filter carMake n fuelSource
                    return carModelRepository.findCarModelByCMnFS(carMake, fuelSource);
                } else {
                    //filter carMake n filter n name
                    return carModelRepository.findCarModelByCMnFSnName(carMake, fuelSource, carModelName);
                }
            }
        }


    }

    public Iterable<CarModel> getCar01(String carMake) {
        if (carMake.equals("All")) {
            return carModelRepository.findAll();
        } else {
            return carModelRepository.findCarModelByCarMakeName(carMake);
        }
    }

    public Iterable<CarModel> getCar02(String fuelSource) {
        if (fuelSource.equals("All")) {
            return carModelRepository.findAll();
        } else {
            return carModelRepository.findCarModelByFuelSourceType(fuelSource);
        }
    }

    public Iterable<CarModel> getCar03(String carModelName) {
        if (carModelName.equals("All")) {
            return carModelRepository.findAll();
        } else {
            return carModelRepository.findCarModelByName(carModelName);
        }
    }

    public Car getACar(String carModelId) {
        CarModel carModel = carModelRepository.findById(carModelId).orElse(null);
        assert carModel != null;
        for (Car car: carRepository.findCarsByModelId(carModelId)) {
            if (car.isAvailable()) {
                return car;
            }
        }
        return null;
    }



    public void setCar4Rent(Car car) {
        CarStatus carStatus = carStatusRepository.findCarStatusByString("RENT");
        carRepository.updateCarStatus(car.getId(), carStatus);
        checkNSetAvailable(car.getCarModel());
    }

    public void returnCar(Car car) {
        CarStatus carStatus = carStatusRepository.findCarStatusByString("AVAILABLE");
        carRepository.updateCarStatus(car.getId(), carStatus);
        checkNSetAvailable(car.getCarModel());
    }

    public void setCarDown(Car car) {
        CarStatus carStatus = carStatusRepository.findCarStatusByString("DOWN");
        carRepository.updateCarStatus(car.getId(), carStatus);
        checkNSetAvailable(car.getCarModel());
    }

    public void checkNSetAvailable(CarModel carModel) {
        for (Car car: carRepository.findCarsByModelId(carModel.getId())) {
            if (car.getCarStatus().getStatus().equals("AVAILABLE")) {
                carModelRepository.updateCarModelAvailableNow(carModel.getId(), true);
                return;
            }
        }
        carModelRepository.updateCarModelAvailableNow(carModel.getId(), false);
    }



    public Boolean isCarModelAvailable(String carModelId) {
        Iterable<Car> cars = carRepository.findCarsByModelId(carModelId);
        for (Car car: cars) {
            if (car.getCarStatus().getStatus().equals("AVAILABLE")) {
                return true;
            }
        }
        return false;
    }


    public Car getCarbyCarId(String carId) {
        return carRepository.findById(carId).orElse(null);
    }

}
