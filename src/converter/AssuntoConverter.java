package converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import conexao.ImplementacaoOperacoes;
import modelo.Assunto;
import util.Utilitario;

@FacesConverter("assuntoConverter")
public class AssuntoConverter implements Converter<Assunto> {

	@Override
	public Assunto getAsObject(FacesContext fc, UIComponent uic, String value) {
		
		Assunto result = null;
		
		if (value != null && value.trim().length() > 0) {
			
			try {
				
				Integer id = Integer.parseInt(value);
				
				List<Assunto> assuntos = null;
				
				String consulta = "from Assunto a " + 
				                  "where a.id = " + id;
				
				ImplementacaoOperacoes<Assunto> operacao = new ImplementacaoOperacoes<Assunto>();
				
				assuntos = operacao.queryList(consulta);
				
				result = assuntos.get(0);
				
				
			} catch (NumberFormatException e) {
				Utilitario.message("error", "Erro", "Erro ao converter identificador do assunto: " + e.getMessage());
			} catch (Exception e) {
				Utilitario.message("error", "Erro", "Erro ao recuperar assunto:" + e.getMessage());
			}
			
		}
		
		return result;
		
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Assunto assunto) {
		
		if (assunto != null) {
			
			String id = String.valueOf(((Assunto)assunto).getId());
			
			return id;
			
		} else {
			return null;
		}
		
	}
	
}
