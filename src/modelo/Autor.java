package modelo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "autor", schema = "public")
@PrimaryKeyJoinColumn(name = "id")
public class Autor extends PessoaFisica {

	private static final long serialVersionUID = 1L;

	public Autor() {

	}

	public Autor(Integer id, String nome) {
		setId(id);
		setNome(nome);
	}

}