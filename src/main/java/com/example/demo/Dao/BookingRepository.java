package com.example.demo.Dao;

import com.example.demo.Model.Booking.Booking;
import com.example.demo.Model.Booking.BookingStatus;
import com.example.demo.Model.Booking.Invoice;
import com.example.demo.Model.Booking.Review;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;

public interface BookingRepository extends CrudRepository<Booking, String> {

    @Query("select b from Booking b order by b.dateToCollect desc")
    Iterable<Booking> findAll();

    @Query("select b from Booking b where b.id LIKE %:bookingId% order by b.dateToCollect desc")
    Iterable<Booking> findBookingById(String bookingId);

    @Query("select b from Booking b where b.customer.id = :id order by b.dateToCollect desc")
    ArrayList<Booking> findBookingByCustomerId(int id);

    @Query("select b from Booking b where b.dateToCollect = :date")
    ArrayList<Booking> findBookingByDateToCollect(Date date);
    @Query("select b from Booking b where b.bookingStatus.status = :bookingStatus order by b.dateToCollect desc")
    ArrayList<Booking> findBookingByBookingStatus(String bookingStatus);
    @Query("select b from Booking b where b.car.carModel.name LIKE %:carModelName% order by b.dateToCollect desc")
    ArrayList<Booking> findBookingByCarModel(String carModelName);

    @Query("select b from Booking b where b.dateToCollect = :date order by b.PriceToPay DESC")
    ArrayList<Booking> sortBookingsByPrice(Date date);
    @Query("select b from Booking b order by b.PriceToPay DESC")
    ArrayList<Booking> sortAllBookingsByPrice();

    @Modifying @Transactional
    @Query("update Booking  b set b.bookingStatus = :bookingStatus where b.id = :bookingId ")
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
