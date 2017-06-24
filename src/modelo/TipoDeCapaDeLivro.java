package modelo;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "tipo_de_capa_de_livro", schema = "public")
@PrimaryKeyJoinColumn(name = "id")
public class TipoDeCapaDeLivro extends TipoDeCapa {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="tipoDeCapaDeLivro", fetch = FetchType.LAZY) 
	@Cascade(CascadeType.ALL) 
	private Collection<Livro> livros;

	public Collection<Livro> getLivros() {
		return livros;
	}

	public void setLivros(Collection<Livro> livros) {
		this.livros = livros;
	} 

}