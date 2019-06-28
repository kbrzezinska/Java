package com.company;

import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static jdk.nashorn.internal.objects.NativeDate.setDate;

public class Counter implements Serializable {

    private String date="";   // the bound property "count"

    private Timetable timetable;

    public Timetable getTimetable() {
        return timetable;
    }


    private String name="Bean";
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int size=10;

    public int getSize() {
        return size;
    }

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private VetoableChangeSupport vetos = new VetoableChangeSupport(this);

    public synchronized void addVetoableChangeListener(VetoableChangeListener l) {
        vetos.addVetoableChangeListener(l);
    }

    public synchronized void removeVetoableChangeListener(VetoableChangeListener l) {
        vetos.removeVetoableChangeListener(l);
    }

    public synchronized void setSize(int size) throws PropertyVetoException {

      int oldsize = this.size ;
      vetos.fireVetoableChange("size", new Integer(oldsize), new Integer(size));

        this.size=size;
      propertyChangeSupport.firePropertyChange("size", new Integer(oldsize), new Integer(this.size));

    }

    public synchronized void addPropertyChangeListener2(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener2(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }


    // A helper object for registering listeners
    // and firing change events at them.
    private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);


    public Counter() {

        this("Beans");
    }


    public Counter(String name) {

        timetable=new Timetable();
        try {
            setDate(timetable.DatetoString(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ;setName(name);
    }


    // The methods for adding and removing listeners

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChange.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChange.removePropertyChangeListener(l);
    }

    // Incrementing and decrementing the counter

    public void increment() {
        Date d= null;
        try {
            d = timetable.StringtoDate(getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        d=timetable.nextDate(d);
        try {
            setDate(timetable.DatetoString(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void decrement() {

        Date d= null;
        try {
            d = timetable.StringtoDate(getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        d=timetable.previousDate(d);
        try {
            setDate(timetable.DatetoString(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    // The getter for the property "count"
    public String getDate() {
        return date;
    }

    //  The setter for the property "count"
    public synchronized void setDate(String aCount) {
        String oldValue = date;
        date = aCount;

        // Calling the method firePropertChange() from the PropertyChangeSupport object
        // fires the PropertyChangeEvent at registered listeners,
        // but only if the old value differs from the new value.
        propertyChange.firePropertyChange("date", new String(oldValue), new String(aCount));
    }

}