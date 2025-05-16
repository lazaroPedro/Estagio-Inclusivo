package com.ifbaiano.estagioinclusivo.model;
import com.ifbaiano.estagioinclusivo.model.enums.TipoUsuario;
import com.ifbaiano.estagioinclusivo.utils.SenhaUtils;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.Length;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotBlank;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotNull;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.Pattern;

public class Usuario {

    private int id;
    @NotBlank
    private String nome;
    @NotBlank
    /*@Pattern(regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")*/
    private String email;
    @NotNull
    private Endereco endereco;

    private String salt;

    private String hashSenha;

    @NotNull
    private TipoUsuario papel;
    @NotBlank
    @Length(9)
    private String telefone;


    public Usuario() {
    }



    public Usuario(int id, String nome, String email, Endereco endereco, String salt, String hashSenha, TipoUsuario papel, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.salt = salt;
        this.hashSenha = hashSenha;
        this.papel = papel;
        this.telefone = telefone;
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

    public TipoUsuario getPapel() {
        return papel;
    }

    public void setPapel(TipoUsuario papel) {
        this.papel = papel;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
