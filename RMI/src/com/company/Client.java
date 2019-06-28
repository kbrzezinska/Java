package com.company;

import p1.Instance;
import p1.Solution;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Client  {


    public Instance generate()
    {
        Random random=new Random();

        Instance inst=new Instance(random.nextInt(10)+10);
        for(int i=0;i<15;i++)
        {
            inst.AddItem(random.nextInt(10),random.nextDouble()+5.50);
        }
        return inst;
    }
    public static void main(String[] args) {

        Registry registry= null;
        IServer iserver;
        try {
            registry = LocateRegistry.getRegistry(1900);
            // lookup method to find reference of remote object
            ISpis access = (ISpis) registry.lookup("rmi://localhost:1900");

            List<OS> s=access.getServers();
             iserver=access.getIServer();
            for (int i=0;i<access.getServers().size();i++) {
                System.out.println(s.get(i).algorytm+" "+s.get(i).server);
            }
      /*  } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
*/
        Client client=new Client();
        String answer,value="server";
        //try
       // {
         /*    registry= LocateRegistry.getRegistry(1901);
            // lookup method to find reference of remote object
            IServer access = (IServer) registry.lookup("rmi://localhost:1901");
            answer = access.query(value);
            System.out.println(  value + " " + answer);
            int y;

*/
              // y=System.in.read();
            IServer accesss =iserver;
            Solution solution=accesss.solve(client.generate());
            for(int i = 0; i < solution.przedmioty.size(); ++i) {
                System.out.println(i+" waga:"+solution.przedmioty.get(i).getWaga()+" wartosc:"+solution.przedmioty.get(i).getWartosc());
            }
            System.out.println("max waga: "+solution.maxWaga);
            System.out.println("max wartosc: "+solution.maxWartosc);


        }
        catch(Exception ae)
        {
            System.out.println(ae);
        }

    }
}
