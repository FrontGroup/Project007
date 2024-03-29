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
    private HashMap<Integer, TeamStatus> users = null;
    ServerConnectionInterface conn;

    public SourceTeam() {
        conn = ServerConnection.getInstance();
    }

    SourceTeam(ServerConnectionInterface sc) {
        conn = sc;
    }

    @Override
    public String loadDataFromPM(int idPM) {
        ServerConnectionInterface sc = conn;
        String response = sc.sendMSG("GET_PM_TEAMS " + idPM);
        if (response.startsWith("KO")) {
            return response;
        }
        data = new HashMap<Integer, Team>();
        String[] teams = response.split(";");
        for (int i = 0; i < teams.length; i++) {
            String[] team = teams[i].split(" ");
            data.put(Integer.valueOf(team[0]), new Team(Integer.valueOf(team[0]), Integer.valueOf(team[1]), team[2], team[3], team[4], team[5], team[6]));
        }
        return "OK";
    }

    @Override
    public String loadData() {
        ServerConnectionInterface sc = conn;
        String response = sc.sendMSG("GET_TEAMS");
        if (response.startsWith("KO")) {
            return response;
        }
        data = new HashMap<Integer, Team>();
        String[] teams = response.split(";");
        for (int i = 0; i < teams.length; i++) {
            String[] team = teams[i].split(" ");
            data.put(Integer.valueOf(team[0]), new Team(Integer.valueOf(team[0]), Integer.valueOf(team[1]), team[2], team[3], team[4], team[5], team[6]));
        }
        return "OK";
    }

    @Override
    public String loadUserStatusInTeam(int idTeam) {
        ServerConnectionInterface sc = conn;
        users = new HashMap<Integer, TeamStatus>();
        String response = sc.sendMSG("GET_TEAM_USERS " + idTeam);
        if (response.startsWith("KO")) {
            return response;
        }
        if (!response.isEmpty()) {
            String[] split = response.split(";");
            for (int i = 0; i < split.length; i++) {
                String[] s = split[i].split(" ");
                users.put(Integer.valueOf(s[0]), new TeamStatus(s[1]));
            }
        }
        return "OK";
    }

    @Override
    public Team getTeam(int id) {
        return data.get(id);
    }

    @Override
    public String addTeam(Team team) {
        //ServerConnection sc = ServerConnection.getInstance();
        String response = conn.sendMSG("ADD_TEAM " + team.getPm() + " " + team.getName() + " " + team.getProject() + " " + team.getInfo() + " " + team.getGoal());
        if (response.startsWith("KO")) {
            return response;
        }
        return "OK";
    }

    @Override
    public String delTeam(int id) {
        //ServerConnection sc = ServerConnection.getInstance();
        String response = conn.sendMSG("DEL_TEAM " + id);
        if (response.startsWith("KO")) {
            return response;
        }
        return "OK";
    }

    @Override
    public String updateTeam(int id, Team team) {
        //ServerConnection sc = ServerConnection.getInstance();
        String response = conn.sendMSG("UPDATE_TEAM " + id + " " + team.getPm()
                + " " + team.getName() + " " + team.getProject() + " "
                + team.getInfo() + " " + team.getGoal() + " " + team.isActiveToInt());
        if (response.startsWith("KO")) {
            return response;
        }
        return "OK";
    }

    @Override
    public HashMap<Integer, Team> getAllTeams() {
        return data;
    }

    @Override
    public HashMap<Integer, TeamStatus> getUserStatusInTeam() {
        return users;
    }
}
