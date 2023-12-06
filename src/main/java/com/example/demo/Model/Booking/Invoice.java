package com.example.demo.Model.Booking;

import com.example.demo.Model.User;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity @Table
public class Invoice {
    @Id @NotNull
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn (name =  "bookingId")
    private Booking booking;

    @NotNull
    private float totalPrice;

    @NotNull
    private Boolean paid;

    @NotNull
    @ManyToOne
    @JoinColumn (name = "admin-in-charge")
    private User AdminInCharge;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public User getAdminInCharge() {
        return AdminInCharge;
    }

    public void setAdminInCharge(User adminInCharge) {
        AdminInCharge = adminInCharge;
    }
}
