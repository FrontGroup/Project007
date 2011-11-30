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

    private boolean confirmed;

    public TeamStatus(String confirmed) {
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
}
