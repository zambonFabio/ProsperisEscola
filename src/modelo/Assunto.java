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
@Table(name = "assunto", schema = "public")
public class Assunto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assunto_sequence")
	@SequenceGenerator(name = "assunto_sequence", sequenceName = "assunto_sequence", allocationSize = 1)
	@Column(name = "id_assunto", unique = true, nullable = false, insertable = true, updatable = true)
	private Integer id;

	@Column(name = "assunto", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	private String assunto;

	public Assunto() {

	}

	public Assunto(Integer id, String assunto) {
		this.id = id;
		this.assunto = assunto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

}
