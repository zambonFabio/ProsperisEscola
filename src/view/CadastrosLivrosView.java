package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import modelo.Ano;
import modelo.Assunto;
import modelo.Autor;
import modelo.Edicao;
import modelo.Editora;
import modelo.Idioma;
import modelo.Livro;
import modelo.TipoDeCapaDeLivro;
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
	private Ano ano;
	private Edicao edicao;
	private Idioma idioma;
	private TipoDeCapaDeLivro tipoDeCapaDeLivro;
	private Assunto assunto;
	private Autor autor;

	private String tituloFiltro;

	private List<Livro> livros;
	private List<Editora> editoras;
	private List<Ano> anos;
	private List<Edicao> edicoes;
	private List<Idioma> idiomas;
	private List<TipoDeCapaDeLivro> tiposDeCapasDeLivros;
	private List<Assunto> assuntos;
	private List<Assunto> assuntosTbl;
	private List<Autor> autores;
	private List<Autor> autoresTbl;

	String msg = null;

	@PostConstruct
	private void inicializar() {
		setLivros(new ArrayList<Livro>());
		setEditoras(new ArrayList<Editora>());
		setAnos(new ArrayList<Ano>());
		setEdicoes(new ArrayList<Edicao>());
		setIdiomas(new ArrayList<Idioma>());
		setTiposDeCapasDeLivros(new ArrayList<TipoDeCapaDeLivro>());
		setAssuntos(new ArrayList<Assunto>());
		setAssuntosTbl(new ArrayList<Assunto>());
		setAutores(new ArrayList<Autor>());
		setAutoresTbl(new ArrayList<Autor>());
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
	
	public List<Ano> completeAno(String query) throws ExecutionException {

		anos.clear();
		
		if (query != null) {

			String consulta = "from Ano a " +
							  "where cast(a.ano as string) like '%" + query + "%' " +
							  "order by a.ano";

			ImplementacaoOperacoes<Ano> operacao = new ImplementacaoOperacoes<Ano>();
			anos = operacao.queryList(consulta);

		}

		return anos;

	}
	
	public List<Edicao> completeEdicao(String query) throws ExecutionException {

		edicoes.clear();
		
		if (query != null) {

			String consulta = "from Edicao a " +
							  "where upper(a.edicao) like '%" + query.toUpperCase() + "%' " +
							  "order by a.edicao";

			ImplementacaoOperacoes<Edicao> operacao = new ImplementacaoOperacoes<Edicao>();
			edicoes = operacao.queryList(consulta);

		}

		return edicoes;

	}
	
	public List<Idioma> completeIdioma(String query) throws ExecutionException {

		idiomas.clear();
		
		if (query != null) {

			String consulta = "from Idioma a " +
							  "where upper(a.idioma) like '%" + query.toUpperCase() + "%' " +
							  "order by a.idioma";

			ImplementacaoOperacoes<Idioma> operacao = new ImplementacaoOperacoes<Idioma>();
			idiomas = operacao.queryList(consulta);

		}

		return idiomas;

	}
	
	public List<TipoDeCapaDeLivro> completeTipoDeCapaDeLivro(String query) throws ExecutionException {

		tiposDeCapasDeLivros.clear();
		
		if (query != null) {

			String consulta = "from TipoDeCapaDeLivro a " +
					  		  "where upper(a.tipoDeCapa) like '%" + query.toUpperCase() + "%' " +
					          "order by a.tipoDeCapa";

			ImplementacaoOperacoes<TipoDeCapaDeLivro> operacao = new ImplementacaoOperacoes<TipoDeCapaDeLivro>();
			tiposDeCapasDeLivros = operacao.queryList(consulta);

		}

		return tiposDeCapasDeLivros;

	}
	
	public List<Assunto> completeAssunto(String query) throws ExecutionException {

		assuntos.clear();
		
		if (query != null) {

			String consulta = "from Assunto a " +
							  "where upper(a.assunto) like '%" + query.toUpperCase() + "%' " +
							  "order by a.assunto";

			ImplementacaoOperacoes<Assunto> operacao = new ImplementacaoOperacoes<Assunto>();
			assuntos = operacao.queryList(consulta);

		}

		return assuntos;

	}
	
	public List<Autor> completeAutor(String query) throws ExecutionException {

		autores.clear();
		
		if (query != null) {

			String consulta = "from Autor a " +
							  "where upper(a.nome) like '%" + query.toUpperCase() + "%' " +
							  "order by a.nome";

			ImplementacaoOperacoes<Autor> operacao = new ImplementacaoOperacoes<Autor>();
			autores = operacao.queryList(consulta);

		}

		return autores;

	}
	
	public void incluirAssunto() {
		
		if (this.assunto != null) {
			assuntosTbl.add(getAssunto());
			this.assunto = null;
		}
		
	}
	
	public void incluirAutor() {
		
		if (this.autor != null) {
			autoresTbl.add(getAutor());
			this.autor = null;
		}
		
	}
	
	public void excluirAssunto(Assunto assunto) {
		assuntosTbl.remove(assunto);
	}

	public void excluirAutor(Autor autor) {
		autoresTbl.remove(autor);
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
		this.ano = null;
		this.edicao = null;
		this.idioma = null;
		this.tipoDeCapaDeLivro = null;
		this.assunto = null;
		this.assuntos = null;
		this.assuntosTbl = null;
		this.autor = null;
		this.autores = null;
		this.autoresTbl = null;
		
		
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
				livro.setAno(this.getAno());
				livro.setEdicao(this.getEdicao());
				livro.setIdioma(this.getIdioma());
				livro.setTipoDeCapaDeLivro(this.getTipoDeCapaDeLivro());
				livro.setAssuntos(this.getAssuntosTbl());
				livro.setAutores(this.getAutoresTbl());

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
				this.setAno(null);
				this.setEdicao(null);
				this.setIdioma(null);
				this.setTipoDeCapaDeLivro(null);
				
				assuntosTbl.clear();

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
		this.setAno(livro.getAno());
		this.setEdicao(livro.getEdicao());
		this.setIdioma(livro.getIdioma());
		this.setTipoDeCapaDeLivro(livro.getTipoDeCapaDeLivro());
		this.setAssuntosTbl((List<Assunto>)livro.getAssuntos());
		this.setAutoresTbl((List<Autor>)livro.getAutores());
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

	public Ano getAno() {
		return ano;
	}

	public void setAno(Ano ano) {
		this.ano = ano;
	}

	public List<Ano> getAnos() {
		return anos;
	}

	public void setAnos(List<Ano> anos) {
		this.anos = anos;
	}

	public Edicao getEdicao() {
		return edicao;
	}

	public void setEdicao(Edicao edicao) {
		this.edicao = edicao;
	}

	public List<Edicao> getEdicoes() {
		return edicoes;
	}

	public void setEdicoes(List<Edicao> edicoes) {
		this.edicoes = edicoes;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public TipoDeCapaDeLivro getTipoDeCapaDeLivro() {
		return tipoDeCapaDeLivro;
	}

	public void setTipoDeCapaDeLivro(TipoDeCapaDeLivro tipoDeCapaDeLivro) {
		this.tipoDeCapaDeLivro = tipoDeCapaDeLivro;
	}

	public List<Idioma> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(List<Idioma> idiomas) {
		this.idiomas = idiomas;
	}

	public List<TipoDeCapaDeLivro> getTiposDeCapasDeLivros() {
		return tiposDeCapasDeLivros;
	}

	public void setTiposDeCapasDeLivros(List<TipoDeCapaDeLivro> tiposDeCapasDeLivros) {
		this.tiposDeCapasDeLivros = tiposDeCapasDeLivros;
	}

	public List<Assunto> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public List<Assunto> getAssuntosTbl() {
		return assuntosTbl;
	}

	public void setAssuntosTbl(List<Assunto> assuntosTbl) {
		this.assuntosTbl = assuntosTbl;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public List<Autor> getAutoresTbl() {
		return autoresTbl;
	}

	public void setAutoresTbl(List<Autor> autoresTbl) {
		this.autoresTbl = autoresTbl;
	}
	
	
	
}
