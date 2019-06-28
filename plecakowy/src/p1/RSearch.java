
package p1;

import java.util.ArrayList;

/**
 * Serving class for solving problem by  drawing random items
 */
import java.util.Random;
public class RSearch extends Algorytm{
	
	
/**
 * method solving problem by  drawing random items
 */
	
	@Override
	public void GetSolution()
	{   
		bestt=new ArrayList();
		ArrayList<Item> o=new ArrayList();
		ArrayList<Item> b=new ArrayList();
		for(int i=0;i<inst.items.size();i++)
		{
			o.add(inst.items.get(i));
		}
		
		
		int l;
		double bw=0;
	    
	    n=inst.items.size();
	    Random r=new Random();	
	    for(int j=0;j<100;j++){
	    	
	    wartosc=0;
	    waga=0;
	    bestt.clear();
		for(int i=0;i<n&& waga<=inst.waga_plecaka;i++)
		{
				
			l=r.nextInt(inst.items.size());
			if(waga+inst.items.get(l).getWaga()<=inst.waga_plecaka)
			{
				waga+=inst.items.get(l).getWaga();
				wartosc+=inst.items.get(l).getWartosc();
				bestt.add(inst.items.get(l));
				
				inst.items.remove(l);
				
			}
		}
		if(wartosc>=bw)
		{b.clear();
			for(int i=0;i<bestt.size();i++)
			{
				b.add(bestt.get(i));
			};
			
			bw=wartosc;
		}
	   
		inst.items.clear();
		for(int i=0;i<o.size();i++)
		{
			inst.items.add(o.get(i));
		};//System.out.println("kk"+o.size());
		
	    }
	    bestt.clear();
		for(int i=0;i<b.size();i++)
		{
			bestt.add(b.get(i));
		};
	    }

}
