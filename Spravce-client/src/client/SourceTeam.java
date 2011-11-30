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
public class SourceTeam implements SourceTeamInt {

    private HashMap<Integer, Team> data = null;

    public SourceTeam() {
    }

    @Override
    public String loadData() {
        ServerConnection sc = ServerConnection.getInstance();
        String response = sc.sendMSG("GET_TEAMS");
        if (response.startsWith("KO")) {
            return response;
        }
        data = new HashMap<Integer, Team>();
        String[] teams = response.split(";");
        for (int i = 0; i < teams.length; i++) {
            String[] team = teams[i].split(" ");
            data.put(Integer.valueOf(team[0]), new Team(Integer.valueOf(team[0]), team[1], team[2], team[3], team[4], team[5], team[6]));
        }
        return "OK";
    }

    @Override
    public Team getTeam(int id) {
        return data.get(id);
    }

    @Override
    public String addTeam(Team team) {
        ServerConnection sc = ServerConnection.getInstance();
        String response = sc.sendMSG("ADD_TEAM " + team.getPm() + " " + team.getName() + " " + team.getProject() + " " + team.getInfo() + " " + team.getGoal());
        if (response.startsWith("KO")) {
            return response;
        }
        return "OK";
    }

    @Override
    public String delTeam(int id) {
        ServerConnection sc = ServerConnection.getInstance();
        String response = sc.sendMSG("DEL_TEAM " + id);
        if (response.startsWith("KO")) {
            return response;
        }
        return "OK";
    }

    @Override
    public String updateTeam(int id, Team team) {
        ServerConnection sc = ServerConnection.getInstance();
        String response = sc.sendMSG("UPDATE_TEAM " + id + " " + team.getPm() + " " + team.getName() + " " + team.getProject() + " " + team.getInfo() + " " + team.getGoal());
        if (response.startsWith("KO")) {
            return response;
        }
        return "OK";
    }

    @Override
    public HashMap<Integer, Team> getAllTeams() {
        return data;
    }
}
