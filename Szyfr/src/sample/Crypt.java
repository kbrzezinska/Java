
package sample;

import javax.crypto.Cipher;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Crypt {

    public PublicKey pub;
    public PrivateKey priv;
    KeyPair keyPair;

    public void gen() throws NoSuchAlgorithmException {


        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair keyPair = kpg.generateKeyPair();
        pub=keyPair.getPublic();
        priv=keyPair.getPrivate();
    }

    public void saveKeys()
    {
        try (FileOutputStream out = new FileOutputStream("szyfr" + ".key")) {
            out.write(priv.getEncoded());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream out = new FileOutputStream("szyfr" + ".pub")) {
            out.write(pub.getEncoded());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public   void encrypt() throws Exception {

        byte buf[]= Files.readAllBytes(new File("normal.txt").toPath());

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, priv);
        byte[] encrypt=cipher.doFinal(buf);

        FileOutputStream out = new FileOutputStream("zaszyfrowany.txt") ;
            out.write(encrypt);
            out.close();


    }
    public   void descrypt() throws Exception {

       byte buf[]= Files.readAllBytes(new File("zaszyfrowany.txt").toPath());
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, pub);
        byte[] encrypt=cipher.doFinal(buf);

        FileOutputStream out = new FileOutputStream("zaszyfrowany.txt") ;
        out.write(encrypt);
        out.close();


    }

    public void restore() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {

        byte[] bytes = Files.readAllBytes(Paths.get("szyfr" + ".pub"));
        X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
         pub = kf.generatePublic(ks);

        byte[] bytess = Files.readAllBytes(Paths.get("szyfr" + ".key"));
        PKCS8EncodedKeySpec kss = new PKCS8EncodedKeySpec(bytess);
        KeyFactory kff = KeyFactory.getInstance("RSA");
        priv = kff.generatePrivate(kss);
    }
    public Crypt()
    {

    }
}
