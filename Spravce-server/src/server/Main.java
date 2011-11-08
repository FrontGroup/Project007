package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

/**
 * Main trida komunikacniho serveru.
 * @author lucas
 */
public class Main {

    private static int port = 10010;//cislo portu, pres ktery se bude komunikovat
    private static ServerSocket server = null;
    private static Socket client = null;

    public static void main(String[] args) {

        try {
            server = new ServerSocket(port);
            while (true) {
                client = server.accept();
                //vytvoreni noveho vlakna pro komunikaci s klientem
                Thread t = new Thread(new ClientConnection(client));
                t.start();
            }
        } catch (IOException ex) {
            System.err.println("ERROR CONNECTED!");
            System.exit(1);
        }
    }
}