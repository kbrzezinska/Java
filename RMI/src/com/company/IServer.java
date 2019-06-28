package com.company;

import p1.Algorytm;
import p1.Instance;
import p1.Solution;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends  Serializable {

    public Algorytm algorytm = null;
    public String name=null;
    public String query(String search) throws RemoteException;
    public Solution solve(Instance i)throws RemoteException;

}
