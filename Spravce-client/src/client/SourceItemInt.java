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

    //metoda, ktera aktualizuje data v databazi
    //vraci OK nebo KO chyba
    public String updateData();

    public Item getItem(int id);

    public void addItem(Item item);

    public void delItem(int id);
}
