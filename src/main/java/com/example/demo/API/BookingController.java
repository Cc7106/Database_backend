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
        Booking booking = bookingService.setBookingToCancelled(bookingId);
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/returnCar")
    public ResponseEntity<Booking> returnCar(@RequestParam String bookingId) {
        Booking booking = bookingService.setBookingToDone(bookingId);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/getAllBookings")
    public @ResponseBody Iterable<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/checkUsersBooking")
    public @ResponseBody Iterable<Booking> checkUsersBooking(@RequestParam int customerId) {
        return bookingService.getBookingByUserId(customerId);
    }

    @GetMapping("/getBooking")
    public ResponseEntity<Booking> getBookingById(@RequestParam String bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/deleteBooking")
    public ResponseEntity<Void> deleteBookingById(@RequestParam String bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filterBooking")
    public @ResponseBody Iterable<Booking> searchBooking(@RequestParam String bookingId, @RequestParam String carModelName,
                                                         @RequestParam String bookingStatus, @RequestParam String date) {
        //检查是否有欠费
        bookingService.refreshStatus();
        Iterable<Booking> bookings = bookingService.filterBookings(bookingId, carModelName, bookingStatus, date);
        return bookings;
    }
}
