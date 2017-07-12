package modelo;

import java.util.Collection; 
import javax.persistence.*; 
 
import org.hibernate.annotations.Cascade; 
import org.hibernate.annotations.CascadeType; 

@Entity
@Table(name = "editora", schema = "public")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Editora extends PessoaJuridica {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy="editora", fetch = FetchType.LAZY) 
	@Cascade(CascadeType.ALL) 
	private Collection<Livro> livros; 

	public Collection<Livro> getLivros() {
		return livros;
	}

	public void setLivros(Collection<Livro> livros) {
		this.livros = livros;
	}	
	
}