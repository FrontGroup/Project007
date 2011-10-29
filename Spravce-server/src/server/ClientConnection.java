package server;

import java.net.Socket;
import java.io.*;

/**
 * Trida pro vytvoreni komunikace s klientem.
 * @author lucas
 */
public class ClientConnection implements Runnable {

    private Socket client;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Protocol protocol = null;

    /**
     * Konstruktor
     * @param client Soket pres ktery se bude komunikovat.
     */
    public ClientConnection(Socket client) {
        this.client = client;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException ex) {
            System.err.println("IO-Exception BY OPENING!");
        }
    }

    @Override
    public void run() {

        String msg, response;
        protocol = Protocol.getInstance();

        try {
            while ((msg = in.readLine()) != null) {
                response = protocol.process(msg);
                out.println(response);
            }
            //klient ukoncil komunikacni socket
            System.err.println("CLIENT IS OFFLINE!");
        } catch (IOException ex) {
            //server ukoncil komunikacni socket s klientem
            System.err.println("IO-Exception BY RUNNING!");
        }
    }

    /**
     * Metoda odesila zpravu klientovi, ke kteremu je trida pripojena.
     * @param msg String, ktery bude odeslan.
     */
    public void sendMsg(String msg) {
        out.println(msg);
    }

    /**
     * Metoda ukoncuje spojeni s klientem.
     */
    public void close() {
        try {
            client.close();
        } catch (IOException ex) {
            System.err.println("IO-Exception BY CLOSING!");
        }
    }
}
