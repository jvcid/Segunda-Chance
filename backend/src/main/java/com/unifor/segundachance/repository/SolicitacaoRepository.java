package com.unifor.segundachance.repository;

import com.unifor.segundachance.entity.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoRepository
        extends JpaRepository<Solicitacao, Integer> {

    boolean existsByAnuncioIdAndUserId(
            Integer anuncioId,
            Integer userId
    );

    List<Solicitacao> findByAnuncioId(Integer anuncioId);

    List<Solicitacao> findByUserIdOrderByCreatedAtDesc(
            Integer userId
    );

    List<Solicitacao> findByAnuncioUserIdOrderByCreatedAtDesc(
            Integer userId
    );
}