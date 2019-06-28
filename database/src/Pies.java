import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Pies {

    @XmlTransient
    private int ID_;
    private String Imie;
    private String Rasa;
    private int Wiek;

    public Pies(){}

    public Pies(int id,String name,String s,int p){this.ID_=id;this.Imie=name;this.Rasa=s;this.Wiek=p;}


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

    public String getRasa() {
        return Rasa;
    }

    public void setRasa(String rasa) {
        Rasa = rasa;
    }

    public int getWiek() {
        return Wiek;
    }

    public void setWiek(int wiek) {
        Wiek = wiek;
    }
}
