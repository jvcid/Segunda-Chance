package com.unifor.segundachance.controller;

import com.unifor.segundachance.dto.request.CategoryRequestDTO;
import com.unifor.segundachance.dto.response.CategoryResponseDTO;
import com.unifor.segundachance.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDTO create(@Valid @RequestBody CategoryRequestDTO dto) {
        return categoryService.create(dto);
    }

    @GetMapping
    public List<CategoryResponseDTO> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO findById(@PathVariable Integer id) {
        return categoryService.findById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO update(@PathVariable Integer id,
                                      @Valid @RequestBody CategoryRequestDTO dto) {
        return categoryService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        categoryService.delete(id);
    }
}