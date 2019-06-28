package sample;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class MessageManager {
    public static Controller guiControler;
    public static void setControler(Controller ctr){ MessageManager.guiControler=ctr;}

    public static org.w3c.dom.Node createMessage(String messages, String reciver) throws SOAPException, IOException, ParserConfigurationException, SAXException {

        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage message = mf.createMessage();
        SOAPFactory soapFactory = SOAPFactory.newInstance();

        //body
        SOAPBody MyBody = message.getSOAPBody();
        Name bodyName = soapFactory.createName("text");
        SOAPBodyElement text = MyBody.addBodyElement(bodyName);

        Name childName = soapFactory.createName("value");
        SOAPElement value = text.addChildElement(childName);
        value.addTextNode(messages);

        //header
        SOAPHeader MyHeader = message.getSOAPHeader();

        Name hvalues = soapFactory.createName("HeaderValues","hv","no values");
        SOAPElement hvaluesElement = MyHeader.addChildElement(hvalues);

        // Header Values
        Name senderName = soapFactory.createName("Sender");
        SOAPElement sender = hvaluesElement.addChildElement(senderName);
        sender.addTextNode(Connection.host+":"+Connection.listeningPort);

        Name receiverName = soapFactory.createName("receiver");
        SOAPElement receiverElement = hvaluesElement.addChildElement(receiverName);
        receiverElement.addTextNode(reciver);
        message.writeTo(System.out);

        //////////////////////
        Source source = message.getSOAPPart().getContent();
        org.w3c.dom.Node root = null;
        if (source instanceof DOMSource) {
            root =  ((DOMSource) source).getNode();///System.out.println("NNNNNNN");
        } else if (source instanceof SAXSource) {
            InputSource inSource = ((SAXSource) source).getInputSource();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = null;

            db = dbf.newDocumentBuilder();

            Document doc = db.parse(inSource);
            root = (Node) doc.getDocumentElement();
        }
        return root;
    }

    public static void dumpDocument(org.w3c.dom.Node root) throws TransformerException, ParserConfigurationException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();

            StringWriter writer = new StringWriter();

            //transform document to string
            transformer.transform(new DOMSource(root), new StreamResult(writer));

            String xmlString = writer.getBuffer().toString();
            OutputStream os = Connection.socket.getOutputStream();
            os.write(xmlString.getBytes());
            os.flush();
            os.close();
        }
        catch (TransformerException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void createMessageFromStream(String newMessage) {
        InputStream is = new ByteArrayInputStream(newMessage.getBytes());

        try {
            if(is.available()>0) {
                SOAPMessage msg = MessageFactory.newInstance().createMessage(null, is);
                System.out.println("msg format ok");

                SOAPHeader head = msg.getSOAPHeader();
                SOAPFactory fc= SOAPFactory.newInstance();



                Node d = (Node) head.extractAllHeaderElements().next();
                Node senderNode = (Node) d.getFirstChild().getFirstChild();
                Node recNode = (Node) d.getLastChild().getFirstChild();
                String target = recNode.getNodeValue();
                String name = Connection.name;
                String sender = senderNode.getNodeValue();
                String port = Connection.host+":"+Connection.listeningPort;
                if(sender.equals(port))
                {
                    System.out.println("I received my msg");
                }
                else if(!target.equals(name) || target.equals("broadcast"))
                {
                    if(target.equals("broadcast")) {
                        String msgtext = sender+" said: ";
                        SOAPBody body = msg.getSOAPBody();
                        Node text = (Node) body.getFirstChild().getFirstChild().getFirstChild();
                        String msgBody ="";
                        if(text!=null) {
                            msgBody = text.getNodeValue();
                        }
                        msgtext+=msgBody;
                        guiControler.AddLog(msgtext);
                    }
                    Connection.sendMessage(newMessage);
                }
                else if(target.equals(name))
                {
                    String msgtext = sender+" said: ";
                    SOAPBody body = msg.getSOAPBody();
                    Node text = (Node) body.getFirstChild().getFirstChild().getFirstChild();
                    String msgBody ="";
                    if(text!=null) {
                        msgBody = text.getNodeValue();
                    }
                    msgtext+=msgBody;
                   guiControler.AddLog(msgtext);
                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
    }
}
