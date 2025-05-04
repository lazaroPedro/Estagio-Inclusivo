package com.ifbaiano.estagioinclusivo.model;


import com.ifbaiano.estagioinclusivo.model.enums.Genero;
import com.ifbaiano.estagioinclusivo.model.enums.TipoUsuario;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotBlank;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotNull;

import java.time.LocalDate;

public class Candidato extends Usuario{
    @NotBlank
    private String cpf;
    @NotNull
    private Genero genero;
    @NotNull
    private LocalDate dataNascimento;


    public Candidato () {
    }



    public Candidato(int id, String nome, String email, Endereco endereco, String salt, String hashSenha, String telefone, String cpf, Genero genero, LocalDate dataNascimento) {
        super(id, nome, email, endereco, salt, hashSenha, TipoUsuario.CANDIDATO, telefone);
        this.cpf = cpf;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


}
