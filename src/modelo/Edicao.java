package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "edicao", schema = "public")
public class Edicao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "edicao_sequence")
	@SequenceGenerator(name = "edicao_sequence", sequenceName = "edicao_sequence", allocationSize = 1)
	@Column(name = "id_edicao", unique = true, nullable = false, insertable = true, updatable = true)
	private Integer id;

	@Column(name = "edicao", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	private String edicao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

}
