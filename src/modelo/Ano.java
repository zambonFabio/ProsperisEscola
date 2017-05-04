package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ano", schema = "public")
public class Ano {

	@Id
	@Column(name = "ano", unique = true, nullable = false, insertable = true, updatable = true)
	private Integer ano;

	public Ano() {

	}

	public Ano(Integer ano) {
		this.ano = ano;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

}
