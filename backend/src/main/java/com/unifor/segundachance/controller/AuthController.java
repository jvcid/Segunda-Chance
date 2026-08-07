package com.unifor.segundachance.controller;

import com.unifor.segundachance.dto.request.LoginRequestDTO;
import com.unifor.segundachance.dto.request.RegisterRequestDTO;
import com.unifor.segundachance.dto.response.LoginResponseDTO;
import com.unifor.segundachance.dto.response.UserResponseDTO;
import com.unifor.segundachance.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(
            @Valid @RequestBody LoginRequestDTO dto
    ) {
        return authService.login(dto);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO register(
            @Valid @RequestBody RegisterRequestDTO dto
    ) {
        return authService.register(dto);
    }
}