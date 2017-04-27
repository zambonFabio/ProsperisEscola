package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "assunto", schema = "public")
public class Assunto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assunto_sequence")
	@SequenceGenerator(name = "assunto_sequence", sequenceName = "assunto_sequence", allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true)
	private Integer id;

	@Column(name = "descricao", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	private String descricao;

	public Assunto() {
	}

	public Assunto(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
