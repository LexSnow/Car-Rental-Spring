package com.lex.car_rental_spring.security.permissions;

public enum UserPermission {
    CAR_READ("car:read"),
    CAR_WRITE("car:write"),
    CAR_RENT("car:rent"),
    CAR_RETURN("car:return"),
    HISTORY_WRITE("history:write"),
    HISTORY_READ("history:read"),
    LOCATION_WRITE("location:write"),
    LOCATION_READ("location:read");



    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
