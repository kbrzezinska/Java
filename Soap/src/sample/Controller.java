package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sun.plugin2.message.Message;

import java.awt.*;

import  javafx.scene.control.*;
import static sample.Connection.connect;

public class Controller {

   // public javafx.scene.control.Label PortLabel;
    @FXML
   Button SendButton1;
    @FXML
    Button ConnectBtn;
    @FXML
    Button ListenBtn;
    @FXML
    Label PortLabel1;
    @FXML
    Label TargetLabel1;
    @FXML
    TextArea MessagesTextBox1;
    @FXML
    TextField ReceiverBox1;
    @FXML
    TextArea MessagesLog1;
    @FXML
    Label AppName1;
    @FXML
    Label MessReceived1;

    @FXML
    public void initialize() {
        SetLabels();
        MessageManager.setControler(this);
    }

    public boolean Connect() {
        boolean success = Connection.connect();
        if (success) {
            AddLog("Connection success Message send");
            System.out.println("Message send");
        } else {
            AddLog("Connection failed");
        }
        return success;

    }

    void SetLabels() {
        AppName1.setText(Connection.name);
        PortLabel1.setText(Connection.listeningPort);
        TargetLabel1.setText(Connection.connectingPort);

    }

    @FXML
   public void Send(ActionEvent event){
        String message=MessagesTextBox1.getText();
        String receiver= ReceiverBox1.getText();
        if(Connect()){
            Connection.sendMessage(message,receiver);
        }

    }
    void AddLog(String mess)
    {
        MessagesLog1.setText(MessagesLog1.getText()+mess+ "\n");
    }
}