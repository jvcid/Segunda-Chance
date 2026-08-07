package com.unifor.segundachance.repository;

import com.unifor.segundachance.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByNome(String nome);
}