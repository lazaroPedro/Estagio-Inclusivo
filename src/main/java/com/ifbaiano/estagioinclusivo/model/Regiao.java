package com.ifbaiano.estagioinclusivo.model;

public class Regiao {
	private int id;
	private String Estado;
	private String cidade;
	
	public Regiao(int id, String estado, String cidade) {
		super();
		this.id = id;
		Estado = estado;
		this.cidade = cidade;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
}

