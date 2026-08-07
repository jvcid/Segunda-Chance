package com.unifor.segundachance.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ImagemAnuncioRequestDTO {

    @NotNull(message = "O anúncio é obrigatório")
    private Integer anuncioId;

    @NotBlank(message = "A URL da imagem é obrigatória")
    @Size(max = 500, message = "A URL deve ter no máximo 500 caracteres")
    private String url;

    @NotNull(message = "A ordem da imagem é obrigatória")
    @Min(value = 0, message = "A ordem não pode ser negativa")
    private Integer ordem;

    public ImagemAnuncioRequestDTO() {
    }

    public Integer getAnuncioId() {
        return anuncioId;
    }

    public void setAnuncioId(Integer anuncioId) {
        this.anuncioId = anuncioId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }
}