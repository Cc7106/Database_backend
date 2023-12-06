package com.example.demo.Dao;

import com.example.demo.Model.Booking.BookingStatus;
import com.example.demo.Model.Car.CarStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BookingStatusRepository extends CrudRepository<BookingStatus, Integer> {

    @Query("select s from BookingStatus s where s.status = :status")
    BookingStatus findBookingStatusByString(String status);
}
