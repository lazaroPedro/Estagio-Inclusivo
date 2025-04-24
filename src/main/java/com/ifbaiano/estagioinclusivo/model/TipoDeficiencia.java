package com.ifbaiano.estagioinclusivo.model;

import java.time.LocalDateTime;

public class TipoDeficiencia {
    private Long id;
    private String nome;
    private String descricao;
    private String tipo;
    private String cid;
    private String tipoApoio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTipoApoio() {
        return tipoApoio;
    }

    public void setTipoApoio(String tipoApoio) {
        this.tipoApoio = tipoApoio;
    }



    public TipoDeficiencia(String nome, String descricao, String tipo, String cid, String tipoApoio) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.cid = cid;
        this.tipoApoio = tipoApoio;
    }

    public TipoDeficiencia(Long id, String nome, String descricao, String tipo, String cid, String tipoApoio) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.cid = cid;
        this.tipoApoio = tipoApoio;
    }
}
