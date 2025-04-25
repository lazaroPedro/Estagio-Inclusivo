package com.ifbaiano.estagioinclusivo.model;

public class Vaga {
	private int id;
	private Empresa empresa;
	private Regiao regiao;
	private Curso curso;
	private String descricao;
	private String requisitos;
	private String beneficios;
	public Vaga(int id, Empresa empresa, Regiao regiao, Curso curso, String descricao, String requisitos,
			String beneficios) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.regiao = regiao;
		this.curso = curso;
		this.descricao = descricao;
		this.requisitos = requisitos;
		this.beneficios = beneficios;
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
	public Regiao getRegiao() {
		return regiao;
	}
	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
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
	
	
		
}
