package com.example.demo.Dao;

import com.example.demo.Model.Booking.Booking;
import com.example.demo.Model.Booking.Invoice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface InvoiceRepository extends CrudRepository<Invoice, String> {


    @Procedure(name = "MakeInvoice")
    Invoice makeInvoice(@Param("bookingId") String bookingId, @Param("adminId") int id);

}
