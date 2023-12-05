package com.example.demo.Service;

import com.example.demo.Dao.RoleRepository;
import com.example.demo.Dao.UserRepository;
import com.example.demo.Exception.LoginException;
import com.example.demo.Exception.RegistrationException;
import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    public User customerRegistration(String username, String email,
                                     String phoneNumber, String password) {
        if (userRepository.findUserByEmail(email) != null) {
            throw new RegistrationException();
        }

        Role customer = roleRepository.findByName("CUSTOMER");
        if (customer == null) {
            customer = new Role("CUSTOMER");
        }
        User user = new User(username, email, phoneNumber, password);
        user.setRole(customer);
        return userRepository.save(user);
    }


    public User login(String email, String password) {
        if (userRepository.findUserByEmail(email) == null) {
            throw new LoginException();
        } else {
            User user = userRepository.findUserByEmail(email);
            if (!user.getPassword().equals(password)) {
                throw new LoginException();
            } else {
                //需要跳转用户页面
                return user;
            }
        }
    }








}
