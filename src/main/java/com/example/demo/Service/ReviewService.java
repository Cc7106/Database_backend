package com.example.demo.Service;

import com.example.demo.Dao.ReviewRepository;
import com.example.demo.Model.Booking.Booking;
import com.example.demo.Model.Booking.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BookingService bookingService;


    public Review makeReview(String bookingId, int stars, String content) {
        Booking booking = bookingService.getBookingById(bookingId);
        Review review = new Review(stars, content, booking);
        reviewRepository.save(review);
        bookingService.setBookingToRated(bookingId);
        return review;
    }

    public Review getReview(String bookingId) {
        return reviewRepository.findReviewByBooking(bookingId);
    }

    public Iterable<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public ArrayList<Review> filterReviews(String carMake, String stars) {
        ArrayList<Review> list1, list2;

        if (carMake.equals("All")) {
            list1 = (ArrayList<Review>) reviewRepository.findAll();
        } else {
            list1 = reviewRepository.findReviewByCarMake(carMake);
        }

        if (stars.equals("All")) {
            list2 = (ArrayList<Review>) reviewRepository.findAll();
        } else {
            list2 = reviewRepository.findReviewByRating(Integer.parseInt(stars));
        }

        ArrayList<Review> finalList = new ArrayList<>();

        for (int i = 0; i < list1.size(); i++) {
            Review review = list1.get(i);
            if (list2.contains(review)){
                finalList.add(review);
            }
        }
        return finalList;
    }
}
