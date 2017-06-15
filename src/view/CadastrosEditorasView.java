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
import modelo.Editora;
import util.Utilitario;

@ManagedBean
@ViewScoped
public class CadastrosEditorasView implements Serializable {

	private static final long serialVersionUID = 1L;
	ImplementacaoOperacoes<Editora> operacao = new ImplementacaoOperacoes<Editora>();

	private Integer id;
	private String nomeFantasia;

	private String nomeFantasiaFiltro;

	private List<Editora> editoras;

	String msg = null;

	@PostConstruct
	private void inicializar() {
		setEditoras(new ArrayList<Editora>());
	}

	public void consultar() throws ExecutionException {

		editoras.clear();

		String consulta = "from Editora a " +
						  "where upper(a.nomeFantasia) like '%" + getNomeFantasiaFiltro().toUpperCase() + "%' " +
						  "order by a.nomeFantasia";

		editoras = operacao.queryList(consulta);
		
		if (editoras.size() <= 0) {
			msg = "Nenhum editora encontrada!";
			Utilitario.message("warn", "Atenção", msg);
		}

	}

	public void novo() {
		this.setId(null);
		this.setNomeFantasia(null);
	}

	public void salvar() throws ExecutionException {

		RequestContext context = RequestContext.getCurrentInstance();
		Boolean salvo = null;

		if (this.getNomeFantasia() == null || this.getNomeFantasia().trim().length() == 0) {

			salvo = false;
			msg = "Para salvar é preciso preencher o nome fantasia da editora!";
			Utilitario.message("warn", "Atenção", msg);

		} else {

			Editora editora = new Editora();

			editora.setId(this.getId());
			editora.setNomeFantasia(this.getNomeFantasia());

			operacao.saveOrUpdate(editora);

			salvo = true;
			msg = this.getNomeFantasia() + " salvo com sucesso.";
			Utilitario.message("info", "Info", msg);

			this.setId(null);
			this.setNomeFantasia("");

			consultar();

		}

		context.addCallbackParam("salvo", salvo);

	}

	public void editar(Editora editora) {
		this.setId(editora.getId());
		this.setNomeFantasia(editora.getNomeFantasia());
	}

	public void excluir(Editora editora) throws ExecutionException {

		try {

			msg = editora.getNomeFantasia() + " excluido com sucesso.";

			operacao.delete(editora);

			Utilitario.message("info", "Info", msg);

			consultar();

		} catch (Exception e) {

			msg = "Erro ao excluir " + editora.getNomeFantasia();
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

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getNomeFantasiaFiltro() {
		return nomeFantasiaFiltro;
	}

	public void setNomeFantasiaFiltro(String nomeFantasiaFiltro) {
		this.nomeFantasiaFiltro = nomeFantasiaFiltro;
	}

	public List<Editora> getEditoras() {
		return editoras;
	}

	public void setEditoras(List<Editora> editoras) {
		this.editoras = editoras;
	}

	
}
