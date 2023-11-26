package com.example.demo.API;

import com.example.demo.Dao.RoleRepository;
import com.example.demo.Dao.UserRepository;
import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping( "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


//    @PostMapping("/add")
//    public @ResponseBody String addNewUser(@RequestParam String email,
//                                           @RequestParam String password) {
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword(password);
//        userRepository.save(user);
//        return "Success!";
//    }

    @PostMapping("/register")
    public @ResponseBody String createNewUser(@RequestParam String username, @RequestParam String email,
                                              @RequestParam String phoneNumber, @RequestParam String password) {
        if (userRepository.findUserByEmail(email) != null) {
            return "Email has been registered!";
        }

        Role customer = roleRepository.findByName("CUSTOMER");
        if (customer == null) {
            customer = new Role("CUSTOMER");
        }

        User user = new User(username, email, phoneNumber, password);
        user.setRole(customer);
        userRepository.save(user);
        return "Success!";
    }


    @PostMapping("/login")
    public @ResponseBody String goToUser(@RequestParam String email,
                                         @RequestParam String password) {
        if (userRepository.findUserByEmail(email) == null) {
            return "Email has not been registered!";
        } else {
            User user = userRepository.findUserByEmail(email);
            if (!user.getPassword().equals(password)) {
                return "INCORRECT EMAIL OR PASSWORD!";
            } else {
                //需要跳转用户页面
                return "LOGIN SUCCESS!";
            }
        }
    }


    @GetMapping( "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping ("/deleteAcc")
    public @ResponseBody String deleteUser(@RequestParam String email,
                                           @RequestParam String password) {
        if (userRepository.findUserByEmail(email) == null) {
            return "Email has not been registered!";
        } else {
            User user = userRepository.findUserByEmail(email);
            if (!user.getPassword().equals(password)) {
                return "INCORRECT EMAIL OR PASSWORD!";
            } else {
                //需要跳转用户页面
                userRepository.delete(user);
                return "DELETE SUCCESS!";
            }
        }
    }


}
