package com;

import java.awt.*;
import javax.swing.*;
import java.net.*;
import javax.jnlp.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//cd C:\Users\Lenovooo\apache-tomcat-8.5.41\bin
//startup.bat//apache/bin
 //jarsigner -verify C:\Users\Lenovooo\workspace\plecakow7jar.jar
public class TestJnlp {
    static BasicService basicService = null;
    public static void main(String args[]) {
        JFrame frame = new JFrame("My First Web application");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel();
        Container content = frame.getContentPane();
        content.setSize(400,300);
        content.add(label, BorderLayout.CENTER);
        String message = "application";

        label.setText(message);

        try {
            basicService = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
        } catch (UnavailableServiceException e) {
            System.err.println("Lookup failed: " + e);
        }

        JButton button = new JButton("http://www.google.com");

        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    URL url = new URL(actionEvent.getActionCommand());
                    basicService.showDocument(url);
                } catch (MalformedURLException ignored) {
                }
            }
        };

        button.addActionListener(listener);

        content.add(button, BorderLayout.SOUTH);
        frame.pack();
        frame.show();
    }
}
