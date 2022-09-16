package com.lex.car_rental_spring.security.roles;

import com.google.common.collect.Sets;
import com.lex.car_rental_spring.security.permissions.UserPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    USER(Sets.newHashSet( UserPermission.CAR_RENT, UserPermission.CAR_RETURN)),
    ADMIN(Sets.newHashSet(UserPermission.CAR_WRITE, UserPermission.CAR_READ, UserPermission.LOCATION_WRITE, UserPermission.LOCATION_READ,UserPermission.HISTORY_READ));



    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }
    public Set<UserPermission> getPermissions(){return permissions;}
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
