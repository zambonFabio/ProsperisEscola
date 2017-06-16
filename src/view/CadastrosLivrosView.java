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
import modelo.Livro;
import util.Utilitario;

@ManagedBean(name="cadastrosLivrosView")
@ViewScoped
public class CadastrosLivrosView implements Serializable {

	private static final long serialVersionUID = 1L;
	ImplementacaoOperacoes<Livro> operacao = new ImplementacaoOperacoes<Livro>();

	private Integer id;
	private String titulo;
	private Long codigoDeBarras;
	private Long isbn;
	private Integer numeroDePaginas;
	private String informacoes;
	private Boolean ativo;
	private Editora editora;

	private String tituloFiltro;

	private List<Livro> livros;
	private List<Editora> editoras;

	String msg = null;

	@PostConstruct
	private void inicializar() {
		setLivros(new ArrayList<Livro>());
		setEditoras(new ArrayList<Editora>());
	}

	public void consultar() throws ExecutionException {

		livros.clear();

		String consulta = "from Livro a " +
						  "where upper(a.titulo) like '%" + getTituloFiltro().toUpperCase() + "%' " +
						  "order by a.titulo";

		livros = operacao.queryList(consulta);

		if (livros.size() <= 0) {
			msg = "Nenhum livro encontrado!";
			Utilitario.message("warn", "Atenção", msg);
		}

	}

	public List<Editora> completeEditora(String query) throws ExecutionException {

		editoras.clear();
		
		if (query != null) {

			String consulta = "from Editora a " +
							  "where upper(a.nomeFantasia) like '%" + query.toUpperCase() + "%' " +
							  "order by a.nomeFantasia";

			ImplementacaoOperacoes<Editora> operacao = new ImplementacaoOperacoes<Editora>();
			editoras = operacao.queryList(consulta);

		}

		return editoras;

	}

	public void novo() {
		this.setId(null);
		this.setTitulo(null);
		this.codigoDeBarras = null;
		this.isbn = null;
		this.numeroDePaginas = null;
		this.informacoes = null;
		this.ativo = true;
		this.editora = null;
	}

	public void salvar(Boolean continuar) throws ExecutionException {

		RequestContext context = RequestContext.getCurrentInstance();
		Boolean salvo = null;

		if (this.getTitulo() == null || this.getTitulo().trim().length() == 0) {

			salvo = false;
			msg = "Para salvar é preciso preencher o titulo!";
			Utilitario.message("warn", "Atenção", msg);

		} else {

			try {

				Livro livro = new Livro();

				livro.setId(this.getId());
				livro.setTitulo(this.getTitulo());
				livro.setCodigoDeBarras(this.getCodigoDeBarras());
				livro.setIsbn(this.getIsbn());
				livro.setNumeroDePaginas(this.getNumeroDePaginas());
				livro.setInformacoes(this.getInformacoes());
				livro.setAtivo(this.getAtivo());
				livro.setEditora(this.getEditora());

				operacao.saveOrUpdate(livro);

				salvo = true;
				msg = this.getTitulo() + " salvo com sucesso.";
				Utilitario.message("info", "Info", msg);

				this.id = null;
				this.setTitulo(null);
				this.setCodigoDeBarras(null);
				this.setIsbn(null);
				this.setNumeroDePaginas(null);
				this.setInformacoes(null);
				this.setAtivo(true);
				this.setEditora(null);

				consultar();

			} catch (Exception e) {

				msg = "Erro ao salvar " + this.getTitulo();
				msg = msg + "\n" + e;
				Utilitario.message("error", "Erro", msg);

			}

		}

		context.addCallbackParam("salvo", salvo);
		context.addCallbackParam("continuar", continuar);

	}

	public void editar(Livro livro) {
		this.setId(livro.getId());
		this.setTitulo(livro.getTitulo());
		this.setCodigoDeBarras(livro.getCodigoDeBarras());
		this.setIsbn(livro.getIsbn());
		this.setNumeroDePaginas(livro.getNumeroDePaginas());
		this.setInformacoes(livro.getInformacoes());
		this.setAtivo(livro.getAtivo());
		this.setEditora(livro.getEditora());
	}

	public void excluir(Livro livro) throws ExecutionException {

		try {

			msg = livro.getTitulo() + " excluido com sucesso.";

			operacao.delete(livro);

			Utilitario.message("info", "Info", msg);

			consultar();

		} catch (Exception e) {

			msg = "Erro ao excluir " + livro.getTitulo();
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(Long codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public Integer getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(Integer numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}

	public String getTituloFiltro() {
		return tituloFiltro;
	}

	public void setTituloFiltro(String tituloFiltro) {
		this.tituloFiltro = tituloFiltro;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public List<Editora> getEditoras() {
		return editoras;
	}

	public void setEditoras(List<Editora> editoras) {
		this.editoras = editoras;
	}
	
}
