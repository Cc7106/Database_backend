package com.example.demo.Service;

import com.example.demo.Dao.ReviewRepository;
import com.example.demo.Model.Booking.Booking;
import com.example.demo.Model.Booking.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
