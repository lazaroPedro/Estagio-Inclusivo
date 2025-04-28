package com.ifbaiano.estagioinclusivo.model;

import java.util.List;

public class Candidato extends Usuario{
    private String cpf;
    private String curso;
    private String telefone;
    private Regiao regiao;

    public void validar(){}
    public Candidato () {
    }

    public Candidato(int id, String nome, String email, String hashSenha, String salt, String cpf, String curso, String telefone) {
        super(id, nome, email, hashSenha, salt);
        this.cpf = cpf;
        this.curso = curso;
        this.telefone = telefone;
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


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
