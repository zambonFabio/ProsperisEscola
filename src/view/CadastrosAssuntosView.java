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
import modelo.Assunto;
import util.Utilitario;

@ManagedBean
@ViewScoped
public class CadastrosAssuntosView implements Serializable {

	private static final long serialVersionUID = 1L;
	ImplementacaoOperacoes<Assunto> operacao = new ImplementacaoOperacoes<Assunto>();

	private Integer id;
	private String assunto;

	private String assuntoFiltro;

	private List<Assunto> assuntos;

	String msg = null;

	@PostConstruct
	private void inicializar() {
		setAssuntos(new ArrayList<Assunto>());
	}

	public void consultar() throws ExecutionException {

		assuntos.clear();
		
		String consulta = "from Assunto a " +
		                  "where upper(a.assunto) like '%" + getAssuntoFiltro().toUpperCase() + "%' " +
				          "order by a.assunto";

		assuntos = operacao.queryList(consulta);
		
		if (assuntos.size() <= 0) {
			msg = "Nenhum assunto encontrado!";
			Utilitario.message("warn", "Atenção", msg);
		}

	}

	public void novo() {
		this.setId(null);
		this.setAssunto(null);
	}

	public void salvar(Boolean continuar) throws ExecutionException {

		RequestContext context = RequestContext.getCurrentInstance();
		Boolean salvo = null;

		if (this.getAssunto() == null || this.getAssunto().trim().length() == 0) {

			salvo = false;
			msg = "Para salvar é preciso preencher o assunto!";
			Utilitario.message("warn", "Atenção", msg);

		} else {

			try {

				Assunto assunto = new Assunto();

				assunto.setId(this.getId());
				assunto.setAssunto(this.getAssunto());

				operacao.saveOrUpdate(assunto);

				salvo = true;
				msg = this.getAssunto() + " salvo com sucesso.";
				Utilitario.message("info", "Info", msg);

				this.id = null;
				this.setAssunto(null);

				consultar();

			} catch (Exception e) {

				msg = "Erro ao salvar " + this.getAssunto();
				msg = msg + "\n" + e;
				Utilitario.message("error", "Erro", msg);

			}

		}

		context.addCallbackParam("salvo", salvo);
		context.addCallbackParam("continuar", continuar);

	}

	public void editar(Assunto assunto) {
		this.setId(assunto.getId());
		this.setAssunto(assunto.getAssunto());
	}

	public void excluir(Assunto assunto) throws ExecutionException {

		try {

			msg = assunto.getAssunto() + " excluido com sucesso.";

			operacao.delete(assunto);

			Utilitario.message("info", "Info", msg);

			consultar();

		} catch (Exception e) {

			msg = "Erro ao excluir " + assunto.getAssunto();
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

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getAssuntoFiltro() {
		return assuntoFiltro;
	}

	public void setAssuntoFiltro(String assuntoFiltro) {
		this.assuntoFiltro = assuntoFiltro;
	}

	public List<Assunto> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	
	
	
}
