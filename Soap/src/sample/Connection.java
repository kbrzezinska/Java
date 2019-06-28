package sample;

import javax.xml.soap.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection extends Thread{

    public static String listeningPort;
    public static String connectingPort;
    public static String host;
    public static String name;
    static Socket socket;
    static ServerSocket serverSocket;
    public static Connection instance;

    public Connection(String name, String listeningPort, String connectingPort, String host) throws SOAPException {

        Connection.name = name;
        Connection.listeningPort=listeningPort;
        Connection.connectingPort = connectingPort;
        Connection.host = host;
        Connection.instance = this;

    }

    static public boolean connect()
    {
        try {

            socket=new Socket(host, Integer.parseInt(connectingPort));
            System.out.println("Connected to " + connectingPort + "ready to send message");
            return true;
        } catch (IOException e) {
            System.out.println("Couldn't connect to: "+host + " port: "+ connectingPort);
            return false;
        }
    }

    static public void portListen(){

        try {
            serverSocket = new ServerSocket(Integer.parseInt(listeningPort));
            System.out.println("Listening on port " + listeningPort);
            while(!serverSocket.isClosed()) {
                Socket s = serverSocket.accept();

                //

                InputStreamReader in = new InputStreamReader(s.getInputStream());
                BufferedReader bf = new BufferedReader(in);

                String input=bf.readLine();
                String receivedMessage=input;
                while ((input = bf.readLine()) != null) {
                    receivedMessage += input;
                }
                bf.close();
                in.close();
                System.out.println("New Message arrived");
                System.out.println(receivedMessage);
                MessageManager.createMessageFromStream(receivedMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    static public boolean sendMessage(String msg)
    {
        OutputStream os = null;
        try {
            socket=new Socket(host, Integer.parseInt(connectingPort));
            os = socket.getOutputStream();
            os.write(msg.getBytes());
            os.flush();
            os.close();
            socket.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    static public boolean sendMessage(String message, String receiver)
    {
        try {
            org.w3c.dom.Node mess =  MessageManager.createMessage(message,receiver);
            MessageManager.dumpDocument(mess);
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
    public void run()
    {
        portListen();
    }
}
