package com.unifor.segundachance.controller;

import com.unifor.segundachance.dto.request.SolicitacaoRequestDTO;
import com.unifor.segundachance.dto.response.SolicitacaoResponseDTO;
import com.unifor.segundachance.service.SolicitacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;

    public SolicitacaoController(
            SolicitacaoService solicitacaoService
    ) {
        this.solicitacaoService = solicitacaoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitacaoResponseDTO create(
            @Valid @RequestBody SolicitacaoRequestDTO dto
    ) {
        return solicitacaoService.create(dto);
    }

    @GetMapping
    public List<SolicitacaoResponseDTO> findAll() {
        return solicitacaoService.findAll();
    }

    @GetMapping("/enviadas")
    public List<SolicitacaoResponseDTO> findEnviadas() {
        return solicitacaoService.findEnviadas();
    }

    @GetMapping("/recebidas")
    public List<SolicitacaoResponseDTO> findRecebidas() {
        return solicitacaoService.findRecebidas();
    }

    @GetMapping("/{id}")
    public SolicitacaoResponseDTO findById(
            @PathVariable Integer id
    ) {
        return solicitacaoService.findById(id);
    }

    @PutMapping("/{id}")
    public SolicitacaoResponseDTO update(
            @PathVariable Integer id,
            @Valid @RequestBody SolicitacaoRequestDTO dto
    ) {
        return solicitacaoService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Integer id
    ) {
        solicitacaoService.delete(id);
    }

    @PatchMapping("/{id}/aprovar")
    public SolicitacaoResponseDTO aprovar(
            @PathVariable Integer id
    ) {
        return solicitacaoService.aprovar(id);
    }

    @PatchMapping("/{id}/rejeitar")
    public SolicitacaoResponseDTO rejeitar(
            @PathVariable Integer id
    ) {
        return solicitacaoService.rejeitar(id);
    }
}