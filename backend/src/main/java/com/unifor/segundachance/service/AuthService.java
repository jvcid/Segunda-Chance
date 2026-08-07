package com.unifor.segundachance.service;

import com.unifor.segundachance.dto.request.LoginRequestDTO;
import com.unifor.segundachance.dto.request.RegisterRequestDTO;
import com.unifor.segundachance.dto.response.LoginResponseDTO;
import com.unifor.segundachance.dto.response.UserResponseDTO;
import com.unifor.segundachance.entity.Role;
import com.unifor.segundachance.entity.User;
import com.unifor.segundachance.exception.DuplicateResourceException;
import com.unifor.segundachance.exception.ResourceNotFoundException;
import com.unifor.segundachance.repository.RoleRepository;
import com.unifor.segundachance.repository.UserRepository;
import com.unifor.segundachance.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponseDTO register(RegisterRequestDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateResourceException(
                    "Já existe um usuário com este e-mail"
            );
        }

        Role userRole = roleRepository.findByNome("USER")
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Perfil USER não encontrado"
                        )
                );

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(userRole);

        User saved = userRepository.save(user);

        return new UserResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getRole().getId(),
                saved.getRole().getNome()
        );
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        return new LoginResponseDTO(token);
    }
}