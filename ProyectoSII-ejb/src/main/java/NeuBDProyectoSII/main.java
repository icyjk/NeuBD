package NeuBDProyectoSII;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf;
		EntityManager em;
		emf = Persistence.createEntityManagerFactory("ProyectoSII");
		em = emf.createEntityManager();
		em.close();
		emf.close();
	}

}
