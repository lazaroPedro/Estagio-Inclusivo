package com.ifbaiano.estagioinclusivo.model;
import java.time.LocalDate;

public class Curso {
    private Long id;
    private String instituicao;
    private String nomeCurso;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Curso(Long id, String instituicao, String nomeCurso, String descricao, LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.instituicao = instituicao;
        this.nomeCurso = nomeCurso;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    public void validar() {


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

    public Curso(String instituicao, String nomeCurso, String descricao, LocalDate dataInicio, LocalDate dataFim) {
        this.instituicao = instituicao;
        this.nomeCurso = nomeCurso;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
}
