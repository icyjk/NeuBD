package NeuBDProyectoSII;

import java.io.Closeable;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class App implements Closeable{
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public App() {
		emf = Persistence.createEntityManagerFactory("ProyectoSII");
		em = emf.createEntityManager();
	}
	
	@Override
	public void close() throws IOException {
		em.close();
		emf.close();
	}
	
    
}
