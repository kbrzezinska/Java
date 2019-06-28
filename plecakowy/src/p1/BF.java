package p1;

import java.util.ArrayList;

/**
 * Serving class for solving problem by  using full search
 * @author Lenovooo
 *
 */
public class BF extends Algorytm{
	
	public ArrayList<Item>p;
	
	/**
	 * method solving problem by  using full search
	 */
	@Override
	public void GetSolution()
	{ 
		n=inst.items.size();
		p=new ArrayList();
		bestt=new ArrayList();
		
		double bw=0;
			for(int i=0;i<=2<<n;i++)
			{
				
				for(int j=0;j<n;j++)
				{
					if((2<<j & i)==0) 
					{
						if(waga+inst.items.get(j).getWaga()<=inst.waga_plecaka)
						{ 
						//System.out.print(inst.items.get(j).getWaga()+ " ");
						wartosc +=inst.items.get(j).getWartosc();
						waga+=inst.items.get(j).getWaga();
						p.add(inst.items.get(j));
						}
					}
				}
				if (bw<=wartosc){
					bw=wartosc;
					bestt.clear();
					
					for(int l=0;l<p.size();l++)
					{
						bestt.add(p.get(l));
					};
				
				}
				//System.out.println("kkk");System.out.println("");
				p.clear();wartosc=0;waga=0;
				
			}
			
			
			
		
	}
	

}
