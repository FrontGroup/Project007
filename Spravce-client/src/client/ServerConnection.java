package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Trida pro komunikaci se serverem.
 * @author lucas
 */
public class ServerConnection {

    protected static ServerConnection instance = null;
    private static int port = 1001;//cislo portu, na kterem server komunikuje
    private static String host = "localhost";//adresa serveru
    private Socket server = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public static ServerConnection getInstance() {
        if (instance == null) {
            instance = new ServerConnection();
        }
        return instance;
    }

    /**
     * Konstruktor.
     */
    private ServerConnection() {
    }

    public String connect(String id, String pass) {
        try {

            server = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            out = new PrintWriter(server.getOutputStream(), true);
            if (pass == null && id == null) {
                //test pripojeni
                out.println("TEST");
                return in.readLine();
            } else {
                //pripojeni konkretniho uzivatele k serveru
                out.println("LOGIN " + id + " " + pass);
                return in.readLine();
            }

        } catch (UnknownHostException ex) {
            System.err.println("Server is unavailable!");
            return "KO Server is unavailable!";
        } catch (IOException ex) {
            System.err.println("IO-Exception by starting!");
            return "KO IO-Exception by starting!";
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
