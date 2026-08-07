package com.unifor.segundachance.service;

import com.unifor.segundachance.dto.request.UserRequestDTO;
import com.unifor.segundachance.dto.response.UserResponseDTO;
import com.unifor.segundachance.entity.Role;
import com.unifor.segundachance.entity.User;
import com.unifor.segundachance.exception.DuplicateResourceException;
import com.unifor.segundachance.exception.ResourceNotFoundException;
import com.unifor.segundachance.repository.RoleRepository;
import com.unifor.segundachance.repository.UserRepository;
import com.unifor.segundachance.security.AuthenticatedUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticatedUserService authenticatedUserService;

    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            AuthenticatedUserService authenticatedUserService
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticatedUserService = authenticatedUserService;
    }

    @Transactional
    public UserResponseDTO create(UserRequestDTO dto) {
        validarEmailDuplicado(dto.getEmail(), null);

        Role role = buscarRole(dto.getRoleId());

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(role);

        User saved = userRepository.save(user);

        return toResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findById(Integer id) {
        return toResponseDTO(buscarUsuario(id));
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findAuthenticatedUser() {
        User authenticatedUser =
                authenticatedUserService.getAuthenticatedUser();

        return toResponseDTO(authenticatedUser);
    }

    @Transactional
    public UserResponseDTO update(Integer id, UserRequestDTO dto) {
        User user = buscarUsuario(id);

        validarEmailDuplicado(dto.getEmail(), id);

        Role role = buscarRole(dto.getRoleId());

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(role);

        User updated = userRepository.save(user);

        return toResponseDTO(updated);
    }

    @Transactional
    public void delete(Integer id) {
        User user = buscarUsuario(id);
        userRepository.delete(user);
    }

    private User buscarUsuario(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuário não encontrado"
                        )
                );
    }

    private Role buscarRole(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Perfil não encontrado"
                        )
                );
    }

    private void validarEmailDuplicado(
            String email,
            Integer userIdAtual
    ) {
        userRepository.findByEmail(email)
                .ifPresent(userExistente -> {
                    boolean pertenceAoMesmoUsuario =
                            userIdAtual != null
                                    && userExistente.getId()
                                    .equals(userIdAtual);

                    if (!pertenceAoMesmoUsuario) {
                        throw new DuplicateResourceException(
                                "Já existe um usuário com este e-mail"
                        );
                    }
                });
    }

    private UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().getId(),
                user.getRole().getNome()
        );
    }
}