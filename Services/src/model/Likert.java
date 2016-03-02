package model;

public enum Likert {
	UM("Nao concordo totalmente"),
	DOIS("Nao concordo parcialmente"),
	TRES("Indiferente"),
	QUATRO("Concordo parcialmente"),
	CINCO("Concordo totalmente");
	
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
