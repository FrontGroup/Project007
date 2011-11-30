/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author lucas
 */
public interface SourceItemInt {

    //metoda, ktera naplni tridu vsemi items
    //vraci OK nebo KO chyba
    public String loadData(int id);

    public Item getItem(int id);

    public String addItem(Item item);

    public String delItem(int id);

    public String updateItem(int id, Item item);
}
