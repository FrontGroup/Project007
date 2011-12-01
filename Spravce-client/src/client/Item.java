/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Lukášek
 */
public class Item {

    private int id;
    private String name;
    private boolean state;

    public Item(String name, String s) {
        this.name = name;
        if (s.contains("false") || s.contains("0")) {
            state = false;
        } else {
            state = true;
        }
    }

    public Item(String name) {
        this.name = name;
        this.state = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
