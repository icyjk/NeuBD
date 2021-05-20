package NeuBDProyectoSII;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Prueba
 *
 */
@Entity

public class Prueba implements Serializable {

	   
	@Id
	private int num;
	private static final long serialVersionUID = 1L;

	public Prueba() {
		super();
	}   
	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}
   
}
