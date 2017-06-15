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
import modelo.Idioma;
import util.Utilitario;

@ManagedBean
@ViewScoped
public class CadastrosIdiomasView implements Serializable {

	private static final long serialVersionUID = 1L;
	ImplementacaoOperacoes<Idioma> operacao = new ImplementacaoOperacoes<Idioma>();

	private Integer id;
	private String idioma;

	private String idiomaFiltro;

	private List<Idioma> idiomas;

	String msg = null;

	@PostConstruct
	private void inicializar() {
		setIdiomas(new ArrayList<Idioma>());
	}

	public void consultar() throws ExecutionException {

		idiomas.clear();
		
		String consulta = "from Idioma a " +
						  "where a.idioma like '%" + getIdiomaFiltro().toUpperCase() + "%' " +
						  "order by a.idioma";

		idiomas = operacao.queryList(consulta);
		
		if (idiomas.size() <= 0) {
			msg = "Nenhum idioma encontrado!";
			Utilitario.message("warn", "Atenção", msg);
		}

	}

	public void novo() {
		this.setId(null);
		this.setIdioma(null);
	}

	public void salvar() throws ExecutionException {

		RequestContext context = RequestContext.getCurrentInstance();
		Boolean salvo = null;

		if (this.getIdioma() == null || this.getIdioma().trim().length() == 0) {

			salvo = false;
			msg = "Para salvar é preciso preencher o idioma!";
			Utilitario.message("warn", "Atenção", msg);

		} else {

			Idioma idioma = new Idioma();

			idioma.setId(this.getId());
			idioma.setIdioma(this.getIdioma());

			operacao.saveOrUpdate(idioma);

			salvo = true;
			msg = this.getIdioma() + " salvo com sucesso.";
			Utilitario.message("info", "Info", msg);

			this.setId(null);
			this.setIdioma("");

			consultar();

		}

		context.addCallbackParam("salvo", salvo);

	}

	public void editar(Idioma idioma) {
		this.setId(idioma.getId());
		this.setIdioma(idioma.getIdioma());
	}

	public void excluir(Idioma idioma) throws ExecutionException {

		try {

			msg = idioma.getIdioma() + " excluido com sucesso.";

			operacao.delete(idioma);

			Utilitario.message("info", "Info", msg);

			consultar();

		} catch (Exception e) {

			msg = "Erro ao excluir " + idioma.getIdioma();
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

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getIdiomaFiltro() {
		return idiomaFiltro;
	}

	public void setIdiomaFiltro(String idiomaFiltro) {
		this.idiomaFiltro = idiomaFiltro;
	}

	public List<Idioma> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(List<Idioma> idiomas) {
		this.idiomas = idiomas;
	}



}
