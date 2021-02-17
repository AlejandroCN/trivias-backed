package com.trivias.backend.model.pojo;

public class Pagina {
	
	private Integer numPagina;
	
	private Integer tamPagina;
	
	private Boolean direccion;
	
	private String atributo;
	
	private String termino;
	
	public Pagina() {
	}

	public Pagina(Integer numPagina, Integer tamPagina, Boolean direccion, String atributo, String termino) {
		super();
		this.numPagina = numPagina;
		this.tamPagina = tamPagina;
		this.direccion = direccion;
		this.atributo = atributo;
		this.termino = termino;
	}

	public Integer getNumPagina() {
		return numPagina;
	}

	public void setNumPagina(Integer numPagina) {
		this.numPagina = numPagina;
	}

	public Integer getTamPagina() {
		return tamPagina;
	}

	public void setTamPagina(Integer tamPagina) {
		this.tamPagina = tamPagina;
	}

	public Boolean getDireccion() {
		return direccion;
	}

	public void setDireccion(Boolean direccion) {
		this.direccion = direccion;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public String getTermino() {
		return termino;
	}

	public void setTermino(String termino) {
		this.termino = termino;
	}
	
}

