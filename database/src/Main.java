import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Connection connection=null;

    static String[] rasa ={"york","mops","west","corgi"};
    static String[] name ={"lopi","lola","tan","charli"};
    static String[] imie ={"anna","kaya","ola","tom","john","jan"};
    static String[] nazw ={"john","smith","pitt","Jon"};

   static int id;
    public static void  dodajPies( String id,String name,String Rasa,String p) throws SQLException {
        Statement statement = connection.createStatement();

        String queryString = "select max(ID) from Pies ;";
        ResultSet   rs = statement.executeQuery(queryString);


        String i="0";
        while (rs.next()) {
            //System.out.println(rs.getString(1));
            i=rs.getString(1);
        }
        //   System.out.println(i);
        if(i!=null){
           int idd =Integer.parseInt(i);
            idd++;
            i=String.valueOf(idd);
        }
        else {i="1";}

        String queryString2 = "insert into Pies (ID,Imie,Rasa,Wiek) values(?,?,?,?);";
        PreparedStatement stmt = connection.prepareStatement(queryString2);
        stmt.setString(1, i);
        stmt.setString(2, name);//imie
        stmt.setString(3, Rasa);
        stmt.setString(4, p);
        stmt.executeUpdate();



    }

    public static void Import ()  {
        Pies pies;
        JAXBContext ctx = null;
        try {
            ctx = JAXBContext.newInstance(ListPies.class);
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            ListPies psy = (ListPies) unmarshaller.unmarshal(new File("pies.xml"));
            for(int i=0;i<psy.getList().size();i++)
            { Pies p= (Pies) psy.getList().get(i);

           // System.out.println(p.getImie());
                dodajPies(String.valueOf(i),p.getImie(),p.getRasa(),String.valueOf(p.getWiek()));
            }


        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public static void export() throws SQLException, JAXBException {

      JAXBContext ctx = JAXBContext.newInstance(ListPies.class);
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        File file = new File("pies.xml");
        Statement statement = connection.createStatement();

        String queryString = "select * from Pies ";
        ResultSet rs = statement.executeQuery(queryString);
        List<Pies> psy=new ArrayList<Pies>();
        while (rs.next()) {
            psy.add(new Pies(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4))));
        }
        ListPies listPies=new ListPies();
        listPies.setList(psy);
            marshaller.marshal(listPies, file);
            //marshaller.marshal(pies, System.out);


    }



    public static void selctPies() throws SQLException {
        Statement statement = connection.createStatement();

        String queryString = "select * from Pies ";
        ResultSet rs = statement.executeQuery(queryString);
        System.out.println("Pies");
        System.out.println("ID"+"\t "+"Imie"+"\t "+"Rasa"+"\t "+"Wiek");
        while (rs.next()) {
            System.out.println(rs.getString(1)+"\t "+rs.getString(2)+"\t \t"+rs.getString(3)+"\t "+rs.getString(4));
        }

        System.out.println(" ");
    }
    public static void selctOsoba() throws SQLException {
        Statement statement = connection.createStatement();
        System.out.println(" Osoba");
        System.out.println("ID"+"\t "+"Imie"+"\t "+"Nazwisko"+"\t "+"ID_psa");
        String queryString = "select * from Osoba ";
        ResultSet rs = statement.executeQuery(queryString);
        while (rs.next()) {
            System.out.println(rs.getString(1)+"\t "+rs.getString(2)+"\t"+rs.getString(3)+"\t "+rs.getString(4));
        }


    }

    public static void  dodajPies( ) throws SQLException {
        Statement statement = connection.createStatement();

        String queryString = "select max(ID) from Pies ;";
        ResultSet   rs = statement.executeQuery(queryString);


        String i="0";
        while (rs.next()) {
            //System.out.println(rs.getString(1));
            i=rs.getString(1);
        }
     //   System.out.println(i);
        if(i!=null){
        id=Integer.parseInt(i);
        id++;
        i=String.valueOf(id);
        }
        else {i="1";}
        //System.out.println(i);
        Random random=new Random();
            queryString = "insert into Pies (ID,Imie,Rasa,Wiek) values(?,?,?,?);";
             PreparedStatement stmt = connection.prepareStatement(queryString);
            stmt.setString(1, i);
            stmt.setString(2, name[random.nextInt(name.length)]);//imie
            stmt.setString(3, rasa[random.nextInt(rasa.length)]);
            stmt.setString(4, String.valueOf(random.nextInt(8)+1));
            stmt.executeUpdate();


      /*   queryString = "INSERT INTO Osoba (Imie,Nazwisko)"+"Values(?,?)";
         stmt = connection.prepareStatement(queryString);
        stmt.setString(1, imie);
        stmt.setString(2, nazwiso);
        stmt.executeUpdate();
*/


    }
    public static void  dodajOsoba( ) throws SQLException {
        Statement statement = connection.createStatement();



        String queryString = "select max(ID) from Osoba ;";
        ResultSet   rs = statement.executeQuery(queryString);


        String i="0";
        while (rs.next()) {
            //System.out.println(rs.getString(1));
            i=rs.getString(1);
        }
      //  System.out.println(i);
        if(i!=null){
            id=Integer.parseInt(i);
            id++;
            i=String.valueOf(id);
        }
        else {i="1";}

        String queryString2 = "select max(ID) from Pies ;";
        ResultSet   rs2 = statement.executeQuery(queryString2);


        String ii="0";
        while (rs2.next()) {
            //System.out.println(rs.getString(1));
            ii=rs2.getString(1);
        }
       System.out.println("id"+ii);
       // if(ii==null){

       // }

        //System.out.println(Integer.parseInt(ii));
         queryString = "INSERT INTO Osoba (ID,Imie,Nazwisko,ID_psa)"+"Values(?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(queryString);
        Random random=new Random();
        stmt.setString(1, i);
        stmt.setString(2, imie[random.nextInt(imie.length)]);
        stmt.setString(3, nazw[random.nextInt(nazw.length)]);
        if(Integer.parseInt(ii)>2){
        stmt.setString(4, String.valueOf(1+random.nextInt(Integer.parseInt(ii)-1)));}
        else{stmt.setString(4, ii);}
        stmt.executeUpdate();



    }
    public static void  UsunOsoba(String id) throws SQLException {
        Statement statement = connection.createStatement();

        String queryString = "select max(ID) from Pies ;";
        ResultSet rs = statement.executeQuery(queryString);


        String i = "s";
        while (rs.next()) {
            //System.out.println(rs.getString(1));
            i = rs.getString(1);
        }

        if (i!=null && Integer.parseInt(id)<=Integer.parseInt(i)) {
            queryString = "delete from Osoba where ID_psa=(?)";
            PreparedStatement stmt = connection.prepareStatement(queryString);
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }
    public static void  UsunOsoba() throws SQLException {
        Statement statement = connection.createStatement();

        String queryString = "select max(ID) from Osoba ;";
        ResultSet rs = statement.executeQuery(queryString);


        String i = "s";
        while (rs.next()) {
            //System.out.println(rs.getString(1));
            i = rs.getString(1);
        }

        if (i!=null && !i.equals("s")) {
            queryString = "delete from Osoba where ID=(?)";
            PreparedStatement stmt = connection.prepareStatement(queryString);
            stmt.setString(1, i);
            stmt.executeUpdate();
        }
    }
        public static void  UsunPies() throws SQLException {
            Statement statement = connection.createStatement();

            String queryString = "select max(ID) from Pies ;";
            ResultSet   rs = statement.executeQuery(queryString);


            String i="s";
            while (rs.next()) {
                //System.out.println(rs.getString(1));
                i=rs.getString(1);
            }
            if(i!=null &&!i.equals("s")) {

               UsunOsoba(i);

                System.out.println(i);
                queryString = "delete from Pies where ID=(?)";
                PreparedStatement     stmt = connection.prepareStatement(queryString);
                stmt.setString(1, i);
                stmt.executeUpdate();
            }


    }
    public static void  Mod(String p, String pp) throws SQLException {
        Statement statement = connection.createStatement();


        String queryString = "update  Pies set Imie=(?) where ID=(?)";
        PreparedStatement stmt = connection.prepareStatement(queryString);
        stmt.setString(1, p);
        stmt.setString(2, pp);
        stmt.executeUpdate();

    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, JAXBException, IOException {

        //Connection connection=null;
        String userName = "sa";
        String password = "kasia";

        String url = "jdbc:sqlserver://LENOVO\\SERVERSQL;databaseName=Kolory";

       // String url = "jdbc:sqlserver://LENOVO\\SERVERSQL;databaseName=Paczka;integratedSecurity=true";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
             connection= DriverManager.getConnection(url, userName, password);



        if(connection!=null)
        {
            System.out.println("connection established");

           // Statement statement = connection.createStatement();
         //  dodajPies();
           // dodajOsoba();dodajOsoba();dodajOsoba();
           // dodajOsoba();
          // UsunPies();UsunPies();UsunPies();

          // UsunOsoba();
           // Mod("ll","0");
          // UsunPies();
           // Import();
            //selctPies();
           // selctOsoba();

            //export();
            System.out.println("1-select Pies");
            System.out.println("2-select Osoba");
            System.out.println("3-dodajPies");
            System.out.println("4-dodaj Osoba");
            System.out.println("5-usunPies");
            System.out.println("6-usun Osoba");
            System.out.println("7-export");
            System.out.println("8-import");
            Scanner sc = new Scanner(System.in);
            String buf;


            while(true)
            { buf = sc.nextLine();
                System.out.println(buf);

                switch (Integer.parseInt(buf)){

                    case 1:
                        selctPies();break;
                    case 2:
                        selctOsoba();break;
                    case  3:
                        dodajPies();break;
                    case 4:
                        dodajOsoba();break;
                    case 5:
                        UsunPies();break;
                    case 6:
                        UsunOsoba();break;
                    case 7:
                        export();break;
                    case 8:
                        Import();break;

                        default:break;


                }


            }


        }

        else
        {
            System.out.println("connection not established");
        }
    }

}
