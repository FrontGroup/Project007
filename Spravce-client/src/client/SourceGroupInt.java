/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author lucas
 */
public interface SourceGroupInt {
    
    //metoda, ktera naplni tridu vsemi dostupnymi skupinami
    //vraci OK nebo KO chyba
    public String loadData();
    
    //metoda, ktera aktualizuje data v databazi
    //vraci OK nebo KO chyba
    public String updateData();
    
    public String getName(int id);
    
    public void addGroup(String name);
    
    public void delGroup(int id);
    
    
    
    
}
