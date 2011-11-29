/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author lucas
 */
public interface SourceTeamInt {
    
    //metoda, ktera naplni tridu vsemi teamy
    //vraci OK nebo KO chyba
    public String loadData();
    
    //metoda, ktera aktualizuje data v databazi
    //vraci OK nebo KO chyba
    public String updateData();
    
    public Team getTeam(int id);
    
    public void addTeam(Team team);
    
    public void delTeam(int id);
    
}
