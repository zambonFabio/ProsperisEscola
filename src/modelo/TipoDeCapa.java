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
@Table(name = "tipo_de_capa", schema = "public")
public class TipoDeCapa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_de_capa_sequence")
	@SequenceGenerator(name = "tipo_de_capa_sequence", sequenceName = "tipo_de_capa_sequence", allocationSize = 1)
	@Column(name = "id_tipo_de_capa", unique = true, nullable = false, insertable = true, updatable = true)
	private Integer id;

	@Column(name = "tipo_de_capa", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	private String tipoDeCapa;

	public TipoDeCapa() {

	}

	public TipoDeCapa(Integer id, String tipoDeCapa) {
		this.id = id;
		this.tipoDeCapa = tipoDeCapa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoDeCapa() {
		return tipoDeCapa;
	}

	public void setTipoDeCapa(String tipoDeCapa) {
		this.tipoDeCapa = tipoDeCapa;
	}

}
