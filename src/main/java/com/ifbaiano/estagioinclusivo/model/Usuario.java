package com.ifbaiano.estagioinclusivo.model;
import com.ifbaiano.estagioinclusivo.utils.SenhaUtils;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private Endereco endereco;
    private String salt;
    private String hashSenha;


    public Usuario() {
    }

    public Usuario(int id, String nome, String email, Endereco endereco, String salt, String hashSenha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.salt = salt;
        this.hashSenha = hashSenha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHashSenha() {
        return hashSenha;
    }

    public void setHashSenha(String hashSenha) {
        this.hashSenha = hashSenha;
    }
}
