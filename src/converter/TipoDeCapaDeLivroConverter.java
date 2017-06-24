package converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import conexao.ImplementacaoOperacoes;
import modelo.TipoDeCapaDeLivro;
import util.Utilitario;

@FacesConverter("tipodeCapaDeLivroConverter")
public class TipoDeCapaDeLivroConverter implements Converter<TipoDeCapaDeLivro> {

	@Override
	public TipoDeCapaDeLivro getAsObject(FacesContext fc, UIComponent uic, String value) {
		
		TipoDeCapaDeLivro result = null;
		
		if (value != null && value.trim().length() > 0) {
			
			try {
				
				Integer id = Integer.parseInt(value);
				
				List<TipoDeCapaDeLivro> tiposDeCapasDeLivros = null;
				
				String consulta = "from TipoDeCapaDeLivro a " + 
				                  "where a.id = " + id;
				
				ImplementacaoOperacoes<TipoDeCapaDeLivro> operacao = new ImplementacaoOperacoes<TipoDeCapaDeLivro>();
				
				tiposDeCapasDeLivros = operacao.queryList(consulta);
				
				result = tiposDeCapasDeLivros.get(0);
				
				
			} catch (NumberFormatException e) {
				Utilitario.message("error", "Erro", "Erro ao converter identificador do tipo de capa de livro: " + e.getMessage());
			} catch (Exception e) {
				Utilitario.message("error", "Erro", "Erro ao recuperar tipo de capa de livro:" + e.getMessage());
			}
			
		}
		
		return result;
		
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, TipoDeCapaDeLivro tipoDeCapaDeLivro) {
		
		if (tipoDeCapaDeLivro != null) {
			
			String id = String.valueOf(((TipoDeCapaDeLivro)tipoDeCapaDeLivro).getId());
			
			return id;
			
		} else {
			return null;
		}
		
	}
	
}
