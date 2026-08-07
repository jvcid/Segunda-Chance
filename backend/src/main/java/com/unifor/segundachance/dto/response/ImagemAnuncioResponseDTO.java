package com.unifor.segundachance.dto.response;

public class ImagemAnuncioResponseDTO {

    private Integer id;
    private String url;
    private Integer ordem;
    private Integer anuncioId;
    private String anuncioTitulo;

    public ImagemAnuncioResponseDTO() {
    }

    public ImagemAnuncioResponseDTO(
            Integer id,
            String url,
            Integer ordem,
            Integer anuncioId,
            String anuncioTitulo
    ) {
        this.id = id;
        this.url = url;
        this.ordem = ordem;
        this.anuncioId = anuncioId;
        this.anuncioTitulo = anuncioTitulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}