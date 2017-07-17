package com.melus.Beers.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


public class Cerveja {
	
	@NotBlank(message="SKU é obrigatório")
	private String sku;
	@NotBlank(message="Nome é obrigatório")
	private String nome;
	@Size(min=1, max=50, message="Tamanho máximo é de 50 caracter.")
	private String descricao;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
