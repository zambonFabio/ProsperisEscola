package modelo;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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

	@OneToMany(mappedBy="ano", fetch = FetchType.LAZY) 
	@Cascade(CascadeType.ALL) 
	private Collection<Livro> livros; 
	
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

	public Collection<Livro> getLivros() {
		return livros;
	}

	public void setLivros(Collection<Livro> livros) {
		this.livros = livros;
	}

}
