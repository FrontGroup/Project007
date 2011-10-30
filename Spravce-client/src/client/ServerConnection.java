package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Trida pro komunikaci se serverem.
 * @author lucas
 */
public class ServerConnection {

    private static int port = 1001;//cislo portu, na kterem server komunikuje
    private static String host = "localhost";//adresa serveru
    private Socket server = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    /**
     * Konstruktor.
     */
    public ServerConnection() {
    }

    public String connect(String id, String pass) {
        try {
            server = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            out = new PrintWriter(server.getOutputStream(), true);
            //pripojeni konkretniho uzivatele k serveru
            out.println("LOGIN " + id + " " + pass);
            return in.readLine();

        } catch (UnknownHostException ex) {
            System.err.println("Server is unavailable!");
            return "KO";
        } catch (IOException ex) {
            System.err.println("IO-Exception by starting!");
            return "KO";
        }
    }

    /**
     * Metoda pro odeslani zpravy
     * @param pozadavek na server
     * @return odpoved ze serveru
     */
    public String sendMSG(String msg) {
        try {
            out.println(msg);
            return in.readLine();
        } catch (IOException ex) {
            System.err.println("IO-Exception by running!");
            return null;
        }
    }

    public void close() {
        try {
            server.close();
        } catch (IOException ex) {
            System.err.println("IO-Exception BY CLOSING!");
        }
    }
}
