package client;

/**
 * Server connection mockup class. Enables us to intercept sent messages and forge responses to suit test needs.
 */
class SCMockup implements ServerConnectionInterface {

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String connect(String id, String pass) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SCMockup() {
    }

    public SCMockup(String cresponse) {
        response = cresponse;
    }
    String message = null;
    String response = "OK";

    @Override
    public String sendMSG(String msg) {
        message = msg;
        return response;
    }
}
