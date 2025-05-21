package com.ifbaiano.estagioinclusivo.model;

import com.ifbaiano.estagioinclusivo.utils.validation.annotations.MaxLength;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotBlank;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class Curriculo {
    private int id;
    @NotBlank
    @MaxLength(1000)
    private String objetivo;
    @NotBlank
    @MaxLength(1000)
    private String habilidades;
    @NotBlank
    @MaxLength(1000)
    private String experiencia;
    @NotNull
    private Candidato candidato;
    private Optional<Curso> cursos;
    private List<TipoDeficiencia> deficiencias;

    public Curriculo() {}

    public Curriculo(int id, String objetivo, String habilidades, String experiencia, Candidato candidato, Optional<Curso> cursos, List<TipoDeficiencia> deficiencias) {
        this.id = id;
        this.objetivo = objetivo;
        this.habilidades = habilidades;
        this.experiencia = experiencia;
        this.candidato = candidato;
        this.cursos = cursos;
        this.deficiencias = deficiencias;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }

    public String getHabilidades() { return habilidades; }
    public void setHabilidades(String habilidades) { this.habilidades = habilidades; }

    public String getExperiencia() { return experiencia; }
    public void setExperiencia(String experiencia) { this.experiencia = experiencia; }

    public Candidato getCandidato() { return candidato; }
    public void setCandidato(Candidato candidato) { this.candidato = candidato; }

    public Optional<Curso> getCursos() { return cursos; }
    public void setCursos(Optional<Curso> cursos) { this.cursos = cursos; }

    public List<TipoDeficiencia> getDeficiencias() { return deficiencias; }
    public void setDeficiencias(List<TipoDeficiencia> deficiencias) { this.deficiencias = deficiencias; }
}
