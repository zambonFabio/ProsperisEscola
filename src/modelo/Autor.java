package modelo;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "autor", schema = "public")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Autor extends PessoaFisica {

	private static final long serialVersionUID = 1L;
	
	@ManyToMany(fetch=FetchType.LAZY) 
	@JoinTable(name="autor_livro", schema="public",
		joinColumns = @JoinColumn(name="id_pessoa"),inverseJoinColumns=@JoinColumn(name="id_livro")) 
	 private Collection<Livro> livros;

	public Collection<Livro> getLivros() {
		return livros;
	}

	public void setLivros(Collection<Livro> livros) {
		this.livros = livros;
	}

}