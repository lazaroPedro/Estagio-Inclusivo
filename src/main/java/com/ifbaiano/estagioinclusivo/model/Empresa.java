package com.ifbaiano.estagioinclusivo.model;

import com.ifbaiano.estagioinclusivo.model.enums.TipoUsuario;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.Length;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotBlank;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotNull;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.Pattern;

public class Empresa extends Usuario {

    @NotBlank
    @Pattern(regex = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})")
    @Length(14)
    private String cnpj;
    @NotBlank
    private String razaoSocial;

    public Empresa() {
    }

    public Empresa(int id, String nome, String email, Endereco endereco, String salt, String hashSenha, String telefone,String cnpj, String razaoSocial) {
        super(id, nome, email, endereco, salt, hashSenha, TipoUsuario.EMPRESA ,telefone);
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
