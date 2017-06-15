package converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import conexao.ImplementacaoOperacoes;
import modelo.Editora;
import util.Utilitario;

@FacesConverter("editoraConverter")
public class EditoraConverter implements Converter<Editora> {

	@Override
	public Editora getAsObject(FacesContext fc, UIComponent uic, String value) {
		
		Editora result = null;
		
		if (value != null && value.trim().length() > 0) {
			
			try {
				
				Integer id = Integer.parseInt(value);
				
				List<Editora> editoras = null;
				
				String consulta = "from Editora a " + 
				                  "where a.id = " + id;
				
				ImplementacaoOperacoes<Editora> operacao = new ImplementacaoOperacoes<Editora>();
				
				editoras = operacao.queryList(consulta);
				
				result = editoras.get(0);
				
				
			} catch (NumberFormatException e) {
				Utilitario.message("error", "Erro", "Erro ao converter identificador da editora: " + e.getMessage());
			} catch (Exception e) {
				Utilitario.message("error", "Erro", "Erro ao recuperar editora:" + e.getMessage());
			}
			
		}
		
		return result;
		
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Editora editora) {
		
		if (editora != null) {
			
			String id = String.valueOf(((Editora)editora).getId());
			
			return id;
			
		} else {
			return null;
		}
		
	}
	
}
