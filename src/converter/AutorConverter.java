package converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import conexao.ImplementacaoOperacoes;
import modelo.Autor;
import util.Utilitario;

@FacesConverter("autorConverter")
public class AutorConverter implements Converter<Autor> {

	@Override
	public Autor getAsObject(FacesContext fc, UIComponent uic, String value) {
		
		Autor result = null;
		
		if (value != null && value.trim().length() > 0) {
			
			try {
				
				Integer id = Integer.parseInt(value);
				
				List<Autor> autores = null;
				
				String consulta = "from Autor a " + 
				                  "where a.id = " + id;
				
				ImplementacaoOperacoes<Autor> operacao = new ImplementacaoOperacoes<Autor>();
				
				autores = operacao.queryList(consulta);
				
				result = autores.get(0);
				
				
			} catch (NumberFormatException e) {
				Utilitario.message("error", "Erro", "Erro ao converter identificador do autor: " + e.getMessage());
			} catch (Exception e) {
				Utilitario.message("error", "Erro", "Erro ao recuperar autor:" + e.getMessage());
			}
			
		}
		
		return result;
		
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Autor autor) {
		
		if (autor != null) {
			
			String id = String.valueOf(((Autor)autor).getId());
			
			return id;
			
		} else {
			return null;
		}
		
	}
	
}
