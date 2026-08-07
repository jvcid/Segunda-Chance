package com.unifor.segundachance.dto.response;

public class UserResponseDTO {

    private Integer id;
    private String name;
    private String email;
    private Integer roleId;
    private String roleName;

    public UserResponseDTO() {
    }

    public UserResponseDTO(
            Integer id,
            String name,
            String email,
            Integer roleId,
            String roleName
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}