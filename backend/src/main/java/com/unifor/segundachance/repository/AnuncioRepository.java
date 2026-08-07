package com.unifor.segundachance.repository;

import com.unifor.segundachance.entity.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AnuncioRepository
        extends JpaRepository<Anuncio, Integer>,
        JpaSpecificationExecutor<Anuncio> {

    List<Anuncio> findByUserIdOrderByCreatedAtDesc(Integer userId);
}