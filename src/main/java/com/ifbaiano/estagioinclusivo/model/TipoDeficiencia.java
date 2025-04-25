package com.ifbaiano.estagioinclusivo.model;

import com.ifbaiano.estagioinclusivo.model.enums.TipoDeficienciaEnum;
import com.ifbaiano.estagioinclusivo.utils.validation.ErroCampo;
import com.ifbaiano.estagioinclusivo.utils.validation.ValidationException;
import com.ifbaiano.estagioinclusivo.utils.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class TipoDeficiencia {
    private Long id;
    private String nome;
    private String descricao;
    private TipoDeficienciaEnum tipo;
    private String cid;
    private String tipoApoio;
    private Candidato candidato;

    public void validar() {
        List<ErroCampo> erros = new ArrayList<>();

        Validator.notBlank(nome, "nome", erros);
        Validator.notBlank(descricao, "descricao", erros);
        Validator.notNull(tipo, "tipo", erros);
        Validator.notBlank(cid, "cid", erros);
        // Validator.notNull(tipoApoio, "tipoApoio", erros); aceita null
        Validator.notNull(candidato, "candidato", erros);

        if (!erros.isEmpty()) {
            throw new ValidationException(erros);
        }
    }

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

    public TipoDeficienciaEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeficienciaEnum tipo) {
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

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
    public TipoDeficiencia() {}

    public TipoDeficiencia(Long id, String nome, String descricao, TipoDeficienciaEnum tipo, String cid, String tipoApoio, Candidato candidato) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.cid = cid;
        this.tipoApoio = tipoApoio;
        this.candidato = candidato;
    }
}
