package com.example.demo.API;

import com.example.demo.Model.Booking.Review;
import com.example.demo.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping( "/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/makeReview")
    public ResponseEntity<Review> makeReview(@RequestParam String bookingId, @RequestParam int stars, @RequestParam String content) {
        Review review = reviewService.makeReview(bookingId, stars, content);
        return ResponseEntity.ok(review);
    }

    @GetMapping("getReview")
    public ResponseEntity<Review> getReview(@RequestParam String bookingId) {
        return ResponseEntity.ok(reviewService.getReview(bookingId));
    }
}
