package modelo;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "livro", schema = "public")
public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "livro_sequence")
	@SequenceGenerator(name = "livro_sequence", sequenceName = "livro_sequence", allocationSize = 1)
	@Column(name = "id_livro", unique = true, nullable = false, insertable = true, updatable = true)
	private Integer id;

	@Column(name = "titulo", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	private String titulo;

	@Column(name = "codigo_de_barras", insertable = true, updatable = true)
	private Long codigoDeBarras;

	@Column(name = "isbn", insertable = true, updatable = true)
	private Long isbn;

	@Column(name = "numero_de_paginas", insertable = true, updatable = true)
	private Integer numeroDePaginas;

	@Lob
	@Column(name = "informacoes", insertable = true, updatable = true)
	private String informacoes;

	@Column(name = "ativo", insertable = true, updatable = true)
	private Boolean ativo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_editora", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Editora editora;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(Long codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public Integer getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(Integer numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

}
