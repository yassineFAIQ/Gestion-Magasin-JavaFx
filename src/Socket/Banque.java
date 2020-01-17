package Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;



import Compte.Compte;


public class Banque implements Serializable {
  
	private static final long serialVersionUID = 1L;
	private OutputStream outputStream;
    private InputStream inputStream;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String host = "127.0.0.1";
    private int port = 50000;
    private Socket me;
    private Compte compte;

    public Banque(Compte compte) {
        this.compte = compte;
        try {
            me = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int sendPayment() {
        try {
            outputStream = me.getOutputStream();
            inputStream = me.getInputStream();
            out = new ObjectOutputStream(outputStream);
            in = new ObjectInputStream(inputStream);
            
            
            out.writeObject(compte);
            
            out.flush();

            int rs = 0;
            try {
                rs = (Integer)in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("code : " + rs);
            return rs;
        } catch (IOException e) {
            e.printStackTrace();
            return 500;
        } finally {
            try {
                me.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
