package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * class that objects are placed in tables
 */
public class Row {

    private  SimpleStringProperty nrS ;
    private  SimpleIntegerProperty weightS  ;
    private  SimpleStringProperty valueS;

    /**
     * construktor of object of Class Row
     * @param s
     * @param i
     * @param d
     */
    public Row(String s,int i,String d)
    {
        this.valueS=new SimpleStringProperty(d);
        this.weightS=new SimpleIntegerProperty(i);
        this.nrS=new SimpleStringProperty(s);
    }

    /**
     * method getting numer of row created in the table
     * @return
     */
    public String getNrS(){return nrS.get();}

    /**
     * method setting numer of row created in the table
     * @param nrS
     */
    public void setNrS(String nrS){this.nrS.set(nrS);}

    /**
     * method getting a weight of class Row's object
     * @return
     */
    public int getWeightS(){return weightS.get();}

    /**
     * method setting a weight of class Row's object
     * @param weightS
     */

    public void setWeightS(int weightS){this.weightS.set(weightS);}

    /**
     * method getting a value of class Row's object
     * @return
     */
    public String getValueS(){return valueS.get();}

    /**
     * method setting a value of class Row's object
     * @param valueS
     */
    public void setValueS(String valueS) {
        this.valueS.set(valueS);
    }
}
