package modelo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "editora", schema = "public")
@PrimaryKeyJoinColumn(name = "id")
public class Editora extends PessoaJuridica {

	private static final long serialVersionUID = 1L;

	public Editora() {

	}

	public Editora(Integer id, String nomeFantasia) {
		setId(id);
		setNomeFantasia(nomeFantasia);
	}

}