package com.example.demo.Service;


import com.example.demo.Dao.BookingRepository;
import com.example.demo.Dao.BookingStatusRepository;
import com.example.demo.Dao.CarRepository;
import com.example.demo.Dao.UserRepository;
import com.example.demo.Exception.BookingNotFoundException;
import com.example.demo.Exception.CarNotFoundException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Model.Booking.Booking;
import com.example.demo.Model.Booking.BookingStatus;
import com.example.demo.Model.Booking.Invoice;
import com.example.demo.Model.Car.Car;
import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingStatusRepository bookingStatusRepository;
    @Autowired
    private CarService carService;
    @Autowired
    UserService userService;

    public Booking makeABooking(int customerId, String carId, Date dateToCollect,
                                int days, float priceToPay, String name, String contactNumber, String licenseNo) {
        User customer = userService.getUserById(customerId);
        if (customer == null) {
            throw new UserNotFoundException();
        }

        Car car = carService.getCarbyCarId(carId);
        if (car == null) {
            throw new CarNotFoundException();
        }

        BookingStatus pendingStatus = bookingStatusRepository.findBookingStatusByString("PENDING");

        //下单
        carService.setCar4Rent(car);
        Booking booking = new Booking(customer, car, dateToCollect, days, priceToPay,
                name, contactNumber, licenseNo);
        booking.setBookingStatus(pendingStatus);
        bookingRepository.save(booking);
        return booking;
    }

    public Booking cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            throw new BookingNotFoundException();
        }

        carService.returnCar(booking.getCar());
        BookingStatus bookingStatus = bookingStatusRepository.findBookingStatusByString("CANCELLED");
        bookingRepository.updateBookingStatus(bookingId, bookingStatus);
        return booking;
    }



    public Iterable<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            throw new BookingNotFoundException();
        }
        return booking;
    }

    public ArrayList<Booking> getBookingByUserId(int userId) {
        return bookingRepository.findBookingByCustomerId(userId);
    }

}
