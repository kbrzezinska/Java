package com.company;

import javax.swing.*;
import java.text.ParseException;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws ParseException {
        // Create the counter and its view
        Counter counter = new Counter();

        CounterView counterView = new CounterView(counter.getTimetable().DatetoString(new Date()) );

        Limitator limitator=new Limitator(55,111);
        SizeView sizeView=new SizeView();
        counter.addPropertyChangeListener2(sizeView);
        counter.addVetoableChangeListener(limitator);
        // Register the view object as a listener
        counter.addPropertyChangeListener(counterView);

        // Create and display the GUI
        CounterControlGui gui = new CounterControlGui(counter, counterView,sizeView);
        gui.pack();
        gui.show();


     // BeanInfo beanInfo=new BeanInfo();
    //  beanInfo.events();
    //  beanInfo.properties();

    }
}
