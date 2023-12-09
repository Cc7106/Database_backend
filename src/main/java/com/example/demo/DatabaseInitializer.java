package com.example.demo;

import com.example.demo.Dao.BookingRepository;
import com.example.demo.Dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void run(String... args) throws Exception {
//        userRepository.addConstraintForPhoneNumber();
//        userRepository.addConstraintForPassword();
//        userRepository.addConstraintForEmail();
//        bookingRepository.addConstraintForContactNumber();
//        bookingRepository.addConstraintForLicenseNo();
    }
}
