package com.ifbaiano.estagioinclusivo.model;

import java.util.List;

public class Candidato extends Usuario{
    private String cpf;
    private String curso;
    private String telefone;
    private List<Curso> cursos;
    private List<TipoDeficiencia> deficiencias;

    public Candidato () {
    }

    public Candidato(int id, String nome, String email, String hashSenha, String salt, String cpf, String curso, String telefone, List<TipoDeficiencia> deficiencias) {
        super(id, nome, email, hashSenha, salt);
        this.cpf = cpf;
        this.curso = curso;
        this.telefone = telefone;
        this.deficiencias = deficiencias;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<TipoDeficiencia> getDeficiencias() {
        return deficiencias;
    }

    public void setDeficiencias(List<TipoDeficiencia> deficiencias) {
        this.deficiencias = deficiencias;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void validar() {

    }
}
