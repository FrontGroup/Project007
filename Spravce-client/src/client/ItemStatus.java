/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author lucas
 */
public class ItemStatus {

    private int idBind;
    private boolean state;

    public ItemStatus(String idBind, String state) {
        this.idBind = Integer.valueOf(idBind);
        if (state.contains("false") || state.contains("0")) {
            this.state = false;
        } else {
            this.state = true;
        }
    }

    public int getIdBind() {
        return idBind;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
