package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.tools.ForwardingFileObject;

import org.infinispan.marshall.exts.ArrayExternalizers.ListArray;
import org.primefaces.context.RequestContext;

import conexao.ImplementacaoOperacoes;
import modelo.Autor;
import util.Utilitario;

@ManagedBean
@ViewScoped
public class CadastrosAutoresView implements Serializable {

	private static final long serialVersionUID = 1L;
	ImplementacaoOperacoes<Autor> operacao = new ImplementacaoOperacoes<Autor>();

	private Integer id;
	private String nome;

	private String nomeFiltro;

	private List<Autor> autores;

	String msg = null;

	@PostConstruct
	private void inicializar() {
		setAutores(new ArrayList<Autor>());
	}

	public void consultar() throws ExecutionException {

		autores.clear();
		String consulta = "select new modelo.Autor(a.id, a.nome) from modelo.Autor a where a.nome like '%"
				+ getNomeFiltro() + "%' order by a.nome";

		autores = operacao.queryList(consulta);
		
		if (autores.size() <= 0) {
			msg = "Nenhum autor encontrado!";
			Utilitario.message("warn", "Atenção", msg);
		}

	}

	public void novo() {
		this.setId(null);
		this.setNome(null);
	}

	public void salvar() throws ExecutionException {

		RequestContext context = RequestContext.getCurrentInstance();
		Boolean salvo = null;

		if (this.getNome() == null || this.getNome().trim().length() == 0) {

			salvo = false;
			msg = "Para salvar é preciso preencher o nome do autor!";
			Utilitario.message("warn", "Atenção", msg);

		} else {

			Autor autor = new Autor();

			autor.setId(this.getId());
			autor.setNome(this.getNome());

			operacao.saveOrUpdate(autor);

			salvo = true;
			msg = this.getNome() + " salvo com sucesso.";
			Utilitario.message("info", "Info", msg);

			this.setId(null);
			this.setNome("");

			consultar();

		}

		context.addCallbackParam("salvo", salvo);

	}

	public void editar(Autor autor) {
		this.setId(autor.getId());
		this.setNome(autor.getNome());
	}

	public void excluir(Autor autor) throws ExecutionException {

		try {

			msg = autor.getNome() + " excluido com sucesso.";

			operacao.delete(autor);

			Utilitario.message("info", "Info", msg);

			consultar();

		} catch (Exception e) {

			msg = "Erro ao excluir " + autor.getNome();
			msg = msg + "\n" + e;
			Utilitario.message("error", "Erro", msg);

		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeFiltro() {
		return nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

}
