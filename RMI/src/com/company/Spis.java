package com.company;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Spis implements ISpis {

public ArrayList<OS>osy=new ArrayList<OS>();
    public ArrayList<IServer>servers=new ArrayList<IServer>();

    @Override
    public IServer getIServer()
    {
        return servers.get(servers.size()-1);
    }
    @Override
    public boolean register(OS os,IServer s)
    {

        osy.add(os);servers.add(s);
        System.out.println(os.server+" "+os.algorytm);
        return true;
    }
    @Override
    public List<OS> getServers()
    {
        return osy;
    }

    public static void main(String[] args) {
        try {
            ISpis os=new Spis();

            ISpis stub=(ISpis) UnicastRemoteObject.exportObject((ISpis)os, 0);;
            Registry registry = LocateRegistry.createRegistry(1900);

            registry.rebind("rmi://localhost:1900",stub);


            System.err.println("Spis ready");
        } catch (RemoteException e) {
            e.printStackTrace();

            System.out.println(e);
        }
    }
}
