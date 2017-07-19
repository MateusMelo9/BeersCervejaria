package com.melus.Beers.model;

public enum TipoPessoa {

	PESSOAFISICA("Pessoa Física"),
	JURIDICA ("Juríca");
	
	private String descricao;
	
	private TipoPessoa(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
