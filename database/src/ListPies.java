import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListPies {

        private List<Pies> psy;

         public  ListPies() {psy=new ArrayList<Pies>();
         }
        public List getList() {
            return psy;
        }

        public void setList(List<Pies> bookslst) {
            this.psy = bookslst;
        }


}
