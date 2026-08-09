package com.unifor.segundachance.controller;

import com.unifor.segundachance.dto.response.AdminUserResponseDTO;
import com.unifor.segundachance.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public List<AdminUserResponseDTO> findAllUsers() {
        return adminService.findAllUsers();
    }

    @PatchMapping("/users/{id}/ban")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void banUser(@PathVariable Integer id) {
        adminService.banUser(id);
    }

    @PatchMapping("/users/{id}/unban")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unbanUser(@PathVariable Integer id) {
        adminService.unbanUser(id);
    }

    @DeleteMapping("/anuncios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnuncio(@PathVariable Integer id) {
        adminService.deleteAnuncio(id);
    }
}