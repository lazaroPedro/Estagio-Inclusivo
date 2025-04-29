package com.ifbaiano.estagioinclusivo.model;
import com.ifbaiano.estagioinclusivo.utils.SenhaUtils;

public class Usuario {
    private int id_usuario;
    private String nome;
    private String email;
    private String salt;
    private String hashSenha;


    public Usuario() {
    }

    public Usuario(String nome, String email, String salt, String hashSenha) {
        this.nome = nome;
        this.email = email;
        this.salt = salt;
        this.hashSenha = hashSenha;
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.salt = SenhaUtils.gerarSalt();
        this.hashSenha = SenhaUtils.gerarHashSenha(senha, this.salt);
    }


    public Usuario(int id_usuario, String nome, String email, String salt, String hashSenha) {
        this.nome = nome;
        this.email = email;
        this.salt = salt;
        this.hashSenha = hashSenha;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public String getHashSenha() {
        return hashSenha;
    }

    public void setHashSenha(String hashSenha) {
        this.hashSenha = hashSenha;
    }

    public void setId(int id) {
        this.id_usuario = id;
    }

    public int getId() {
        return id_usuario;
    }
}
