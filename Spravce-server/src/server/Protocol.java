package server;

/**
 * Trida, ktera zpracovava a ridi komunikaci s klienty.
 * @author lucas
 */
public class Protocol {

    private static Protocol instance = null;
    String db_address = "jdbc:mysql://beretis.sh.cvut.cz/oss?"
            + "user=frontgroup&password=hesielko";
    DBConnection dbc = new DBConnection(db_address);

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
            String[] split = msg.split(" ");
            String id = split[1];
            String pass = split[2];
            String response = dbc.login(id, pass);
            return response;
        }
        if (msg.startsWith("INFO")) {
            //zadost o atributy profilu uzivatele
            String[] split = msg.split(" ");
            String id = split[1];
            String response = dbc.getInfo(id);
            return response;
        }
        if (msg.startsWith("ADD")) {
            //zadost o pridani uzivatele
            String[] split = msg.split(" ");
            String name = split[1];
            String lastname = split[2];
            int role = Integer.valueOf(split[3]);
            int group = Integer.valueOf(split[4]);
            String pass = split[5];;
            String response = dbc.addUser(group, pass, name, lastname, role);
            return response;
        }
        if (msg.startsWith("DEL")) {
            //zadost o smazani uzivatele
            String[] split = msg.split(" ");
            String id = split[1];
            String response = dbc.deleteUser(id);
            return response;
        }
        if (msg.startsWith("CHANGE_PASS")) {
            //zadost o zmenu hesla uzivatele
            String[] split = msg.split(" ");
            String id = split[1];
            String oldPass = split[2];
            String newPass = split[3];
            String response = dbc.changePass(id, oldPass, newPass);
            return response;
        }
        if (msg.startsWith("ADMIN_CHANGE_PASS")) {
            //zadost o zmenu hesla uzivatele pres admina
            String[] split = msg.split(" ");
            String id = split[1];
            String newPass = split[2];
            String response = dbc.adminChangePass(id, newPass);
            return response;
        }
        return "";
    }
}
