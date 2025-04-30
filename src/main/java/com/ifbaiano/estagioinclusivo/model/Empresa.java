package com.ifbaiano.estagioinclusivo.model;

public class Empresa extends Usuario {
    private String cnpj;
    private String razaoSocial;

    public void validar(){}

    public Empresa() {
    }

    public Empresa(int id, String nome, String email, Endereco endereco, String salt, String hashSenha, String cnpj, String razaoSocial) {
        super(id, nome, email, endereco, salt, hashSenha);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }


}
