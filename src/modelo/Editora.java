package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "editora", schema = "public")
public class Editora {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "editora_sequence")
	@SequenceGenerator(name = "editora_sequence", sequenceName = "editora_sequence", allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true)
	private Integer id;

	@Column(name = "nome", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	private String nome;

	public Editora() {
	}

	public Editora(Integer id, String nome) {
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
