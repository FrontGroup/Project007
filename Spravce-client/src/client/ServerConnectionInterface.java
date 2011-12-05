/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

/**
 *
 * @author radim
 */
public interface ServerConnectionInterface {

    void close();

    String connect(String id, String pass);

    /**
     * Metoda pro odeslani zpravy
     * @param pozadavek na server
     * @return odpoved ze serveru
     */
    String sendMSG(String msg);

}
