package com.unifor.segundachance.dto.response;

public class AdminUserResponseDTO {

    private Integer id;
    private String name;
    private String email;
    private String role;
    private boolean banned;

    public AdminUserResponseDTO() {
    }

    public AdminUserResponseDTO(
            Integer id,
            String name,
            String email,
            String role,
            boolean banned
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.banned = banned;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public boolean isBanned() {
        return banned;
    }
}