package Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import Compte.Compte;

public class ClientSocket {

	private OutputStream out;
    private InputStream in;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 1010;
    private Socket me;

    public ClientSocket() {
        try {
            me = new Socket(HOST, PORT);
            out = me.getOutputStream();
            in = me.getInputStream();
            out.write(20);
            ObjectInputStream objectIs = new ObjectInputStream(in);
            Compte cmpt = null;
            try {
            	cmpt = (Compte) objectIs.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(cmpt);
            objectIs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
