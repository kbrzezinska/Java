import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Osoba {

    @XmlTransient
    private int ID_;
    private String Imie;
    private String Nazwisko;
    private int ID_pies;


    public int getID_pies() {
        return ID_pies;
    }

    public void setID_pies(int ID_pies) {
        this.ID_pies = ID_pies;
    }

    public int getID_() {
        return ID_;
    }

    public void setID_(int ID_) {
        this.ID_ = ID_;
    }

    public String getImie() {
        return Imie;
    }

    public void setImie(String imie) {
        Imie = imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        Nazwisko = nazwisko;
    }
}
