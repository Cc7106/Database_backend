package com.example.demo.Service;


import com.example.demo.Dao.BookingRepository;
import com.example.demo.Dao.BookingStatusRepository;
import com.example.demo.Exception.BookingNotFoundException;
import com.example.demo.Exception.CarNotFoundException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Model.Booking.Booking;
import com.example.demo.Model.Booking.BookingStatus;
import com.example.demo.Model.Booking.Invoice;
import com.example.demo.Model.Car.Car;
import com.example.demo.Model.Car.CarModel;
import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingStatusRepository bookingStatusRepository;
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;
    private LocalDate todayDate = LocalDate.now();

    public Booking makeABooking(int customerId, String carId, Date dateToCollect,
                                int days, float priceToPay, String name, String contactNumber, String licenseNo) {
        User customer = userService.getUserById(customerId);
        if (customer == null) {
            throw new UserNotFoundException();
        }

        Car car = carService.getCarbyCarId(carId);
        if (car == null) {
            throw new CarNotFoundException();
        }

        BookingStatus pendingStatus = bookingStatusRepository.findBookingStatusByString("PENDING");
        Booking booking = new Booking(customer, car, dateToCollect, days, priceToPay,
                name, contactNumber, licenseNo);
        booking.setBookingStatus(pendingStatus);
        //下单
        try {
            booking = bookingRepository.save(booking);
        } catch (Exception e) {
            //触发constraint
            Throwable rootCause = e.getCause().getCause();
            throw new DataIntegrityViolationException(rootCause.getMessage());
        }
        //成功
        carService.setCar4Rent(car);
        return booking;
    }

    public void setBookingToOnGoing(Booking booking, Invoice invoice) {
        BookingStatus bookingStatus = bookingStatusRepository.findBookingStatusByString("ON-GOING");
        booking.setBookingStatus(bookingStatus);
        booking.setInvoice(invoice);
        bookingRepository.updateBookingStatus(booking.getId(), bookingStatus);
        bookingRepository.updateInvoiceId(booking.getId(), invoice);
    }

    public Booking setBookingToCancelled(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            throw new BookingNotFoundException();
        }

        carService.returnCar(booking.getCar());
        BookingStatus bookingStatus = bookingStatusRepository.findBookingStatusByString("CANCELLED");
        bookingRepository.updateBookingStatus(bookingId, bookingStatus);
        booking.setBookingStatus(bookingStatus);
        return booking;
    }

    public Booking setBookingToDone(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            throw new BookingNotFoundException();
        }

        carService.returnCar(booking.getCar());
        BookingStatus bookingStatus = bookingStatusRepository.findBookingStatusByString("DONE");
        bookingRepository.updateBookingStatus(bookingId, bookingStatus);
        booking.setBookingStatus(bookingStatus);
        return booking;
    }


    public Iterable<Booking> getAllBookings() {
        //同时检查是否有欠费
        refreshStatus();
        return bookingRepository.findAll();
    }

    public Booking getBookingById(String bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
//        if (booking == null) {
//            throw new BookingNotFoundException();
//        }
//        return booking;
    }

    //显示user的所有bookings
    public ArrayList<Booking> getBookingByUserId(int userId) {
        //User user = userService.getUserById(userId);
        return bookingRepository.findBookingByCustomerId(userId);
    }

    //显示某一天的bookings
    public ArrayList<Booking> getBookingByDate(Date date) {
        return bookingRepository.findBookingByDateToCollect(date);
    }

    //显示某个status的所有bookings
    public ArrayList<Booking> getBookingByStatus(String bookingStatus) {
        return bookingRepository.findBookingByBookingStatus(bookingStatus);
    }

    //根据价钱显示所有bookings
    public ArrayList<Booking> getAllSortedBookingList() {
        return bookingRepository.sortAllBookingsByPrice();
    }

    //根据价钱显示某一天的bookings
    public ArrayList<Booking> getSortedBookingList(Date date) {
        return bookingRepository.sortBookingsByPrice(date);
    }





    public void deleteBooking(String bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    public void refreshStatus() {
        ArrayList<Booking> bookings = (ArrayList<Booking>) bookingRepository.findAll();
        for (Booking booking: bookings) {
            if (booking.getBookingStatus().getStatus().equals("ON-GOING")) {
                LocalDate date = booking.getDateToCollect().toLocalDate();
                int days = booking.getDays();
                if (date.plusDays(days).isBefore(todayDate)) {
                    BookingStatus bookingStatus = bookingStatusRepository.findBookingStatusByString("OVERDUE");
                    bookingRepository.updateBookingStatus(booking.getId(), bookingStatus);
                    booking.setBookingStatus(bookingStatus);
                }
            }
        }
    }

    public ArrayList<Booking> filterBookings(String bookingId, String carModelName, String bookingStatus, String date) {
        ArrayList<Booking> list1, list2, list3, list4;
        if (bookingId.isEmpty()) {
            list1 = (ArrayList<Booking>) bookingRepository.findAll();
        } else {
            Booking booking = bookingRepository.findById(bookingId).orElse(null);
            return new ArrayList<>() {{
                add(booking);
            }};
        }

        if (carModelName.isEmpty()) {
            list2 = (ArrayList<Booking>) bookingRepository.findAll();
        } else {
            list2 = bookingRepository.findBookingByCarModel(carModelName);
        }

        if (bookingStatus.equals("All")) {
            list3 = (ArrayList<Booking>) bookingRepository.findAll();
        } else {
            list3 = bookingRepository.findBookingByBookingStatus(bookingStatus);
        }

        if (date.equals("All") ) {
            list4 = (ArrayList<Booking>) bookingRepository.findAll();
        } else {
            list4 = bookingRepository.findBookingByDateToCollect(Date.valueOf(date));
        }

        ArrayList<Booking> finalList = new ArrayList<>();

        for (int i = 0; i < list1.size(); i++) {
            Booking booking = list1.get(i);
            if (list2.contains(booking) && list3.contains(booking) && list4.contains(booking)) {
                finalList.add(booking);
            }
        }
        return finalList;
    }
}
