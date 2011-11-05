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

    public String name;
    public boolean state;

    public Item(String name, String s) {
        this.name = name;
        if (s.contains("false")) {
            state = false;
        } else {
            state = true;
        }

    }
}
