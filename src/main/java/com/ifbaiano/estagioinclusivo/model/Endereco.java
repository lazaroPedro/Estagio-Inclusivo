package com.ifbaiano.estagioinclusivo.model;

import com.ifbaiano.estagioinclusivo.utils.validation.annotations.Length;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotBlank;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.Pattern;

public class Endereco {
	private int id;
	@NotBlank
	private String rua;
	@NotBlank
	private String bairro;
	@NotBlank
	private String municipio;
	@NotBlank
	private String estado;
	@NotBlank
	@Pattern(regex = "([0-9]{5}[-]?[0-9]{3})")
	@Length(8)
	private String cep;

	public Endereco() {}

	public Endereco(int id, String rua, String bairro, String municipio, String estado, String cep) {
		this.id = id;
		this.rua = rua;
		this.bairro = bairro;
		this.municipio = municipio;
		this.estado = estado;
		this.cep = cep;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
}
