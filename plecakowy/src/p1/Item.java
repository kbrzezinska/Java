package p1;

import java.io.Serializable;

/**
 * Serving class for creating items
 * @author Lenovooo
 *
 */
public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int waga;
	private double wartosc;
	
	/**
	 * Constructor of items
	 * @param waga weight of item
	 * @param ww value of item
	 */
	public Item(int waga,double ww){
		this.waga=waga;
		this.wartosc=ww;
	}
	/**
	 * 
	 * @return weight of item
	 */
	public int getWaga() {return waga;}
	
	/**
	 * 
	 * @return value of item
	 */
	public double getWartosc() {return wartosc;}
	
	/**
	 * Default Constructor of items
	 */
	public Item(){}
}
