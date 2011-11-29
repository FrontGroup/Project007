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
            data.put(Integer.valueOf(team[0]), new Team(Integer.valueOf(team[0]), null, team[1], team[2], team[3], team[4], team[5], team[6]));
        }
        return "OK";
    }

    @Override
    public String updateData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Team getTeam(int id) {
        return data.get(id);
    }

    @Override
    public void addTeam(Team team) {
        data.put(team.getId(), team);
    }

    @Override
    public void delTeam(int id) {
        data.remove(id);
    }
}
