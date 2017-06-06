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
@Table(name = "ano", schema = "public")
public class Ano implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ano_sequence")
	@SequenceGenerator(name = "ano_sequence", sequenceName = "ano_sequence", allocationSize = 1)
	@Column(name = "id_ano", unique = true, nullable = false, insertable = true, updatable = true)
	private Integer id;
	
	@Column(name = "ano", unique = true, nullable = false, insertable = true, updatable = true)
	private Integer ano;

	public Ano() {

	}

	public Ano(Integer id, Integer ano) {
		this.id = id;
		this.ano = ano;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

}
