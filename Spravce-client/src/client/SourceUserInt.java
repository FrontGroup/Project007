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
    ////vraci OK nebo KO chyba
    public String loadData(int id);

    //metoda, ktera aktualizuje data v databazi
    //vraci OK nebo KO chyba
    public String updateData();

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

    public String getRole();

    public String getProfessia();

    public String getGroup();
    
    public String getId();

    public void setName(String name);

    public void setLastName(String lastName);

    public void setAddress(String address);

    public void setCity(String city);

    public void setEmail(String email);

    public void setPhone(String phone);

    public void setProfessia(String professia);
}
