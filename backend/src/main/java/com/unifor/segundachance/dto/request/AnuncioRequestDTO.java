package com.unifor.segundachance.dto.request;

import com.unifor.segundachance.entity.enums.TipoAnuncio;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class AnuncioRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotNull(message = "O tipo do anúncio é obrigatório")
    private TipoAnuncio tipo;

    @DecimalMin(value = "0.0", inclusive = true,
            message = "O preço não pode ser negativo")
    private BigDecimal preco;

    @NotNull(message = "A categoria é obrigatória")
    private Integer categoryId;

    public AnuncioRequestDTO() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoAnuncio getTipo() {
        return tipo;
    }

    public void setTipo(TipoAnuncio tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}