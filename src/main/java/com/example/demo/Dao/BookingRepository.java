package com.example.demo.Dao;

import com.example.demo.Model.Booking.Booking;
import com.example.demo.Model.Booking.BookingStatus;
import com.example.demo.Model.Booking.Invoice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface BookingRepository extends CrudRepository<Booking, String> {

    @Query("select b from Booking b where b.customer.id = :id")
    ArrayList<Booking> findBookingByCustomerId(int id);


    @Modifying @Transactional
    @Query("update Booking  b set b.bookingStatus = :bookingStatus where b.id = :bookingId")
    void updateBookingStatus(String bookingId, BookingStatus bookingStatus);


    @Modifying @Transactional
    @Query("update Booking  b set b.invoice = :invoice where b.id = :bookingId")
    void updateInvoiceId(String bookingId, Invoice invoice);


    @Modifying @Transactional
    @Query(value = "ALTER TABLE booking ADD CONSTRAINT ContactNumberCheck CHECK(LENGTH(contact_number) = 11)" , nativeQuery = true)
    void addConstraintForContactNumber();

    @Modifying @Transactional
    @Query(value = "ALTER TABLE booking ADD CONSTRAINT LicenseNoCheck CHECK(LENGTH(license_no) = 12)" , nativeQuery = true)
    void addConstraintForLicenseNo();

}
