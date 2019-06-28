package sample;

import p1.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.Hashtable;

public class Signed  {

public void ss(Item items[],Instance i)
{
    for(int j=0;j<items.length;j++)
    {
        i.AddItem(items[j].getWaga(), items[j].getWartosc());
    }
}

    public Signed( ) {

        Item[] items ={

        new Item(9, 10),
        new Item(12, 7),
        new Item(2, 1),
        new Item(7,3),
        new Item(5,2),
    };

    Instance i=new Instance(25);
        Algorytm a=new Greed();
        Algorytm b=new RSearch();
        Algorytm c=new BF();

        ss(items,i);

        a.inst=i;b.inst=i;c.inst=i;
        a.GetSolution();

        System.out.println("Greedy");
      /*  for(int j=0;j<a.bestt.size();j++)
        {
            System.out.println(a.bestt.get(j).getWaga());
        }*/
        System.out.println("solution: weight: "+a.getWagaSum()+" value: "+a.getWartoscSum());

        ss(items,i);b.GetSolution();
        System.out.println("");
        System.out.println("Random search");
      /*  for(int j=0;j<b.bestt.size();j++)
        {
            System.out.println(b.bestt.get(j).getWaga());
        }*/
        System.out.println("solution: weight: "+b.getWagaSum()+" valuse: "+b.getWartoscSum());


        ss(items,i);c.GetSolution();
        System.out.println("");
        System.out.println("Brute Force");

    /*    for(int j=0;j<c.bestt.size();j++)
        {
            System.out.println(c.bestt.get(j).getWaga());
        }*/
        System.out.println("solution: weight: "+c.getWagaSum()+" value: "+c.getWartoscSum());



    }
}
