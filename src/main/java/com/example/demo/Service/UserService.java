package com.example.demo.Service;

import com.example.demo.Dao.RoleRepository;
import com.example.demo.Dao.UserRepository;
import com.example.demo.Exception.LoginException;
import com.example.demo.Exception.RegistrationException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.mapping.Constraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            //触发constraint
            Throwable rootCause = e.getCause().getCause();
            throw new DataIntegrityViolationException(rootCause.getMessage());
        }


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

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
//        if (user == null) {
//            throw new UserNotFoundException();
//        }
//        return user;
    }


    public void adminDoEditRole(String userId, String roleName) {
        int id = Integer.parseInt(userId);
        User user = getUserById(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        Role role = roleRepository.findByName(roleName);
        userRepository.editRole(id, role);

    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
