package com.example.demo.Dao;

import com.example.demo.Model.Booking.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, String> {
}
