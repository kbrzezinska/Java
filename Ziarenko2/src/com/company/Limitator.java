package com.company;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class Limitator implements VetoableChangeListener {

    private int min,max;
    Limitator(int m,int mm)
    {
        min=m;
        max=mm;

    }

    public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException {


            Integer newVal = (Integer) e.getNewValue();
            int val = newVal.intValue();
            if (val < min || val > max)
                throw new PropertyVetoException("Unacceptable change of value", e);
    }
}
