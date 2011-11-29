/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author lucas
 */
public class TeamStatus {

    private int idBind;
    private boolean confirmed;

    public TeamStatus(String idBind, String confirmed) {
        this.idBind = Integer.valueOf(idBind);
        if (confirmed.contains("false") || confirmed.contains("0")) {
            this.confirmed = false;
        } else {
            this.confirmed = true;
        }
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public int getIdBind() {
        return idBind;
    }
}
