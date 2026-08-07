
package com.unifor.segundachance.service;

import com.unifor.segundachance.dto.request.ImagemAnuncioRequestDTO;
import com.unifor.segundachance.dto.response.ImagemAnuncioResponseDTO;
import com.unifor.segundachance.entity.Anuncio;
import com.unifor.segundachance.entity.ImagemAnuncio;
import com.unifor.segundachance.entity.User;
import com.unifor.segundachance.exception.ForbiddenOperationException;
import com.unifor.segundachance.exception.ResourceNotFoundException;
import com.unifor.segundachance.repository.AnuncioRepository;
import com.unifor.segundachance.repository.ImagemAnuncioRepository;
import com.unifor.segundachance.security.AuthenticatedUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImagemAnuncioService {

    private final ImagemAnuncioRepository imagemAnuncioRepository;
    private final AnuncioRepository anuncioRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public ImagemAnuncioService(
            ImagemAnuncioRepository imagemAnuncioRepository,
            AnuncioRepository anuncioRepository,
            AuthenticatedUserService authenticatedUserService
    ) {
        this.imagemAnuncioRepository = imagemAnuncioRepository;
        this.anuncioRepository = anuncioRepository;
        this.authenticatedUserService = authenticatedUserService;
    }

    @Transactional
    public ImagemAnuncioResponseDTO create(
            ImagemAnuncioRequestDTO dto
    ) {
        Anuncio anuncio = buscarAnuncio(dto.getAnuncioId());
        User authenticatedUser =
                authenticatedUserService.getAuthenticatedUser();

        validarProprietario(anuncio, authenticatedUser);

        ImagemAnuncio imagem = new ImagemAnuncio();
        imagem.setUrl(dto.getUrl());
        imagem.setOrdem(dto.getOrdem());
        imagem.setAnuncio(anuncio);
        imagem.setCreatedAt(LocalDateTime.now());

        ImagemAnuncio saved =
                imagemAnuncioRepository.save(imagem);

        return toResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<ImagemAnuncioResponseDTO> findAll() {
        return imagemAnuncioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ImagemAnuncioResponseDTO findById(Integer id) {
        return toResponseDTO(buscarImagem(id));
    }

    @Transactional(readOnly = true)
    public List<ImagemAnuncioResponseDTO> findByAnuncioId(
            Integer anuncioId
    ) {
        buscarAnuncio(anuncioId);

        return imagemAnuncioRepository
                .findByAnuncioIdOrderByOrdemAsc(anuncioId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional
    public ImagemAnuncioResponseDTO update(
            Integer id,
            ImagemAnuncioRequestDTO dto
    ) {
        ImagemAnuncio imagem = buscarImagem(id);
        User authenticatedUser =
                authenticatedUserService.getAuthenticatedUser();

        validarProprietario(
                imagem.getAnuncio(),
                authenticatedUser
        );

        Anuncio anuncioDestino =
                buscarAnuncio(dto.getAnuncioId());

        validarProprietario(
                anuncioDestino,
                authenticatedUser
        );

        imagem.setUrl(dto.getUrl());
        imagem.setOrdem(dto.getOrdem());
        imagem.setAnuncio(anuncioDestino);

        ImagemAnuncio updated =
                imagemAnuncioRepository.save(imagem);

        return toResponseDTO(updated);
    }

    @Transactional
    public void delete(Integer id) {
        ImagemAnuncio imagem = buscarImagem(id);
        User authenticatedUser =
                authenticatedUserService.getAuthenticatedUser();

        validarProprietario(
                imagem.getAnuncio(),
                authenticatedUser
        );

        imagemAnuncioRepository.delete(imagem);
    }

    private void validarProprietario(
            Anuncio anuncio,
            User authenticatedUser
    ) {
        boolean isOwner = anuncio.getUser()
                .getId()
                .equals(authenticatedUser.getId());

        if (!isOwner) {
            throw new ForbiddenOperationException(
                    "Somente o proprietário do anúncio pode gerenciar suas imagens"
            );
        }
    }

    private ImagemAnuncio buscarImagem(Integer id) {
        return imagemAnuncioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Imagem não encontrada"
                        )
                );
    }

    private Anuncio buscarAnuncio(Integer id) {
        return anuncioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Anúncio não encontrado"
                        )
                );
    }

    private ImagemAnuncioResponseDTO toResponseDTO(
            ImagemAnuncio imagem
    ) {
        return new ImagemAnuncioResponseDTO(
                imagem.getId(),
                imagem.getUrl(),
                imagem.getOrdem(),
                imagem.getAnuncio().getId(),
                imagem.getAnuncio().getTitulo()
        );
    }
}