package conexao;

import java.io.Serializable;
import java.util.List;

public interface InterfaceOperacoes<T> extends Serializable {

	// salva os dados
	void save(T obj) throws Exception;
	
	// exclui os dados
	void delete(T obj) throws Exception;
	
	// salva ou atualiza
	void saveOrUpdate(T obj) throws Exception;
	
	// consulta dados
	List<T> queryList(String consulta) throws Exception;


}
