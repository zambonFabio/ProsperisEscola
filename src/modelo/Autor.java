package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "autor", schema = "public")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autor_sequence")
	@SequenceGenerator(name = "autor_sequence", sequenceName = "autor_sequence", allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true)
	private Integer id;

	@Column(name = "nome", nullable = false, insertable = true, updatable = true, length = 100)
	private String nome;

	public Autor() {
	}

	public Autor(Integer id, String nome) {
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
