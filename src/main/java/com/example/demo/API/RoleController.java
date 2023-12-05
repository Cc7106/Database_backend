package com.example.demo.API;


import com.example.demo.Dao.RoleRepository;
import com.example.demo.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping( "/role")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/initialize")
    public void createAllRole() {
        Role role = new Role("ADMIN");
        roleRepository.save(role);
        role = new Role("CUSTOMER");
        roleRepository.save(role);
    }

//    @PostConstruct
//    public void initialRoleTable() {
//
//        Role role = new Role();
//        role = new Role("Admin");
//        roleRepository.save(role);
//        role = new Role("Customer");
//        roleRepository.save(role);
//
//    }
}
