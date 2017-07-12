package modelo;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "usuario", schema = "public")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Usuario extends PessoaFisica {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "usuario", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	private String usuario;

	@Column(name = "senha", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	private String senha;
	
	@Column(name = "admin", insertable = true, updatable = true)
	private Boolean admin;
	
	@Column(name = "cadastrar_nova_senha", insertable = true, updatable = true)
	private Boolean cadastrarNovaSenha;
	
	@Column(name = "ativo", insertable = true, updatable = true)
	private Boolean ativo;
	
	@ManyToMany(fetch=FetchType.LAZY) 
	@JoinTable(name="agrupador_de_dados_usuario", schema="public",
		joinColumns = @JoinColumn(name="id_pessoa"),inverseJoinColumns=@JoinColumn(name="id_agrupador_de_dados")) 
	private Collection<AgrupadorDeDados> agrupadoresDeDados;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getCadastrarNovaSenha() {
		return cadastrarNovaSenha;
	}

	public void setCadastrarNovaSenha(Boolean cadastrarNovaSenha) {
		this.cadastrarNovaSenha = cadastrarNovaSenha;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Collection<AgrupadorDeDados> getAgrupadoresDeDados() {
		return agrupadoresDeDados;
	}

	public void setAgrupadoresDeDados(Collection<AgrupadorDeDados> agrupadoresDeDados) {
		this.agrupadoresDeDados = agrupadoresDeDados;
	}
	
	
	
}