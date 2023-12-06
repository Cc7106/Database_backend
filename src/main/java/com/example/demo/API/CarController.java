package com.example.demo.API;


import com.example.demo.Model.Car.Car;
import com.example.demo.Model.Car.CarModel;
import com.example.demo.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping( "/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/getCars")
    public @ResponseBody Iterable<CarModel> searchCar(@RequestParam String carMake,
                                              @RequestParam String fuelSource,
                                                        @RequestParam String carModelName) {
        Iterable<CarModel> carModels = carService.getCars(carMake, fuelSource, carModelName);
        return carModels;
    }

    @GetMapping("/getCars2")
    public @ResponseBody Iterable<CarModel> searchCar2(@RequestParam String carMake,
                                                        @RequestParam String fuelSource,
                                                       @RequestParam String carModelName) {
        Iterable<CarModel> carsModels = carService.getCars2(carMake, fuelSource, carModelName);
        return carsModels;

    }

    @GetMapping("/getCarsByCarMake")
    public @ResponseBody Iterable<CarModel> searchCar01(@RequestParam String carMake) {
        return carService.getCar01(carMake);
    }

    @GetMapping("/getCarsByFuelSource")
    public @ResponseBody Iterable<CarModel> searchCar02(@RequestParam String fuelSource) {
        return carService.getCar02(fuelSource);
    }

    @GetMapping("/getCarsByCarModel")
    public @ResponseBody Iterable<CarModel> searchCar03(@RequestParam String carModelName) {
        return carService.getCar03(carModelName);
    }

    @GetMapping("/getACarForRentUse")
    public @ResponseBody Car getACar(@RequestParam String carModelId) {
        return carService.getACar(carModelId);

    }

    //测试rent功能的操作
//    @PostMapping("/rentACar")
//    public @ResponseBody void rentACar(@RequestParam String carModelId) {
//        Car car = carService.getACar(carModelId);
//        carService.setCar4Rent(car.getId());
//    }




}
