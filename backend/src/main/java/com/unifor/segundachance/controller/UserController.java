package com.unifor.segundachance.controller;

import com.unifor.segundachance.dto.request.UserRequestDTO;
import com.unifor.segundachance.dto.response.UserResponseDTO;
import com.unifor.segundachance.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO create(
            @Valid @RequestBody UserRequestDTO dto) {

        return userService.create(dto);
    }

    @GetMapping
    public List<UserResponseDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/me")
    public UserResponseDTO findAuthenticatedUser() {
        return userService.findAuthenticatedUser();
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(
            @PathVariable Integer id) {

        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(
            @PathVariable Integer id,
            @Valid @RequestBody UserRequestDTO dto) {

        return userService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Integer id) {

        userService.delete(id);
    }
}