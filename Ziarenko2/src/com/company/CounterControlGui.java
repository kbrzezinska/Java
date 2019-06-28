package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.text.ParseException;
import java.util.Date;

public class CounterControlGui extends JFrame implements ActionListener {

    Counter counter;
    JButton binc = new JButton("Increment");
    JButton bdec = new JButton("Decrement");
    JButton add = new JButton("Add");

    JTextField txt = new JTextField(10);
    JTextField txtName = new JTextField(5);
    JTextField ss= new JTextField(5);
    JTextArea area;//= new JTextArea(10,10);
    JLabel label;



    // The constructor takes a Counter and a CounterView as arguments.
    // The first of them is required for communication with a counter.
    // The second is the counter view.

    CounterControlGui(Counter c, CounterView clab,SizeView sizeView)  {
        counter = c;
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        add.addActionListener(this);
        cp.add(add);

        binc.addActionListener(this);
        cp.add(binc);
        label=clab;
        cp.add(label);
        bdec.addActionListener(this);
        cp.add(bdec);
        txt.addActionListener(this);
        cp.add(txt);
        txtName.addActionListener(this);
        cp.add(txtName);

        ss.addActionListener(this);
        cp.add(ss);

        area=sizeView;
        cp.add(area);

        setDefaultCloseOperation(3);
        pack();
        show();
    }

    // Handle the action event
    public void actionPerformed(ActionEvent e) {

        try {
            if (e.getSource() == txt) {
                String date;
                date = txt.getText();
                counter.setDate(date);
                return;
            }
            if (e.getSource() == txtName) {
                counter.setName(txtName.getText());
                this.setTitle(counter.getName());
                return;
            }

            if (e.getSource() == ss) {
                counter.setSize(Integer.parseInt(ss.getText()));

                return;
            }
            String cmd = e.getActionCommand();
            if (cmd.equals("Increment"))
                counter.increment();
          else  if (cmd.equals("Decrement"))
                counter.decrement();

         else   if (cmd.equals("Add")) {

                Date d=counter.getTimetable().StringtoDate(label.getText());

                counter.getTimetable().addEvent(d,area.getText());
                System.out.println("add");
            }
            else
                System.out.println("Unrecognized command");

    }catch(PropertyVetoException exc)  {
        System.out.println( "" + exc);
    } catch (ParseException e1) {
            e1.printStackTrace();
        }
    }
}