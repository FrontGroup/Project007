package server;

/**
 * Trida, ktera zpracovava a ridi komunikaci s klienty.
 * @author lucas
 */
public class Protocol {

    private static Protocol instance = null;
    String db_address = "jdbc:mysql://beretis.sh.cvut.cz/oss?"
            + "user=frontgroup&password=hesielko";
    private DBConnection dbc = new DBConnection(db_address);
    private String connect = null;

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
            connect = dbc.connect();
            if (connect.contains("OK")) {
                String[] split = msg.split(" ");
                String id = split[1];
                String pass = split[2];
                String response = dbc.login(id, pass);
                dbc.disConnect();
                return response;
            } else {
                return "KO " + connect;
            }
        }
        if (msg.startsWith("INFO")) {
            //zadost o atributy profilu uzivatele
            connect = dbc.connect();
            if (connect.contains("OK")) {
                String[] split = msg.split(" ");
                String id = split[1];
                String response = dbc.getInfo(id);
                dbc.disConnect();
                return response;
            } else {
                return "KO " + connect;
            }
        }
        if (msg.startsWith("ADD")) {
            //zadost o pridani uzivatele
            connect = dbc.connect();
            if (connect.contains("OK")) {
                String[] split = msg.split(" ");
                int role = Integer.valueOf(split[1]);
                int group = Integer.valueOf(split[2]);
                String pass = split[3];
                String response = dbc.addUser(group, pass, null, null, null, null, null, null, role);
                dbc.disConnect();
                return response;
            } else {
                return "KO " + connect;
            }
        }
        if (msg.startsWith("DEL")) {
            //zadost o smazani uzivatele
            connect = dbc.connect();
            if (connect.contains("OK")) {
                String[] split = msg.split(" ");
                String id = split[1];
                String response = dbc.deleteUser(id);
                dbc.disConnect();
                return response;
            } else {
                return "KO " + connect;
            }
        }
        if (msg.startsWith("CHANGE_PASS")) {
            //zadost o zmenu hesla uzivatele
            connect = dbc.connect();
            if (connect.contains("OK")) {
                String[] split = msg.split(" ");
                String id = split[1];
                String oldPass = split[2];
                String newPass = split[3];
                String response = dbc.changePass(id, oldPass, newPass);
                dbc.disConnect();
                return response;
            } else {
                return "KO " + connect;
            }
        }
        if (msg.startsWith("ADMIN_CHANGE_PASS")) {
            //zadost o zmenu hesla uzivatele pres admina
            connect = dbc.connect();
            if (connect.contains("OK")) {
                String[] split = msg.split(" ");
                String id = split[1];
                String newPass = split[2];
                String response = dbc.adminChangePass(id, newPass);
                dbc.disConnect();
                return response;
            } else {
                return "KO " + connect;
            }
        }
        if (msg.startsWith("GET_GROUPS")) {
            //zadost o seznam groups
            connect = dbc.connect();
            if (connect.contains("OK")) {
                String response = dbc.getGroups();
                dbc.disConnect();
                return response;
            } else {
                return "KO " + connect;
            }
        }
        return "";
    }
}
