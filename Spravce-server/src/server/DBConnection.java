/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author lucas
 */
class DBConnection {


    public DBConnection() {
    }


    public String login(String id, String pass) {
        //metoda vraci ADMIN/MANAGER/HR/EMPLOYEE/KO

        return "ADMIN";//KO Not yet implemented";
    }

    String getInfo(String id) {
        //metoda vraci atributy profilu KLIC HODNOTA;KLIC HODNOTA;...
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
