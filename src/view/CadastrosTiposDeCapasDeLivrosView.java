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
import modelo.TipoDeCapaDeLivro;
import util.Utilitario;

@ManagedBean
@ViewScoped
public class CadastrosTiposDeCapasDeLivrosView implements Serializable {

	private static final long serialVersionUID = 1L;
	ImplementacaoOperacoes<TipoDeCapaDeLivro> operacao = new ImplementacaoOperacoes<TipoDeCapaDeLivro>();

	private Integer id;
	private String tipoDeCapa;

	private String tipoDeCapaFiltro;

	private List<TipoDeCapaDeLivro> tiposDeCapas;

	String msg = null;

	@PostConstruct
	private void inicializar() {
		setTiposDeCapas(new ArrayList<TipoDeCapaDeLivro>());
	}

	public void consultar() throws ExecutionException {

		tiposDeCapas.clear();
		
		String consulta = "from TipoDeCapaDeLivro a " +
						  "where a.tipoDeCapa like '%" + getTipoDeCapaFiltro().toUpperCase() + "%' " +
						  "order by a.tipoDeCapa";

		tiposDeCapas = operacao.queryList(consulta);

		if (tiposDeCapas.size() <= 0) {
			msg = "Nenhum tipo de capa foi encontrado!";
			Utilitario.message("warn", "Atenção", msg);
		}

	}

	public void novo() {
		this.setId(null);
		this.setTipoDeCapa(null);
	}

	public void salvar() throws ExecutionException {

		RequestContext context = RequestContext.getCurrentInstance();
		Boolean salvo = null;

		if (this.getTipoDeCapa() == null || this.getTipoDeCapa().trim().length() == 0) {

			salvo = false;
			msg = "Para salvar é preciso preencher o tipo de capa!";
			Utilitario.message("warn", "Atenção", msg);

		} else {

			TipoDeCapaDeLivro tipoDeCapa = new TipoDeCapaDeLivro();

			tipoDeCapa.setId(this.getId());
			tipoDeCapa.setTipoDeCapa(this.getTipoDeCapa());

			operacao.saveOrUpdate(tipoDeCapa);

			salvo = true;
			msg = this.getTipoDeCapa() + " salvo com sucesso.";
			Utilitario.message("info", "Info", msg);

			this.setId(null);
			this.setTipoDeCapa("");

			consultar();

		}

		context.addCallbackParam("salvo", salvo);

	}

	public void editar(TipoDeCapaDeLivro tipoDeCapaDeLivro) {
		this.setId(tipoDeCapaDeLivro.getId());
		this.setTipoDeCapa(tipoDeCapaDeLivro.getTipoDeCapa());
	}

	public void excluir(TipoDeCapaDeLivro tipoDeCapaDeLivro) throws ExecutionException {

		try {

			msg = tipoDeCapaDeLivro.getTipoDeCapa() + " excluido com sucesso.";

			operacao.delete(tipoDeCapaDeLivro);

			Utilitario.message("info", "Info", msg);

			consultar();

		} catch (Exception e) {

			msg = "Erro ao excluir " + tipoDeCapaDeLivro.getTipoDeCapa();
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

	public String getTipoDeCapa() {
		return tipoDeCapa;
	}

	public void setTipoDeCapa(String tipoDeCapa) {
		this.tipoDeCapa = tipoDeCapa;
	}

	public String getTipoDeCapaFiltro() {
		return tipoDeCapaFiltro;
	}

	public void setTipoDeCapaFiltro(String tipoDeCapaFiltro) {
		this.tipoDeCapaFiltro = tipoDeCapaFiltro;
	}

	public List<TipoDeCapaDeLivro> getTiposDeCapas() {
		return tiposDeCapas;
	}

	public void setTiposDeCapas(List<TipoDeCapaDeLivro> tiposDeCapas) {
		this.tiposDeCapas = tiposDeCapas;
	}

}
