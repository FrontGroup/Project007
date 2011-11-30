/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author lucas
 */
public class Group {

    private int id;
    private String name;
    private int[] idItems;

    public Group(int id, String name, int[] idItems) {
        this.id = id;
        this.name = name;
        this.idItems = idItems;
    }

    public int[] getIdItems() {
        return idItems;
    }

    public void setIdItems(int[] idItems) {
        this.idItems = idItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getIdItemsString() {
        String s = "" + idItems.length;
        for (int i = 0; i < idItems.length; i++) {
            s += " " + idItems[i];
        }
        return s;
    }
}
