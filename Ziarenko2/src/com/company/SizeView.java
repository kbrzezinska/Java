package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static javax.swing.SwingConstants.CENTER;

public class SizeView extends JTextArea implements PropertyChangeListener {

    SizeView()
    {

       this.setColumns(5);
        this.setRows(2);

    }



    public void propertyChange(PropertyChangeEvent e) {

        Integer oldVal = (Integer) e.getOldValue(),
                newVal = (Integer) e.getNewValue();
        System.out.println("Value size from " + oldVal + " to " + newVal);
        setSize(newVal,this.getHeight());
       // this.setColumns(newVal);

    }
}
