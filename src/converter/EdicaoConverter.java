package converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import conexao.ImplementacaoOperacoes;
import modelo.Edicao;
import util.Utilitario;

@FacesConverter("edicaoConverter")
public class EdicaoConverter implements Converter<Edicao> {

	@Override
	public Edicao getAsObject(FacesContext fc, UIComponent uic, String value) {
		
		Edicao result = null;
		
		if (value != null && value.trim().length() > 0) {
			
			try {
				
				Integer id = Integer.parseInt(value);
				
				List<Edicao> edicoes = null;
				
				String consulta = "from Edicao a " + 
				                  "where a.id = " + id;
				
				ImplementacaoOperacoes<Edicao> operacao = new ImplementacaoOperacoes<Edicao>();
				
				edicoes = operacao.queryList(consulta);
				
				result = edicoes.get(0);
				
				
			} catch (NumberFormatException e) {
				Utilitario.message("error", "Erro", "Erro ao converter identificador da edição: " + e.getMessage());
			} catch (Exception e) {
				Utilitario.message("error", "Erro", "Erro ao recuperar edição:" + e.getMessage());
			}
			
		}
		
		return result;
		
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Edicao edicao) {
		
		if (edicao != null) {
			
			String id = String.valueOf(((Edicao)edicao).getId());
			
			return id;
			
		} else {
			return null;
		}
		
	}
	
}
