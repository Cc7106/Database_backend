package com.example.demo.Dao;

import com.example.demo.Model.Booking.Booking;
import com.example.demo.Model.Booking.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ReviewRepository extends CrudRepository<Review, String> {
    @Query("select r from Review r where r.booking.id = :bookingId order by r.booking.dateToCollect")
    Review findReviewByBooking(String bookingId);

    @Query("select r from Review r where r.booking.car.carModel.name LIKE %:carModelName% order by r.booking.dateToCollect")
    ArrayList<Review> findReviewByCarModelName(String carModelName);

    @Query("select r from Review r where r.rating = :stars order by r.booking.dateToCollect")
    ArrayList<Review> findReviewByRating(int stars);

    @Query("select r from Review r where r.booking.car.carModel.carMake.name = :carMake order by r.booking.dateToCollect")
    ArrayList<Review> findReviewByCarMake(String carMake);
}
