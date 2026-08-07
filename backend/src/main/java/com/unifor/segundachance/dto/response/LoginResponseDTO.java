package com.unifor.segundachance.dto.response;

public class LoginResponseDTO {

    private String token;
    private String type;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token) {
        this.token = token;
        this.type = "Bearer";
    }

    public LoginResponseDTO(String token, String type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}