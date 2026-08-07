package com.unifor.segundachance.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "imagens_anuncio")
public class ImagemAnuncio {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imagem_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anuncio_id", nullable = false)
    private Anuncio anuncio;

    @Column(name = "url", nullable = false, length = 500)
    private String url;

    @Column(name = "ordem", nullable = false)
    private Integer ordem;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public ImagemAnuncio() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}