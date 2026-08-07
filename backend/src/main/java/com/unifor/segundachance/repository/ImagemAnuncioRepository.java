package com.unifor.segundachance.repository;

import com.unifor.segundachance.entity.ImagemAnuncio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImagemAnuncioRepository
        extends JpaRepository<ImagemAnuncio, Integer> {

    List<ImagemAnuncio> findByAnuncioIdOrderByOrdemAsc(
            Integer anuncioId
    );

    Optional<ImagemAnuncio> findFirstByAnuncioIdOrderByOrdemAsc(
            Integer anuncioId
    );
}