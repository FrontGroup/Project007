/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.HashMap;

/**
 *
 * @author lucas
 */
public interface SourceUserInt {

    //metoda, ktera naplni tridu daty o uzivateli s id
    public void loadData(int id);

    //metoda, ktera vrati HashMap items uzivatele, ktere ma prirazene
    public HashMap<String, Item> getItems();
    
    //metoda, ktera vrati pole id tymu, ve kterych je uzivatel
    public int[] getTeams();

    public String getName();

    public String getLastname();

    public String getAddress();

    public String getCity();

    public String getEmail();

    public String getPhone();

    public int getRole();

    public String getProfessia();
    
    public int getGroup();
    

}
