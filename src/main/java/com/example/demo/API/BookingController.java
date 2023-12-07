package com.example.demo.API;


import com.example.demo.Model.Booking.Booking;
import com.example.demo.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping( "/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/createBooking")
    public ResponseEntity<Booking> createNewBooking(@RequestParam int customerId, @RequestParam String carId, @RequestParam Date dateToCollect,
                                                  @RequestParam int days, @RequestParam float priceToPay, @RequestParam String name,
                                                    @RequestParam  String contactNumber, @RequestParam  String licenseNo) {
        Booking booking =  bookingService.makeABooking(customerId, carId, dateToCollect, days, priceToPay, name, contactNumber, licenseNo);
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/cancelBooking")
    public ResponseEntity<Booking> cancelBooking(@RequestParam String bookingId) {
        Booking booking = bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/getAllBookings")
    public @ResponseBody Iterable<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/checkUsersBooking")
    public @ResponseBody Iterable<Booking> checkUserspBooking(int customerId) {
        return bookingService.getBookingByUserId(customerId);
    }

    @GetMapping("/getBooking")
    public ResponseEntity<Booking> getBookingById(String bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(booking);
    }
}
