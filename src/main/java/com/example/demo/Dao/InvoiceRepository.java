package com.example.demo.Dao;

import com.example.demo.Model.Booking.Booking;
import com.example.demo.Model.Booking.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface InvoiceRepository extends CrudRepository<Invoice, String> {

}
