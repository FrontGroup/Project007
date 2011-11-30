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

    private boolean state;

    public ItemStatus(String state) {
        if (state.contains("false") || state.contains("0")) {
            this.state = false;
        } else {
            this.state = true;
        }
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
