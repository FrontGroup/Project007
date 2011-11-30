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
public interface SourceTeamInt {

    //metoda, ktera naplni tridu vsemi teamy
    //vraci OK nebo KO chyba
    public String loadData();

    public Team getTeam(int id);

    public String addTeam(Team team);

    public String delTeam(int id);

    public String updateTeam(int id, Team team);
    
    public HashMap<Integer,Team> getAllTeams();
}
