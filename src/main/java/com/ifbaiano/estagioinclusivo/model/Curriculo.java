package com.ifbaiano.estagioinclusivo.model;

import java.util.List;
import java.util.Optional;

public class Curriculo {
    private int id;
    private String objetivo;
    private String habilidades;
    private String experiencia;
    private Candidato candidato;
    private Optional<Curso> cursos;
    private List<TipoDeficiencia> deficiencias;

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
