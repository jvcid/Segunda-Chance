package com.unifor.segundachance.dto.response;

import com.unifor.segundachance.entity.enums.StatusAnuncio;
import com.unifor.segundachance.entity.enums.TipoAnuncio;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AnuncioResponseDTO {

    private Integer id;
    private String titulo;
    private String descricao;
    private TipoAnuncio tipo;
    private BigDecimal preco;
    private StatusAnuncio status;
    private LocalDateTime createdAt;
    private Integer categoryId;
    private String categoryName;
    private Integer userId;
    private String userName;
    private String imagemPrincipalUrl;

    public AnuncioResponseDTO() {
    }

    public AnuncioResponseDTO(
            Integer id,
            String titulo,
            String descricao,
            TipoAnuncio tipo,
            BigDecimal preco,
            StatusAnuncio status,
            LocalDateTime createdAt,
            Integer categoryId,
            String categoryName,
            Integer userId,
            String userName,
            String imagemPrincipalUrl
    ) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.preco = preco;
        this.status = status;
        this.createdAt = createdAt;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.userId = userId;
        this.userName = userName;
        this.imagemPrincipalUrl = imagemPrincipalUrl;
    }

    public String getImagemPrincipalUrl() {
        return imagemPrincipalUrl;
    }

    public void setImagemPrincipalUrl(String imagemPrincipalUrl) {
        this.imagemPrincipalUrl = imagemPrincipalUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public StatusAnuncio getStatus() {
        return status;
    }

    public void setStatus(StatusAnuncio status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}