package com.example.demo.Model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity @Table
public class Role {

    @Id @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role() {

    }


    public void setId(Integer id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
