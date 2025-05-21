package com.ifbaiano.estagioinclusivo.model;
import com.ifbaiano.estagioinclusivo.utils.validation.ErroCampo;
import com.ifbaiano.estagioinclusivo.utils.validation.ValidationException;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.MaxLength;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotBlank;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Curso {
    private int id;
    @NotBlank
    private String instituicao;
    @NotBlank
    private String nomeCurso;
    @NotBlank
    @MaxLength(1000)
    private String descricao;
    @NotNull
    private LocalDate dataInicio;
    @NotNull
    private LocalDate dataFim;
    @NotNull
    private Candidato candidato;

    public Curso(long idCurso) {
    }




    public Curso() {}

    public Curso(int id, String instituicao, String nomeCurso, String descricao, LocalDate dataInicio, LocalDate dataFim, Candidato candidato) {
        this.id = id;
        this.instituicao = instituicao;
        this.nomeCurso = nomeCurso;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.candidato = candidato;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
