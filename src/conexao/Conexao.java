package conexao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Conexao implements Serializable {

	private static final long serialVersionUID = 1L;

	private static SessionFactory sessionFactory = buildSessionFactory();

	// Responsável por ler o arquivo de configuração hibernate.cfg.xml
	private static SessionFactory buildSessionFactory() {

		try {

			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure().buildSessionFactory();
			}

			return sessionFactory;

		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Erro ao criar conexão SessionFactory");
		}

	}

	// Retorna o SessionFactory corrente
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// Retorna a sessão do SessionFactory
	public static Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	// Abre uma nova sessão no SessionFactory
	public static Session openSession() {

		if (sessionFactory == null) {
			buildSessionFactory();
		}

		return sessionFactory.openSession();

	}

	// Se a transação não esta ativa é iniciado uma nova transação
	private void validaTransaction() {
		if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
	}

	// Verifica se a sessão esta vazia e se a transação esta iniciada
	public void validaSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = getSessionFactory();
		}
		validaTransaction();
	}

	// Roda instantaneamente a instrução sql no banco de dados
	public void executeFlushSession() {
		sessionFactory.getCurrentSession().flush();
	}

	// Efetua o commit no banco de dados
	public void executeCommit() {
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		sessionFactory.getCurrentSession().close();
		
	}
	
}
