package converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import conexao.ImplementacaoOperacoes;
import modelo.Idioma;
import util.Utilitario;

@FacesConverter("idiomaConverter")
public class IdiomaConverter implements Converter<Idioma> {

	@Override
	public Idioma getAsObject(FacesContext fc, UIComponent uic, String value) {
		
		Idioma result = null;
		
		if (value != null && value.trim().length() > 0) {
			
			try {
				
				Integer id = Integer.parseInt(value);
				
				List<Idioma> idiomas = null;
				
				String consulta = "from Idioma a " + 
				                  "where a.id = " + id;
				
				ImplementacaoOperacoes<Idioma> operacao = new ImplementacaoOperacoes<Idioma>();
				
				idiomas = operacao.queryList(consulta);
				
				result = idiomas.get(0);
				
				
			} catch (NumberFormatException e) {
				Utilitario.message("error", "Erro", "Erro ao converter identificador do idioma: " + e.getMessage());
			} catch (Exception e) {
				Utilitario.message("error", "Erro", "Erro ao recuperar idioma:" + e.getMessage());
			}
			
		}
		
		return result;
		
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Idioma idioma) {
		
		if (idioma != null) {
			
			String id = String.valueOf(((Idioma)idioma).getId());
			
			return id;
			
		} else {
			return null;
		}
		
	}
	
}
