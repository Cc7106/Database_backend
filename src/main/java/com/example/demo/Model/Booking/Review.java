package com.example.demo.Model.Booking;

import com.example.demo.Model.Booking.Booking;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity @Table
public class Review {
    @Id @NotNull
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private int rating;

    private String content;

    @NotNull
    @OneToOne
    @JoinColumn (name = "bookingId")
    private Booking booking;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
