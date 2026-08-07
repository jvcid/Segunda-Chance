package com.unifor.segundachance.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SolicitacaoRequestDTO {

    @NotNull(message = "O anúncio é obrigatório")
    private Integer anuncioId;

    @NotBlank(message = "A mensagem é obrigatória")
    @Size(max = 500, message = "A mensagem deve ter no máximo 500 caracteres")
    private String mensagem;

    public SolicitacaoRequestDTO() {
    }

    public Integer getAnuncioId() {
        return anuncioId;
    }

    public void setAnuncioId(Integer anuncioId) {
        this.anuncioId = anuncioId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}