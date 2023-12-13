package com.example.demo.API;

import com.example.demo.Model.Booking.Invoice;
import com.example.demo.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping( "/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/makeInvoice")
    public ResponseEntity<Invoice> createInvoice(@RequestParam int adminId, @RequestParam String bookingId) {
        Invoice invoice = invoiceService.makeInvoice(bookingId, adminId);
        return ResponseEntity.ok().body(invoice);
    }

}
