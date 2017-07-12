package modelo;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "agrupador_de_dados", schema = "public")
public class AgrupadorDeDados implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agrupador_de_dados_sequence")
	@SequenceGenerator(name = "agrupador_de_dados_sequence", sequenceName = "agrupador_de_dados_sequence", allocationSize = 1)
	@Column(name = "id_agrupador_de_dados", unique = true, nullable = false, insertable = true, updatable = true)
	private Integer id;

	@Column(name = "agrupador", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	private String agrupador;
	
	@Column(name = "ativo", insertable = true, updatable = true)
	private Boolean ativo;
	
	@ManyToMany(fetch=FetchType.LAZY) 
	@JoinTable(name="agrupador_de_dados_usuario", schema="public",
		joinColumns = @JoinColumn(name="id_agrupador_de_dados"),inverseJoinColumns=@JoinColumn(name="id_pessoa")) 
	 private Collection<Usuario> usuarios;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAgrupador() {
		return agrupador;
	}

	public void setAgrupador(String agrupador) {
		this.agrupador = agrupador;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	
}
