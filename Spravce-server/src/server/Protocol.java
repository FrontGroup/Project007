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
    private Worker worker = null;

    private Protocol() {
    }

    public static Protocol getInstance() {
        if (instance == null) {
            instance = new Protocol();
        }
        return instance;
    }

    public String process(String msg) {

        if (msg.startsWith("TEST")) {
            return "OK";
        }

        if (!dbc.connect().contains("OK")) {
            return "KO Connection to DB failed!";
        }

        worker = Worker.getWorker(msg, dbc);

        String response = worker.process();

        dbc.disConnect();

        return response;
    }
}
