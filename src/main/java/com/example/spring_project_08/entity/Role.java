package com.example.spring_project_08.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public enum Role {
    EMPLOYEE,
    PRINCIPAL,
    MANAGER,
    ADMIN;

    public List<SimpleGrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return simpleGrantedAuthorities;
    }
}
