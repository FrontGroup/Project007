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
    //vraci OK nebo KO chyba
    public String loadData(int id);

    //metoda, ktera aktualizuje data v databazi
    //vraci OK nebo KO chyba
    public String updateData();

    //metoda, ktera vrati HashMap s dovednostmi, ktere ma uzivatel prirazen
    public HashMap<String, ItemStatus> getItems();

    //metoda, ktera vrati HashMap s tymy ve kterych je uzivatel prirazen
    public HashMap<String, TeamStatus> getTeams();

    public String setTeam(int idUser,int idTeam);

    public String delTeam(int idUser,int idTeam);

    public String setTeamConfirmed(int idTeam, boolean confirmed);
    
    public String setItemState(int idItem, boolean confirmed);
    
    public String setItemState(int idUser, int idItem, boolean state);

    public String getName();

    public String getLastname();

    public String getAddress();

    public String getCity();

    public String getEmail();

    public String getPhone();

    public String getRole();

    public String getProfessia();

    public String getGroup();

    public int getId();

    public void setName(String name);

    public void setLastName(String lastName);

    public void setAddress(String address);

    public void setCity(String city);

    public void setEmail(String email);

    public void setPhone(String phone);

    public void setProfessia(String professia);
}
