package converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import conexao.ImplementacaoOperacoes;
import modelo.Ano;
import util.Utilitario;

@FacesConverter("anoConverter")
public class AnoConverter implements Converter<Ano> {

	@Override
	public Ano getAsObject(FacesContext fc, UIComponent uic, String value) {
		
		Ano result = null;
		
		if (value != null && value.trim().length() > 0) {
			
			try {
				
				Integer id = Integer.parseInt(value);
				
				List<Ano> anos = null;
				
				String consulta = "from Ano a " + 
				                  "where a.id = " + id;
				
				ImplementacaoOperacoes<Ano> operacao = new ImplementacaoOperacoes<Ano>();
				
				anos = operacao.queryList(consulta);
				
				result = anos.get(0);
				
				
			} catch (NumberFormatException e) {
				Utilitario.message("error", "Erro", "Erro ao converter identificador do ano: " + e.getMessage());
			} catch (Exception e) {
				Utilitario.message("error", "Erro", "Erro ao recuperar ano:" + e.getMessage());
			}
			
		}
		
		return result;
		
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Ano ano) {
		
		if (ano != null) {
			
			String id = String.valueOf(((Ano)ano).getId());
			
			return id;
			
		} else {
			return null;
		}
		
	}
	
}
