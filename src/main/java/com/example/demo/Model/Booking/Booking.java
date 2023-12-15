package com.example.demo.Model.Booking;

import com.example.demo.Model.User;
import com.example.demo.Model.Car.Car;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Date;
import java.util.Objects;

@Entity @Table
public class Booking {
    @Id @NotNull
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @ManyToOne
    @JoinColumn (name = "customerId")
    private User customer;

    @NotNull
    @ManyToOne
    @JoinColumn (name = "carId")
    private Car car;

    @NotNull
    private Date dateToCollect;

    @NotNull
    private int days;

    @OneToOne
    @JoinColumn (name = "invoiceId")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn (name = "bookingStatusId")
    private BookingStatus bookingStatus;

    @NotNull
    private float PriceToPay;

    private String name;
    private String contactNumber;
    private String licenseNo;

    public Booking(User customer, Car car, Date dateToCollect, int days, float priceToPay, String name, String contactNumber, String licenseNo) {
        this.customer = customer;
        this.car = car;
        this.dateToCollect = dateToCollect;
        this.days = days;
        this.PriceToPay = priceToPay;
        this.name = name;
        this.contactNumber = contactNumber;
        this.licenseNo = licenseNo;
    }

    public Booking() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getDateToCollect() {
        return dateToCollect;
    }

    public void setDateToCollect(Date dateToCollect) {
        this.dateToCollect = dateToCollect;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public float getPriceToPay() {
        return PriceToPay;
    }

    public void setPriceToPay(float priceToPay) {
        PriceToPay = priceToPay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Booking) {
            if (this.id.equals(((Booking)o).getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, car, dateToCollect, days, bookingStatus, PriceToPay, name, contactNumber, licenseNo);
    }
}
