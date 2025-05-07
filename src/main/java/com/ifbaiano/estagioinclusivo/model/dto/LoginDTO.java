package com.ifbaiano.estagioinclusivo.model.dto;

import com.ifbaiano.estagioinclusivo.utils.validation.annotations.Length;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotBlank;

public class LoginDTO {
    @NotBlank(message = "O campo email não pode ser vazio")
    private String email;

    @NotBlank(message = "O campo senha não pode ser vazio")
    @Length(8)
    private String senha;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
