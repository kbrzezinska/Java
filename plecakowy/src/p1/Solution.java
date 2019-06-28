package p1;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Serving class for showing solution of the problem
 * @author Lenovooo
 *
 */
public class Solution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<Item>przedmioty;
	public int maxWaga;
	public double maxWartosc;
	
	public Solution(ArrayList<Item>przedmioty,int maxWaga,double  maxWartosc){
		
		this.przedmioty=new ArrayList<Item>();
		this.przedmioty=przedmioty;
		this.maxWaga=maxWaga;
		this.maxWartosc=maxWartosc;
	}

	
	public static void main (String [] args){
		
		Instance i=new Instance(15);
		i.AddItem(9, 10);
		i.AddItem(12, 7);
		i.AddItem(2, 1);
		i.AddItem(7,3);
		i.AddItem(5,2);
		//Algorytm a=new Greed();
		//Algorytm a=new RSearch();
		Algorytm a=new BF();
		a.inst=i;
		a.GetSolution();
		for(int j=0;j<a.bestt.size();j++)
		{
			System.out.println(a.bestt.get(j).getWaga());
			
		}
		
		System.out.println(a.getWagaSum()+" "+a.getWartoscSum());
		
		
	}
	
	
	
	
	
	
	
	
}
