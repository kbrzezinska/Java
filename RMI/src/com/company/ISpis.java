package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ISpis extends Remote {

    public boolean register(OS os,IServer s)throws RemoteException;;

    public IServer getIServer()throws RemoteException;;
    public List<OS> getServers()throws RemoteException;;

}
