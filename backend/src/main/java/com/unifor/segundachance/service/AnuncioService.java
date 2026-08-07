package com.unifor.segundachance.service;

import com.unifor.segundachance.dto.request.AnuncioRequestDTO;
import com.unifor.segundachance.dto.response.AnuncioResponseDTO;
import com.unifor.segundachance.entity.Anuncio;
import com.unifor.segundachance.entity.Category;
import com.unifor.segundachance.entity.User;
import com.unifor.segundachance.entity.enums.StatusAnuncio;
import com.unifor.segundachance.entity.enums.TipoAnuncio;
import com.unifor.segundachance.exception.BusinessException;
import com.unifor.segundachance.exception.ForbiddenOperationException;
import com.unifor.segundachance.exception.ResourceNotFoundException;
import com.unifor.segundachance.repository.AnuncioRepository;
import com.unifor.segundachance.repository.CategoryRepository;
import com.unifor.segundachance.repository.ImagemAnuncioRepository;
import com.unifor.segundachance.security.AuthenticatedUserService;
import com.unifor.segundachance.specification.AnuncioSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnuncioService {

    private final AnuncioRepository anuncioRepository;
    private final CategoryRepository categoryRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final ImagemAnuncioRepository imagemAnuncioRepository;

    public AnuncioService(
            AnuncioRepository anuncioRepository,
            CategoryRepository categoryRepository,
            AuthenticatedUserService authenticatedUserService,
            ImagemAnuncioRepository imagemAnuncioRepository
    ) {
        this.anuncioRepository = anuncioRepository;
        this.categoryRepository = categoryRepository;
        this.authenticatedUserService = authenticatedUserService;
        this.imagemAnuncioRepository = imagemAnuncioRepository;
    }

    @Transactional
    public AnuncioResponseDTO create(AnuncioRequestDTO dto) {
        Category category = buscarCategoria(dto.getCategoryId());

        User authenticatedUser =
                authenticatedUserService.getAuthenticatedUser();

        Anuncio anuncio = new Anuncio();
        anuncio.setTitulo(dto.getTitulo());
        anuncio.setDescricao(dto.getDescricao());
        anuncio.setTipo(dto.getTipo());

        definirPreco(anuncio, dto);

        anuncio.setCategory(category);
        anuncio.setUser(authenticatedUser);
        anuncio.setStatus(StatusAnuncio.DISPONIVEL);
        anuncio.setCreatedAt(LocalDateTime.now());

        Anuncio saved = anuncioRepository.save(anuncio);

        return toResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public Page<AnuncioResponseDTO> findAll(
            String titulo,
            Integer categoryId,
            TipoAnuncio tipo,
            StatusAnuncio status,
            Pageable pageable
    ) {
        Specification<Anuncio> specification =
                Specification
                        .where(
                                AnuncioSpecification.tituloContem(titulo)
                        )
                        .and(
                                AnuncioSpecification.possuiCategoria(
                                        categoryId
                                )
                        )
                        .and(
                                AnuncioSpecification.possuiTipo(tipo)
                        )
                        .and(
                                AnuncioSpecification.possuiStatus(status)
                        );

        return anuncioRepository
                .findAll(specification, pageable)
                .map(this::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public List<AnuncioResponseDTO> findMyAnuncios() {
        User authenticatedUser =
                authenticatedUserService.getAuthenticatedUser();

        return anuncioRepository
                .findByUserIdOrderByCreatedAtDesc(
                        authenticatedUser.getId()
                )
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public AnuncioResponseDTO findById(Integer id) {
        return toResponseDTO(buscarAnuncio(id));
    }

    @Transactional
    public AnuncioResponseDTO update(
            Integer id,
            AnuncioRequestDTO dto
    ) {
        Anuncio anuncio = buscarAnuncio(id);

        User authenticatedUser =
                authenticatedUserService.getAuthenticatedUser();

        validarProprietario(anuncio, authenticatedUser);

        Category category =
                buscarCategoria(dto.getCategoryId());

        anuncio.setTitulo(dto.getTitulo());
        anuncio.setDescricao(dto.getDescricao());
        anuncio.setTipo(dto.getTipo());

        definirPreco(anuncio, dto);

        anuncio.setCategory(category);

        Anuncio updated =
                anuncioRepository.save(anuncio);

        return toResponseDTO(updated);
    }

    @Transactional
    public void delete(Integer id) {
        Anuncio anuncio = buscarAnuncio(id);

        User authenticatedUser =
                authenticatedUserService.getAuthenticatedUser();

        validarProprietario(anuncio, authenticatedUser);

        anuncioRepository.delete(anuncio);
    }

    private void definirPreco(
            Anuncio anuncio,
            AnuncioRequestDTO dto
    ) {
        if (dto.getTipo() == TipoAnuncio.DOACAO) {
            anuncio.setPreco(null);
            return;
        }

        if (dto.getPreco() == null) {
            throw new BusinessException(
                    "Preço é obrigatório para anúncios de venda"
            );
        }

        anuncio.setPreco(dto.getPreco());
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
                    "Você não tem permissão para alterar este anúncio"
            );
        }
    }

    private Anuncio buscarAnuncio(Integer id) {
        return anuncioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Anúncio não encontrado"
                        )
                );
    }

    private Category buscarCategoria(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Categoria não encontrada"
                        )
                );
    }

    private AnuncioResponseDTO toResponseDTO(
            Anuncio anuncio
    ) {
        String imagemPrincipalUrl =
                imagemAnuncioRepository
                        .findFirstByAnuncioIdOrderByOrdemAsc(
                                anuncio.getId()
                        )
                        .map(imagem -> imagem.getUrl())
                        .orElse(null);

        return new AnuncioResponseDTO(
                anuncio.getId(),
                anuncio.getTitulo(),
                anuncio.getDescricao(),
                anuncio.getTipo(),
                anuncio.getPreco(),
                anuncio.getStatus(),
                anuncio.getCreatedAt(),
                anuncio.getCategory().getId(),
                anuncio.getCategory().getName(),
                anuncio.getUser().getId(),
                anuncio.getUser().getName(),
                imagemPrincipalUrl
        );
    }
}