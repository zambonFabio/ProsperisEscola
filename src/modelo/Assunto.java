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
	
	@ManyToMany(fetch=FetchType.LAZY) 
	@JoinTable(name="assunto_livro", schema="public",
		joinColumns = @JoinColumn(name="id_assunto"),inverseJoinColumns=@JoinColumn(name="id_livro")) 
	private Collection<Livro> livros; 
	  
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

	public Collection<Livro> getLivros() {
		return livros;
	}

	public void setLivros(Collection<Livro> livros) {
		this.livros = livros;
	}

	
}
