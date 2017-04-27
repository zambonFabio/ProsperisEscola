package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "edicao", schema = "public")
public class Edicao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "edicao_sequence")
	@SequenceGenerator(name = "edicao_sequence", sequenceName = "edicao_sequence", allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true)
	private Integer id;

	@Column(name = "nome", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	private String nome;

	public Edicao() {
	}

	public Edicao(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
