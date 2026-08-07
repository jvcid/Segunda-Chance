package com.unifor.segundachance.controller;

import com.unifor.segundachance.dto.request.ImagemAnuncioRequestDTO;
import com.unifor.segundachance.dto.response.ImagemAnuncioResponseDTO;
import com.unifor.segundachance.service.ImagemAnuncioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/imagens-anuncio")
public class ImagemAnuncioController {

    private final ImagemAnuncioService imagemAnuncioService;

    public ImagemAnuncioController(
            ImagemAnuncioService imagemAnuncioService
    ) {
        this.imagemAnuncioService = imagemAnuncioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ImagemAnuncioResponseDTO create(
            @Valid @RequestBody ImagemAnuncioRequestDTO dto
    ) {
        return imagemAnuncioService.create(dto);
    }

    @GetMapping
    public List<ImagemAnuncioResponseDTO> findAll() {
        return imagemAnuncioService.findAll();
    }

    @GetMapping("/{id}")
    public ImagemAnuncioResponseDTO findById(
            @PathVariable Integer id
    ) {
        return imagemAnuncioService.findById(id);
    }

    @GetMapping("/anuncio/{anuncioId}")
    public List<ImagemAnuncioResponseDTO> findByAnuncioId(
            @PathVariable Integer anuncioId
    ) {
        return imagemAnuncioService.findByAnuncioId(anuncioId);
    }

    @PutMapping("/{id}")
    public ImagemAnuncioResponseDTO update(
            @PathVariable Integer id,
            @Valid @RequestBody ImagemAnuncioRequestDTO dto
    ) {
        return imagemAnuncioService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        imagemAnuncioService.delete(id);
    }
}