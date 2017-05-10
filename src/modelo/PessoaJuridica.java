package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_juridica", schema = "public")
@PrimaryKeyJoinColumn(name = "id")
public class PessoaJuridica extends Pessoa {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome_fantasia", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	private String nomeFantasia;

	public PessoaJuridica() {
	}

	public PessoaJuridica(Integer id, String nomeFantasia) {
		setId(id);
		this.nomeFantasia = nomeFantasia;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

}
