package conexao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.hibernate.transform.Transformers;

public class ImplementacaoOperacoes<T> implements InterfaceOperacoes<T> {

	private static final long serialVersionUID = 1L;

	Conexao conexao = new Conexao();

	@Override
	public void save(T obj) throws ExecutionException {

		conexao.validaSessionFactory();
		Conexao.getSessionFactory().getCurrentSession().save(obj);
		conexao.executeFlushSession();
		conexao.executeCommit();

	}

	@Override
	public void delete(T obj) throws ExecutionException {

		conexao.validaSessionFactory();
		Conexao.getSessionFactory().getCurrentSession().delete(obj);
		conexao.executeFlushSession();
		conexao.executeCommit();

	}

	@Override
	public void saveOrUpdate(T obj) throws ExecutionException {

		conexao.validaSessionFactory();
		Conexao.getSessionFactory().getCurrentSession().saveOrUpdate(obj);
		conexao.executeFlushSession();
		conexao.executeCommit();

	}

	@Override
	public List<T> queryList(String consulta) throws ExecutionException {

		conexao.validaSessionFactory();

		StringBuilder query = new StringBuilder();
		query.append(consulta);

		List<T> lista = Conexao.getSessionFactory().getCurrentSession().createQuery(query.toString()).list();

		return lista;

	}

}
