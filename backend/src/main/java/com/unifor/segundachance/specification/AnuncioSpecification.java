package com.unifor.segundachance.specification;

import com.unifor.segundachance.entity.Anuncio;
import com.unifor.segundachance.entity.enums.StatusAnuncio;
import com.unifor.segundachance.entity.enums.TipoAnuncio;
import org.springframework.data.jpa.domain.Specification;

public final class AnuncioSpecification {

    private AnuncioSpecification() {
    }

    public static Specification<Anuncio> tituloContem(String titulo) {
        return (root, query, criteriaBuilder) -> {
            if (titulo == null || titulo.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("titulo")),
                    "%" + titulo.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Anuncio> possuiCategoria(
            Integer categoryId
    ) {
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(
                    root.get("category").get("id"),
                    categoryId
            );
        };
    }

    public static Specification<Anuncio> possuiTipo(
            TipoAnuncio tipo
    ) {
        return (root, query, criteriaBuilder) -> {
            if (tipo == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(
                    root.get("tipo"),
                    tipo
            );
        };
    }

    public static Specification<Anuncio> possuiStatus(
            StatusAnuncio status
    ) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(
                    root.get("status"),
                    status
            );
        };
    }
}