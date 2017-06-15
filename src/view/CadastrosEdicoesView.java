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
import modelo.Edicao;
import util.Utilitario;

@ManagedBean
@ViewScoped
public class CadastrosEdicoesView implements Serializable {

	private static final long serialVersionUID = 1L;
	ImplementacaoOperacoes<Edicao> operacao = new ImplementacaoOperacoes<Edicao>();

	private Integer id;
	private String edicao;

	private String edicaoFiltro;

	private List<Edicao> edicoes;

	String msg = null;

	@PostConstruct
	private void inicializar() {
		setEdicoes(new ArrayList<Edicao>());
	}

	public void consultar() throws ExecutionException {

		edicoes.clear();
		
		String consulta = "from Edicao a " +
						  "where upper(a.edicao) like '%" + getEdicaoFiltro().toUpperCase() + "%' " +
						  "order by a.edicao";

		edicoes = operacao.queryList(consulta);
		
		if (edicoes.size() <= 0) {
			msg = "Nenhuma edição encontrada!";
			Utilitario.message("warn", "Atenção", msg);
		}

	}

	public void novo() {
		this.setId(null);
		this.setEdicao(null);
	}

	public void salvar() throws ExecutionException {

		RequestContext context = RequestContext.getCurrentInstance();
		Boolean salvo = null;

		if (this.getEdicao() == null || this.getEdicao().trim().length() == 0) {

			salvo = false;
			msg = "Para salvar é preciso preencher a edição!";
			Utilitario.message("warn", "Atenção", msg);

		} else {

			Edicao edicao = new Edicao();

			edicao.setId(this.getId());
			edicao.setEdicao(this.getEdicao());

			operacao.saveOrUpdate(edicao);

			salvo = true;
			msg = this.getEdicao() + " salvo com sucesso.";
			Utilitario.message("info", "Info", msg);

			this.setId(null);
			this.setEdicao("");

			consultar();

		}

		context.addCallbackParam("salvo", salvo);

	}

	public void editar(Edicao edicao) {
		this.setId(edicao.getId());
		this.setEdicao(edicao.getEdicao());
	}

	public void excluir(Edicao edicao) throws ExecutionException {

		try {

			msg = edicao.getEdicao() + " excluido com sucesso.";

			operacao.delete(edicao);

			Utilitario.message("info", "Info", msg);

			consultar();

		} catch (Exception e) {

			msg = "Erro ao excluir " + edicao.getEdicao();
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

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public String getEdicaoFiltro() {
		return edicaoFiltro;
	}

	public void setEdicaoFiltro(String edicaoFiltro) {
		this.edicaoFiltro = edicaoFiltro;
	}

	public List<Edicao> getEdicoes() {
		return edicoes;
	}

	public void setEdicoes(List<Edicao> edicoes) {
		this.edicoes = edicoes;
	}

	
}
