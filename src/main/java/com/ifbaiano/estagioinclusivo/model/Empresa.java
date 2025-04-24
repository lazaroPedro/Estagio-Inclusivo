package com.ifbaiano.estagioinclusivo.model;

public class Empresa extends Usuario {
    private String cnpj;
    private String razaoSocial;

    public Empresa() {
    }

    public Empresa(int id, String nome, String email, String senha, String cnpj, String razaoSocial) {
        super(id, nome, email, senha);
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
