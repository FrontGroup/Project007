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

        if (msg.startsWith("TEST")) {
            //uzivatel testuje pripojeni
            return "OK";
        }
        if (msg.startsWith("LOGIN")) {
            //uzivatel se pripojuje (LOGIN ID PASS)
            String id = msg.substring(2);
            String pass = msg.substring(3);
            String response = dbc.login(id, pass);
            return response;
        }
        if (msg.startsWith("INFO")) {
            //zadost o atributy profilu uzivatele
            String id = msg.substring(2);
            String response = dbc.getInfo(id);
            return response;
        }
        if (msg.startsWith("ADD")) {
            //zadost o pridani uzivatele
            String name = msg.substring(2);
            String lastname = msg.substring(3);
            int role = Integer.valueOf(msg.substring(4));
            int group = Integer.valueOf(msg.substring(5));
            String pass = msg.substring(6);
            String response = dbc.addUser(name, lastname, role, group, pass);
            return response;
        }
        if (msg.startsWith("DEL")) {
            //zadost o smazani uzivatele
            String id = msg.substring(2);
            String response = dbc.delUser(id);
            return response;
        }
        if (msg.startsWith("CHANGE_PASS")) {
            //zadost o zmenu hesla uzivatele
            String id = msg.substring(2);
            String oldPass = msg.substring(3);
            String newPass = msg.substring(4);
            String response = dbc.changePass(id, oldPass, newPass);
            return response;
        }
        if (msg.startsWith("ADMIN_CHANGE_PASS")) {
            //zadost o zmenu hesla uzivatele pres admina
            String id = msg.substring(2);
            String newPass = msg.substring(3);
            String response = dbc.adminChangePass(id, newPass);
            return response;
        }
        return "";
    }
}
