package server;

/**
 * Trida, ktera zpracovava a ridi komunikaci s klienty.
 * @author lucas
 */
public class Protocol {

    private static Protocol instance = null;
    private DBConnection dbc = new DBConnection();

    /**
     * Konstruktor
     */
    private Protocol() {
    }

    public static Protocol getInstance() {
        if (instance == null) {
            instance = new Protocol();
        }
        return instance;
    }

    /**
     * Metoda, ktera zpracuje prijatou zpravu podle jejiho pocatku.
     * @param msg Prijata zprava.
     * @return Informace o zpracovani zpravy.
     */
    public String process(String msg) {

        if (msg.startsWith("LOGIN")) {
            //uzivatel se pripojuje (LOGIN ID PASS)
            String id = msg.substring(2);
            String pass = msg.substring(3);
            String response = dbc.login(id, pass);
            return response;
        }

        return "";
    }
}
