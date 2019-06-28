package p1;

import java.util.ArrayList;
/**
 * Abstract class for solving knapsack problem
 * @author Lenovooo
 *
 */
public abstract class Algorytm {

	public int n;
	/**
	 * instance of problem
	 */
	public  Instance inst;
	
	/**
	 * list of values which are results of division  values by weights
	 */
	public ArrayList<Double>sr;
	
	/**
	 * list of chosen items
	 */
	public ArrayList<Item>bestt;
	
	public int waga=0;
	public double wartosc=0;
	
	/**
	 * method solving knapsack problem
	 */
	public void GetSolution() {
		
		
	}
	/**
	 * count sum of size of all solution items 
	 * @return sum of value of all solution items 
	 */
	public double getWartoscSum()
	{
		double u=0;
	
		for(int i=0;i<bestt.size();i++)
	
		{
		 u+=bestt.get(i).getWartosc();	
		}
	
		return u;
	}

	/**
	 * count sum of weight of all solution items 
	 * @return sum of weight of all solution items 
	 */
	public double getWagaSum()
	{
		double u=0;
	
		for(int i=0;i<bestt.size();i++)
	
		{
		 u+=bestt.get(i).getWaga();	
		}
	
		return u;
	}
	
	
}
