package com.example.demo.Service;

import com.example.demo.Dao.InvoiceRepository;
import com.example.demo.Model.Booking.Booking;
import com.example.demo.Model.Booking.Invoice;
import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class InvoiceService {


    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;



    //Admin做invoice
    public Invoice makeInvoice(String bookingId, int adminId) {
        Booking booking = bookingService.getBookingById(bookingId);
        User admin = userService.getUserById(adminId);
        Invoice invoice = new Invoice(booking, booking.getPriceToPay(), admin);
        invoiceRepository.save(invoice);
        bookingService.setBookingToOnGoing(booking);
        return invoice;
    }
}
