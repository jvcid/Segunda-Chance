package com.unifor.segundachance.controller;

import com.unifor.segundachance.dto.request.AnuncioRequestDTO;
import com.unifor.segundachance.dto.response.AnuncioResponseDTO;
import com.unifor.segundachance.entity.enums.StatusAnuncio;
import com.unifor.segundachance.entity.enums.TipoAnuncio;
import com.unifor.segundachance.service.AnuncioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anuncios")
public class AnuncioController {

    private final AnuncioService anuncioService;

    public AnuncioController(
            AnuncioService anuncioService
    ) {
        this.anuncioService = anuncioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnuncioResponseDTO create(
            @Valid @RequestBody AnuncioRequestDTO dto
    ) {
        return anuncioService.create(dto);
    }

    @GetMapping
    public Page<AnuncioResponseDTO> findAll(
            @RequestParam(required = false)
            String titulo,

            @RequestParam(required = false)
            Integer categoryId,

            @RequestParam(required = false)
            TipoAnuncio tipo,

            @RequestParam(required = false)
            StatusAnuncio status,

            @PageableDefault(
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            )
            Pageable pageable
    ) {
        return anuncioService.findAll(
                titulo,
                categoryId,
                tipo,
                status,
                pageable
        );
    }

    @GetMapping("/me")
    public List<AnuncioResponseDTO> findMyAnuncios() {
        return anuncioService.findMyAnuncios();
    }

    @GetMapping("/{id}")
    public AnuncioResponseDTO findById(
            @PathVariable Integer id
    ) {
        return anuncioService.findById(id);
    }

    @PutMapping("/{id}")
    public AnuncioResponseDTO update(
            @PathVariable Integer id,
            @Valid @RequestBody AnuncioRequestDTO dto
    ) {
        return anuncioService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Integer id
    ) {
        anuncioService.delete(id);
    }
}