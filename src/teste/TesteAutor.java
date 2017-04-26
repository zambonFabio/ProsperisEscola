package teste;

import java.util.List;

import conexao.ImplementacaoOperacoes;
import modelo.Autor;

public class TesteAutor{

	public static void main(String[] args) throws Exception {

		ImplementacaoOperacoes<Autor> operacao = new ImplementacaoOperacoes<Autor>(); 
		
//		Autor autor = new Autor();
		
//		autor.setNome("Autor 6");
		//operacao.save(autor);
		
//		autor.setId((long) 6);
//		operacao.delete(autor);
		
//		operacao.saveOrUpdate(autor);
		
		List<Autor> lista = operacao.queryList("select id, nome from modelo.Autor");
		
	}

}
