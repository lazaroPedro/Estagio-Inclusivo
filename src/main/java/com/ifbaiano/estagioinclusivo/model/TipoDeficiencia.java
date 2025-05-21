package com.ifbaiano.estagioinclusivo.model;

import com.ifbaiano.estagioinclusivo.model.enums.TipoDeficienciaEnum;
import com.ifbaiano.estagioinclusivo.utils.validation.ErroCampo;
import com.ifbaiano.estagioinclusivo.utils.validation.ValidationException;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.MaxLength;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotBlank;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TipoDeficiencia {
    private int id;
    @NotBlank
    private String nome;
    @NotBlank
    @MaxLength(1000)
    private String descricao;
    @NotNull
    private TipoDeficienciaEnum tipo;
    @MaxLength(1000)
    @NotBlank
    private String tipoApoio;
    @NotNull
    private Candidato candidato;




    public TipoDeficiencia() {}

    public TipoDeficiencia(int id, String nome, String descricao, TipoDeficienciaEnum tipo, String tipoApoio, Candidato candidato) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.tipoApoio = tipoApoio;
        this.candidato = candidato;
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoDeficienciaEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeficienciaEnum tipo) {
        this.tipo = tipo;
    }


    public String getTipoApoio() {
        return tipoApoio;
    }

    public void setTipoApoio(String tipoApoio) {
        this.tipoApoio = tipoApoio;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

}
