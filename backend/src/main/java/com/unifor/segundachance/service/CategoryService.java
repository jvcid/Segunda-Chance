package com.unifor.segundachance.service;

import com.unifor.segundachance.dto.request.CategoryRequestDTO;
import com.unifor.segundachance.dto.response.CategoryResponseDTO;
import com.unifor.segundachance.entity.Category;
import com.unifor.segundachance.exception.DuplicateResourceException;
import com.unifor.segundachance.exception.ResourceNotFoundException;
import com.unifor.segundachance.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDTO create(CategoryRequestDTO dto) {
        categoryRepository.findByName(dto.getName())
                .ifPresent(c -> {
                    throw new DuplicateResourceException("Categoria já existe");
                });

        Category category = new Category();
        category.setName(dto.getName());

        Category saved = categoryRepository.save(category);

        return new CategoryResponseDTO(saved.getId(), saved.getName());
    }

    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(c -> new CategoryResponseDTO(c.getId(), c.getName()))
                .toList();
    }

    public CategoryResponseDTO findById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        return new CategoryResponseDTO(category.getId(), category.getName());
    }

    public CategoryResponseDTO update(Integer id, CategoryRequestDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        categoryRepository.findByName(dto.getName())
                .filter(c -> !c.getId().equals(id))
                .ifPresent(c -> {
                    throw new DuplicateResourceException("Categoria já existe");
                });

        category.setName(dto.getName());

        Category updated = categoryRepository.save(category);

        return new CategoryResponseDTO(updated.getId(), updated.getName());
    }

    public void delete(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrada");
        }

        categoryRepository.deleteById(id);
    }
}