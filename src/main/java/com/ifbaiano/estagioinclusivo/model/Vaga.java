package com.ifbaiano.estagioinclusivo.model;

import com.ifbaiano.estagioinclusivo.model.enums.TipoVaga;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.Min;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotBlank;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotNull;

public class Vaga {
	private int id;
	@NotNull
	private Empresa empresa;
	@NotNull
	private Endereco endereco;
	@NotBlank
	private String titulo;
	@NotBlank
	private String descricao;
	@NotBlank
	private String requisitos;
	@NotBlank
	private String beneficios;
	@Min(1)
	private Long qtdVagas;
	@NotNull
	private TipoVaga status;


	public Vaga() {
	}

	public Vaga(int id, Empresa empresa, Endereco endereco, String descricao, String requisitos, String beneficios, Long qtdVagas, TipoVaga status, String titulo) {
		this.id = id;
		this.empresa = empresa;
		this.endereco = endereco;
		this.descricao = descricao;
		this.requisitos = requisitos;
		this.beneficios = beneficios;
		this.qtdVagas = qtdVagas;
		this.status = status;
		this.titulo = titulo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public String getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}

	public Long getQtdVagas() {
		return qtdVagas;
	}

	public void setQtdVagas(Long qtdVagas) {
		this.qtdVagas = qtdVagas;
	}

	public TipoVaga getStatus() {
		return status;
	}

	public void setStatus(TipoVaga status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}