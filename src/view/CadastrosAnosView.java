package view;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import conexao.ImplementacaoOperacoes;
import modelo.Ano;
import util.Utilitario;

@ManagedBean
@ViewScoped
public class CadastrosAnosView implements Serializable{

	private static final long serialVersionUID = 1L;
	ImplementacaoOperacoes<Ano> operacao = new ImplementacaoOperacoes<Ano>();
	
	private Integer id;
	private Integer ano;
	
	private List<Ano> anos;
	
	String msg = null;
	
	@PostConstruct
	private void inicializar() {
		setAnos(new ArrayList<Ano>());
	}

	public void consultar() throws ExecutionException {

		anos.clear();
		String consulta = "from Ano a order by a.ano";

		anos = operacao.queryList(consulta);
		
		if (anos.size() <= 0) {
			msg = "Nenhum ano encontrado!";
			Utilitario.message("warn", "Atenção", msg);
		}

	}

	public void novo() {
		this.setId(null);
		this.ano = null;
	}

	public void salvar(Boolean continuar) throws ExecutionException {

		RequestContext context = RequestContext.getCurrentInstance();
		Boolean salvo = null;

		if (this.getAno() == null || this.getAno() <= 0) {

			salvo = false;
			msg = "Para salvar é preciso preencher o ano!";
			Utilitario.message("warn", "Atenção", msg);

		} else {

			try {
			
				Ano ano = new Ano();

				ano.setId(this.getId());
				ano.setAno(this.getAno());

				operacao.saveOrUpdate(ano);

				salvo = true;
				msg = this.getAno().toString() + " salvo com sucesso.";
				Utilitario.message("info", "Info", msg);

				this.id = null;
				this.setAno(null);

				consultar();
			
			} catch (Exception e) {

				msg = "Erro ao salvar " + this.getAno().toString();
				msg = msg + "\n" + e;
				Utilitario.message("error", "Erro", msg);

			}

		}

		context.addCallbackParam("salvo", salvo);
		context.addCallbackParam("continuar", continuar);

	}

	public void editar(Ano ano) {
		this.setId(ano.getId());
		this.setAno(ano.getAno());
	}

	public void excluir(Ano ano) throws ExecutionException {

		try {

			msg = ano.getAno().toString() + " excluido com sucesso.";

			operacao.delete(ano);

			Utilitario.message("info", "Info", msg);

			consultar();

		} catch (Exception e) {

			msg = "Erro ao excluir " + ano.getAno().toString();
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
	
	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public List<Ano> getAnos() {
		return anos;
	}

	public void setAnos(List<Ano> anos) {
		this.anos = anos;
	}
	
	

}
