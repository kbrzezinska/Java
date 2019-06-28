package com.company;

import p1.*;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class Server  implements IServer,Serializable {

    static int j=0;
    public Algorytm algorytm;
    public String name;

    public Server(){
        Random random=new Random();
       name= String.valueOf(random.nextInt());
    algorytm=new BF();
    j=random.nextInt(7);
    if(j%3==0){algorytm=new BF();}
    if(j%3==1){   algorytm=new RSearch(); }
    if(j%3==2){   algorytm=new Greed();}
    j++;
   // System.out.println(algorytm.getClass().getName());

}

    @Override
    public String query(String search) throws RemoteException {
        String result;
        if(search.equals("server")){result= "faund";return  result;}
        else {
            result= "not faund";
            return result;
        }
    }

    @Override
    public Solution solve(Instance instance) {

        algorytm.inst=instance;
        algorytm.GetSolution();
        return new Solution(algorytm.bestt,(int)(algorytm.getWagaSum()),algorytm.getWartoscSum());

    }
   // static Registry registry;

    public static void main(String[] args) {

        Server s=new Server();
        IServer ss=s;
        Registry registry= null;
        try {
            registry = LocateRegistry.getRegistry(1900);
            // lookup method to find reference of remote object
            ISpis access = (ISpis) registry.lookup("rmi://localhost:1900");

            OS o=new OS();o.server=s.name;o.algorytm=s.algorytm.getClass().getName();
            //IServer stub=(IServer)UnicastRemoteObject.exportObject((IServer) ss, 0);;
           System.out.println(access.register(o,ss));

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }


      /*  try {
            IServer server=new Server();

            IServer stub=(IServer)UnicastRemoteObject.exportObject((IServer) server, 0);;
             registry =LocateRegistry.createRegistry(1901);

            registry.rebind("rmi://localhost:1901",stub);


            System.err.println("Server ready");
        } catch (RemoteException e) {
            e.printStackTrace();

            System.out.println(e);
        }
*/
    }
}
