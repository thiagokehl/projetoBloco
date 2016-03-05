package com.infnet.projeto.data;

public enum Likert {
	UM("Discordo totalmente!"),
	DOIS("Discordo!"),
	TRES("Não concordo nem discordo!"),
	QUATRO("Condordo!"),
	CINCO("Concordo totalmente!");
	
	private String significado;
	
	private Likert(String significado){
		this.significado = significado;
	}

	public String getSignificado() {
		return significado;
	}

	public void setSignificado(String significado) {
		this.significado = significado;
	}
}
