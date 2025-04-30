package com.ifbaiano.estagioinclusivo.model;



public class Candidato extends Usuario{
    private String cpf;
    private String telefone;

    public void validar(){}
    public Candidato () {
    }

    public Candidato(int id, String nome, String email, Endereco endereco, String salt, String hashSenha, String cpf, String telefone) {
        super(id, nome, email, endereco, salt, hashSenha);
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
