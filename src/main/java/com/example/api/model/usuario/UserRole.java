package com.example.api.model.usuario;

public enum UserRole {

//    ADMIN("admin"),
//
//    USER("user");
    ADMIN("ROLE_ADMIN"),
    MEDICO("ROLE_MEDICO"),
    ATENDENTE("ROLE_ATENDENTE"),
    PACIENTE("ROLE_PACIENTE");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
