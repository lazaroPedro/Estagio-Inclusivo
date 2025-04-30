package com.ifbaiano.estagioinclusivo.model;

import java.util.List;

public class Candidato extends Usuario{
    private String cpf;
    private String telefone;

    public void validar(){}
    public Candidato () {
    }

    public Candidato(int id, String nome, String email, String hashSenha, String salt, String cpf, String telefone, List<Curso> cursos, Regiao regiao) {
        super(id, nome, email, hashSenha, salt);
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Candidato(int id, String nome, String email, String hashSenha, String salt,
                     String cpf, String telefone) {
        super(id, nome, email, hashSenha, salt);
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
