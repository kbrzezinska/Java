package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.ParseException;
import java.util.Date;
import java.util.zip.DataFormatException;

public class CounterView
        extends JLabel
        implements PropertyChangeListener {


    // The default constructor initializes the label with the text "0"
    CounterView() throws ParseException {

        this("");
    }

    // The constructor for initializing the label with the given text
    CounterView(String lab) throws ParseException {

        super(lab);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(75, 40));
        setHorizontalAlignment(CENTER);
    }

    // Handling the PropertyChange event
    public void propertyChange(PropertyChangeEvent e)  {

        String oldVal = (String) e.getOldValue(),
                newVal = (String) e.getNewValue();
        System.out.println("Value changed from " + oldVal.toString() + " to " + newVal.toString());
        setText("" + newVal + "");  // display the new value on the label
    }

}