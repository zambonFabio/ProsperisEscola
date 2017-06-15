package modelo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_de_capa_de_livro", schema = "public")
@PrimaryKeyJoinColumn(name = "id")
public class TipoDeCapaDeLivro extends TipoDeCapa {

	private static final long serialVersionUID = 1L;

}