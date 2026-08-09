package com.unifor.segundachance.service;

import com.unifor.segundachance.dto.response.AdminUserResponseDTO;
import com.unifor.segundachance.entity.Anuncio;
import com.unifor.segundachance.entity.User;
import com.unifor.segundachance.exception.BusinessException;
import com.unifor.segundachance.exception.ResourceNotFoundException;
import com.unifor.segundachance.repository.AnuncioRepository;
import com.unifor.segundachance.repository.UserRepository;
import com.unifor.segundachance.security.AuthenticatedUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final AnuncioRepository anuncioRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public AdminService(
            UserRepository userRepository,
            AnuncioRepository anuncioRepository,
            AuthenticatedUserService authenticatedUserService
    ) {
        this.userRepository = userRepository;
        this.anuncioRepository = anuncioRepository;
        this.authenticatedUserService = authenticatedUserService;
    }

    @Transactional(readOnly = true)
    public List<AdminUserResponseDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toAdminResponseDTO)
                .toList();
    }

    @Transactional
    public void banUser(Integer id) {
        User user = buscarUsuario(id);

        User admin =
                authenticatedUserService.getAuthenticatedUser();

        if (user.getId().equals(admin.getId())) {
            throw new BusinessException(
                    "Um administrador não pode banir a própria conta"
            );
        }

        if ("ADMIN".equals(user.getRole().getNome())) {
            throw new BusinessException(
                    "Não é permitido banir outro administrador"
            );
        }

        // Remove todos os anúncios publicados pelo usuário
        List<Anuncio> anunciosDoUsuario =
                anuncioRepository.findByUserIdOrderByCreatedAtDesc(
                        user.getId()
                );

        anuncioRepository.deleteAll(anunciosDoUsuario);

        // Bloqueia a conta
        user.setBanned(true);

        userRepository.save(user);
    }

    @Transactional
    public void unbanUser(Integer id) {
        User user = buscarUsuario(id);

        user.setBanned(false);

        userRepository.save(user);
    }

    @Transactional
    public void deleteAnuncio(Integer id) {
        Anuncio anuncio = anuncioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Anúncio não encontrado"
                        )
                );

        anuncioRepository.delete(anuncio);
    }

    private User buscarUsuario(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuário não encontrado"
                        )
                );
    }

    private AdminUserResponseDTO toAdminResponseDTO(User user) {
        return new AdminUserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().getNome(),
                user.isBanned()
        );
    }
}