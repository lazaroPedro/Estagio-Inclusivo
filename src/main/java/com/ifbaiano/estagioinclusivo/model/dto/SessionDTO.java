package com.ifbaiano.estagioinclusivo.model.dto;

import com.ifbaiano.estagioinclusivo.model.enums.TipoUsuario;

public class SessionDTO {
    private int id;
    private String nome;
    private TipoUsuario tipoUsuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public SessionDTO() {}
    public SessionDTO(int id, String nome, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
    }
}
