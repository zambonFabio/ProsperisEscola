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
@Table(name = "idioma", schema = "public")
public class Idioma implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idioma_sequence")
	@SequenceGenerator(name = "idioma_sequence", sequenceName = "idioma_sequence", allocationSize = 1)
	@Column(name = "id_idioma", unique = true, nullable = false, insertable = true, updatable = true)
	private Integer id;

	@Column(name = "idioma", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	private String idioma;

	public Idioma() {

	}

	public Idioma(Integer id, String idioma) {
		this.id = id;
		this.idioma = idioma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

}
