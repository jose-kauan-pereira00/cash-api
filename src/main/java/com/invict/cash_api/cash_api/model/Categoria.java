package com.invict.cash_api.cash_api.model;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Criação para o model para as Categorias Presentes na aplicação
 * */
@Entity
@Table(name = "categoria")
public class Categoria{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@SuppressWarnings("deprecation")
	@NotNull
	private String nome;


	public Long getCodigo() { return codigo; }
 
	public void setCodigo(Long codigo) {this.codigo = codigo;}

	public String getNome() { return nome; }

	public void setNome(String nome) { this.nome = nome; }

	@Override
	public int hashCode(){ return codigo.hashCode(); }

	@Override
	public boolean equals(Object o){ return this.equals(o); }

}
