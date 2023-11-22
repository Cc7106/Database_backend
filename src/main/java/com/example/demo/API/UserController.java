package com.example.demo.API;

import com.example.demo.Dao.UserRepository;
import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping( "/demo")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public @ResponseBody String addNewUser(@RequestParam String email,
                                           @RequestParam String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return "Success!";
    }

    @GetMapping( "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }


}
