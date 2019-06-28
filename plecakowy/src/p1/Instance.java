package p1;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for creating instance of problem
 * @author Lenovooo
 *
 */
public class Instance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * maximum weight of the backpack
	 */
	public int waga_plecaka;	
	
	/**
	 * list of all items
	 */
	public   ArrayList<Item> items;
	
	/**
	 * Constructor of instance of problem
	 * @param waga maximum weight that cannot be exceeded
	 */
	public Instance(int waga){
		
		waga_plecaka=waga;
		
		items =new ArrayList<Item>();
		
	}
	/**
	 * method adding new item to List of items
	 * @param waga weight of item
	 * @param wartosc value of item
	 */
	public void AddItem(int waga,double wartosc)
	{
		
		items.add(new Item(waga,wartosc));
		
	}
	
}
