package com.ifbaiano.estagioinclusivo.model;

import com.ifbaiano.estagioinclusivo.model.enums.TipoVaga;

public class Vaga {
	private int id;
	private Empresa empresa;
	private Endereco endereco;
	private String descricao;
	private String requisitos;
	private String beneficios;
	private int qtdVagas;
	private TipoVaga status;


	public Vaga() {}

	public Vaga(int id, Empresa empresa, Endereco endereco, String descricao, String requisitos, String beneficios, int qtdVagas, TipoVaga status) {
		this.id = id;
		this.empresa = empresa;
		this.endereco = endereco;
		this.descricao = descricao;
		this.requisitos = requisitos;
		this.beneficios = beneficios;
		this.qtdVagas = qtdVagas;
		this.status = status;
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

	public int getQtdVagas() {
		return qtdVagas;
	}

	public void setQtdVagas(int qtdVagas) {
		this.qtdVagas = qtdVagas;
	}

	public TipoVaga getStatus() {
		return status;
	}

	public void setStatus(TipoVaga status) {
		this.status = status;
	}
}
