package p1;

import java.util.ArrayList;

/**
 * Serving class for solving problem by using greedy algorithm
 * @author Lenovooo
 *
 */
public class Greed extends Algorytm{

	
	/**
	 * method solving problem using greedy algorithm
	 */
	@Override
	public void GetSolution()
	{
		sr=new ArrayList();
		bestt=new ArrayList();
		for(int i=0;i<inst.items.size();i++)
		{
			double u=(double)(inst.items.get(i).getWartosc())/(double)(inst.items.get(i).getWaga());
			
			sr.add(u);
		}
		
		double best=0;
		int l=0;
		
		n=inst.items.size();
		
		for(int j=0;j<n && waga<=inst.waga_plecaka;j++)
		{ best=0;
		
		for(int i=0;i<inst.items.size();i++)
		{
			if(best<=sr.get(i))
			{
				best=sr.get(i);l=i;
			}
		}
		
		
			if(waga+inst.items.get(l).getWaga()<=inst.waga_plecaka)
			{ 
				waga+=inst.items.get(l).getWaga();
				
				wartosc+=inst.items.get(l).getWartosc();
				bestt.add(inst.items.get(l));
				
				
			}
			
			inst.items.remove(l);
			sr.remove(l);
		
		}
		
		
	}
	
	
}
