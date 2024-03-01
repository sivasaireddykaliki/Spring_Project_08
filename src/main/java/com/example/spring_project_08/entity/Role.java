package com.example.spring_project_08.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    EMPLOYEE(
            Set.of(
               Permission.EMPLOYEE_READ,
               Permission.EMPLOYEE_CREATE,
               Permission.EMPLOYEE_UPDATE,
               Permission.EMPLOYEE_DELETE
            )
    ),
    PRINCIPAL(
            Set.of(
                    Permission.PRINCIPAL_READ,
                    Permission.PRINCIPAL_CREATE,
                    Permission.PRINCIPAL_UPDATE,
                    Permission.PRINCIPAL_DELETE
            )
    ),
    MANAGER(
            Set.of(
                    Permission.MANAGER_CREATE,
                    Permission.MANAGER_UPDATE,
                    Permission.MANAGER_DELETE
            )
    ),
    ADMIN(
            Set.of(
                    Permission.ADMIN_READ,
                    Permission.ADMIN_CREATE,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_DELETE
            )
    ),

    ALBUM(
            Set.of(
                    Permission.ALBUM_READ
            )
    );


    private final Set<Permission> permissions;

    private Role(Set<Permission> permissions) {
        this.permissions=permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                        .collect(Collectors.toList());
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return simpleGrantedAuthorities;
    }
}
