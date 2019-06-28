package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.soap.SOAPException;

public class Main4 extends Application {
    static Connection manager;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle(manager.name);
        primaryStage.setScene(new Scene(root, 435, 400));
        primaryStage.show();
    }


    public static void main(String[] args) throws SOAPException {

         /*args[0]="A" ;//-> Name
         args[1]="6666" ;//-> Listn.Port
        args[2] ="6667";//-> Connect Port
         args[3]="127.0.0.1" ;//-> Host*/
        manager=new Connection("d","6669","6666","127.0.0.1");
        manager.start();
        launch(args);
        launch(args);
    }
}