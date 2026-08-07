package com.unifor.segundachance.dto.response;

import com.unifor.segundachance.entity.enums.StatusSolicitacao;

import java.time.LocalDateTime;

public class SolicitacaoResponseDTO {

    private Integer id;
    private String mensagem;
    private StatusSolicitacao status;
    private LocalDateTime createdAt;
    private Integer anuncioId;
    private String anuncioTitulo;
    private Integer userId;
    private String userName;

    public SolicitacaoResponseDTO() {
    }

    public SolicitacaoResponseDTO(
            Integer id,
            String mensagem,
            StatusSolicitacao status,
            LocalDateTime createdAt,
            Integer anuncioId,
            String anuncioTitulo,
            Integer userId,
            String userName
    ) {
        this.id = id;
        this.mensagem = mensagem;
        this.status = status;
        this.createdAt = createdAt;
        this.anuncioId = anuncioId;
        this.anuncioTitulo = anuncioTitulo;
        this.userId = userId;
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getAnuncioId() {
        return anuncioId;
    }

    public void setAnuncioId(Integer anuncioId) {
        this.anuncioId = anuncioId;
    }

    public String getAnuncioTitulo() {
        return anuncioTitulo;
    }

    public void setAnuncioTitulo(String anuncioTitulo) {
        this.anuncioTitulo = anuncioTitulo;
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