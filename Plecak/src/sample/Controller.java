package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import p1.*;

import java.awt.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class that controls and initializes application
 */
public class Controller implements Initializable {

    private int n=0;
    /**
     * instance of problem
     */
    private Instance inst;
    private ArrayList<Item>p=new ArrayList<>();
    /**
     * TextField that sets the maximum size of backpack
     */
    @FXML private TextField size;
    @FXML private Label labelSize;
    @FXML private Label labelWeight;
    @FXML private Label labelValue;
    /**
     * Button that sets the size of instance of problem
     */
    @FXML private Button buttonSet;
    /**
     * Button that adds new item in table which shows all the instance's items
     */
    @FXML private Button buttonAdd;
    /**
     * ChoiceBox in which we can choose  the algorithm that solves the problem
     */
    @FXML private ChoiceBox alg;
    /**
     * TextField that sets the weight of the item
     */
    @FXML private TextField weight;
    /**
     * TextField that sets the value of the item
     */
    @FXML private TextField value;
    /**
     * TextField that shows values of all items that was chosen as solution
     */
    @FXML private Label valueOfSolution;
    /**
     * TextField that shows weights of all items that was chosen as solution
     */
    @FXML private Label weightOfSolution;
    /**
     * table of all created items
     */
    @FXML private TableView<Row> tabItems;
    /**
     * column showing weights of all created items
     */
    @FXML private TableColumn<Row, Integer> weightC;
    /**
     * column showing values of all created items
     */
    @FXML private TableColumn<Row, String> valueC;
    /**
     * column showing numbers of rows in the table containing all created items
     */
    @FXML private TableColumn<Row, String> nrC;
    /**
     * table of items chosen as solution to the problem
     */
    @FXML private TableView<Row> tableView;
    /**
     * column showing weights of items chosen as solution to the problem
     */
    @FXML private TableColumn<Row, Integer> weightColumn;
    /**
     * column showing values of items chosen as solution to the problem
     */
    @FXML private TableColumn<Row, String> valueColumn;
    /**
     * column showing numbers of rows in the table showing items chosen as solution to the problem
     */
    @FXML private TableColumn<Row, String> numberColumn;
    /**
     * List of all created items
     */
    private ObservableList<Row> itemList;
    /**
     * List of items that was chosen as a solution
     */
    private ObservableList<Row> solutionList;
    /**
     * menu item that contains menuItem Close
     */
    @FXML private MenuItem menuFile;
    /**
     * menu item that allows us to choose language
     */
    @FXML private MenuItem menuLanguage;
    /**
     * menu item that allows us to close a application
     */
    @FXML private MenuItem menuClose;
    /**
     * label that shows a actual date
     */
    @FXML private Label labelDate;
    /**
     * label that shows sum of values of item chosen as solution
     *
     */
    @FXML private Label sumOfValue;
    /**
     * label that shows sum of weights of item chosen as solution
     */
    @FXML private Label sumOfWeight;

    @FXML private Label labelLocal;
    /**
     * actual date
     */
    private Date date;
    /**
     * format of date
     */
    private DateFormat df;
    private Locale locale;
    private Algorytm a;
    /**
     * length of date format we want to use
     */
    private int style = DateFormat.SHORT;
    int y=1;
    /**
     * format of currency
     */
    private NumberFormat currencyFormatter;
    /**
     * status showing which language we want to use
     */
    private int status=0;//0-pl,eng-1;
    private String pl="przedmiot",en="item";


    /**
     * method which supports button operation of adding new item to list of them and places them in the table of items
     * @param event button with text "add item" that serves to add item to the table
     */
    @FXML
    public void AddItem(ActionEvent event)
    {


       double d=Double.parseDouble(value.getText());int in=Integer.parseInt(weight.getText());


        Item i =new Item(in,d);
        inst.items.add(i);
        p.add(i);Row r;

        switch (status){
            case 0:
                r=new Row( Integer.toString(y)+" "+pl,in,currencyFormatter.format(d));itemList.add(r);
                break;

            case 1:
                 r=new Row( Integer.toString(y)+" "+en,in,currencyFormatter.format(d));itemList.add(r);
                break;

                default:break;
        }
        y++;

    }

    /**
     * method that creates new instane of problem
     * @param event button with text "set" that setting size of backpack
     */
    @FXML
    public void setSize(ActionEvent event)
    {
        inst=new Instance(Integer.parseInt(size.getText()));
    }

    /**
     * method that closes application
     * @param event manuitem loacted in menuItem file
     */
    @FXML
    public void closeApp(ActionEvent event){
        Platform.exit();
        System.exit(0);
    }


    /**
     * method that set locale of Poland(language)
     * @param event manuitem loacted in menuItem language
     */
    @FXML
    public void LoacaleUS (ActionEvent event){

        status=1;
        sumOfValue.setText("sum of items values");
        sumOfWeight.setText("sum of items weights");
        weightC.setText("weight");
       valueC.setText("value");
        weightColumn.setText("weight");
        valueColumn.setText("value");
        menuFile.setText("File");
        menuLanguage.setText("Languages");
        menuClose.setText("Close");
        buttonAdd.setText("add item");
        buttonSet.setText("set");
        labelValue.setText("value");
        labelWeight.setText("weight");
        labelSize.setText("size");
        df = DateFormat.getDateInstance(style, Locale.US);
        labelDate.setText(df.format(new Date()));

        locale=new Locale.Builder().setLanguage("en").setRegion("US").build();
        labelLocal.setText(locale.getDisplayName());
        currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        valueOfSolution.setText(currencyFormatter.format(a.getWartoscSum()));
        itemList.clear();
        for(int i=0;i<p.size();i++)
        {

            itemList.add(new Row(Integer.toString(i+1)+" "+en,p.get(i).getWaga(),currencyFormatter.format(p.get(i).getWartosc())));

        }
        if(solutionList.size()>0 ){
            solutionList.clear();
            for(int i=0;i<a.bestt.size();i++)
            {

                solutionList.add(new Row(Integer.toString(i+1)+" "+en,a.bestt.get(i).getWaga(),currencyFormatter.format(a.bestt.get(i).getWartosc())));

            }
        }

    }

    /**
     * method that set locale of US(language)
     * @param event manuitem loacted in menuItem language
     */
    @FXML
    public void LoacalePL (ActionEvent event){

        status=0;
        sumOfValue.setText("suma wartości przedmiotow");
        sumOfWeight.setText("suma wag przedmiotow");
        weightC.setText("waga");
        valueC.setText("wartosc");
        weightColumn.setText("waga");
        valueColumn.setText("wartosc");
        menuFile.setText("Pliki");
        menuLanguage.setText("Jezyki");
        menuClose.setText("Zamknij");
        buttonAdd.setText("dodaj przedmiot");
        buttonSet.setText("ustaw");
        labelValue.setText("wartość");
        labelWeight.setText("waga");
        labelSize.setText("rozmiar");
        locale=new Locale("pl");
        df = DateFormat.getDateInstance(style, locale);
        labelDate.setText(df.format(new Date()));
        labelLocal.setText(locale.getDisplayName());
        currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        valueOfSolution.setText(currencyFormatter.format(a.getWartoscSum()));
        itemList.clear();
        for(int i=0;i<p.size();i++)
        {

            itemList.add(new Row(Integer.toString(i+1)+" "+pl,p.get(i).getWaga(),currencyFormatter.format(p.get(i).getWartosc())));

        }

        if(solutionList.size()>0){
            solutionList.clear();
            for(int i=0;i<a.bestt.size();i++)
            {

                solutionList.add(new Row(Integer.toString(i+1)+" "+pl,a.bestt.get(i).getWaga(),currencyFormatter.format(a.bestt.get(i).getWartosc())));

            }
        }
    }

    /**
     * method that set locale of Great Britain(language)
     * @param event manuitem loacted in menuItem language
     */
    @FXML
    public void LoacaleGB (ActionEvent event){

        status=1;
        sumOfValue.setText("sum of items values");
        sumOfWeight.setText("sum of items weights");
        weightC.setText("weight");
        valueC.setText("value");
        weightColumn.setText("weight");
        valueColumn.setText("value");
        menuFile.setText("File");
        menuLanguage.setText("Languages");
        menuClose.setText("Close");
        buttonAdd.setText("add item");
        buttonSet.setText("set");
        labelValue.setText("value");
        labelWeight.setText("weight");
        labelSize.setText("size");
        df = DateFormat.getDateInstance(style, Locale.UK);
        labelDate.setText(df.format(new Date()));
        locale=new Locale.Builder().setLanguage("en").setRegion("GB").build();
        labelLocal.setText(locale.getDisplayName());
        currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        valueOfSolution.setText(currencyFormatter.format(a.getWartoscSum()));

        itemList.clear();
        for(int i=0;i<p.size();i++)
        {

            itemList.add(new Row(Integer.toString(i+1)+" "+en,p.get(i).getWaga(),currencyFormatter.format(p.get(i).getWartosc())));

        }

        if(solutionList.size()>0){
        solutionList.clear();
        for(int i=0;i<a.bestt.size();i++)
        {

            solutionList.add(new Row(Integer.toString(i+1)+" "+en,a.bestt.get(i).getWaga(),currencyFormatter.format(a.bestt.get(i).getWartosc())));

        }
        }
    }

    /**
     * method that serves to solve the problem and place the chosen as the solution items in the table that shows the solution
     * @param event button  with text OK
     */
    @FXML
    public void solve (ActionEvent event)
    {
         a=new BF();
        if(alg.getValue()=="Brute Force"){a=new BF();}
        if(alg.getValue()=="Greedy"){a=new Greed();}
        if(alg.getValue()=="Random"){a=new RSearch();}
        a.inst = this.inst;

        a.GetSolution();
        solutionList.clear();

        for(int j=0;j<a.bestt.size();j++)
        {

            switch (status){
                case 0:
                    solutionList.add(new Row( Integer.toString(j+1)+" "+pl,a.bestt.get(j).getWaga(),currencyFormatter.format(a.bestt.get(j).getWartosc())));

                    break;

                case 1:
                    solutionList.add(new Row( Integer.toString(j+1)+" "+en,a.bestt.get(j).getWaga(),currencyFormatter.format(a.bestt.get(j).getWartosc())));

                    break;

                default:break;
            }



        }
        valueOfSolution.setText(currencyFormatter.format(a.getWartoscSum()));
        weightOfSolution.setText(Double.toString(a.getWagaSum()));
        this.inst.items.clear();
        for(int j=0;j<p.size();j++)
        {
            inst.items.add(p.get(j));

        }
    }

    /**
     * method initizes the application
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        alg.getItems().add("Brute Force");
        alg.getItems().add("Greedy");
        alg.getItems().add("Random");

        locale=new Locale("pl");
        date = new Date();
        df = DateFormat.getDateInstance(style, locale);
        labelDate.setText(df.format(new Date()));
        labelLocal.setText(locale.getDisplayName());
        currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        p=new ArrayList<>();
        itemList= FXCollections.observableArrayList() ;
        solutionList= FXCollections.observableArrayList() ;


        nrC.setCellValueFactory(new PropertyValueFactory<Row,String>("nrS"));
        valueC.setCellValueFactory(new PropertyValueFactory<Row,String>("valueS"));
        weightC.setCellValueFactory(new PropertyValueFactory<Row,Integer>("weightS"));
        tabItems.setItems(itemList);

        numberColumn.setCellValueFactory(new PropertyValueFactory<Row,String>("nrS"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<Row,String>("valueS"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<Row,Integer>("weightS"));
        tableView.setItems(solutionList);

    }
}
