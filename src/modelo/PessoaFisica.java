package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_fisica", schema = "public")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class PessoaFisica extends Pessoa {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	private String nome;

	public PessoaFisica() {
	}

	public PessoaFisica(Integer id, String nome) {
		setId(id);
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
