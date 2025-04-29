package com.ifbaiano.estagioinclusivo.model;
import com.ifbaiano.estagioinclusivo.utils.validation.ErroCampo;
import com.ifbaiano.estagioinclusivo.utils.validation.ValidationException;
import com.ifbaiano.estagioinclusivo.utils.validation.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Curso {
    private Long id;
    private String instituicao;
    private String nomeCurso;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Candidato candidato;

    public Curso(long idCurso) {
    }


    public void validar() {
        List<ErroCampo> erros = new ArrayList<>();

        Validator.notBlank(instituicao, "instituicao", erros);
        Validator.notBlank(nomeCurso, "nomeCurso", erros);
        Validator.notBlank(descricao, "descricao", erros);
        Validator.notNull(dataInicio, "dataInicio", erros);
        Validator.notNull(dataFim, "dataFim", erros);
        Validator.notNull(candidato, "candidato", erros);
        Validator.periodoValido(dataInicio, dataFim, "dataInicio", "dataFim", erros);

        if (!erros.isEmpty()) {
            throw new ValidationException(erros);
        }

    }

    public Curso() {}

    public Curso(Long id, String instituicao, String nomeCurso, String descricao, LocalDate dataInicio, LocalDate dataFim, Candidato candidato) {
        this.id = id;
        this.instituicao = instituicao;
        this.nomeCurso = nomeCurso;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.candidato = candidato;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
}
