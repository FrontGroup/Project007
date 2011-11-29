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
public class SourceUser implements SourceUserInt {

    private HashMap<String, String> data = null;
    private HashMap<String, TeamStatus> teamsStatus = null;
    private HashMap<String, ItemStatus> itemsStatus = null;

    public SourceUser() {
    }

    private void saveData(String response) {
        String[] split = response.split(";");
        for (String s : split) {
            data.put(s.substring(1), s.substring(2));
        }
    }

    @Override
    public String loadData(int id) {
        ServerConnection sc = ServerConnection.getInstance();
        String response = sc.sendMSG("INFO " + id);
        if (response.startsWith("KO")) {
            return response;
        }
        data = new HashMap<String, String>();
        saveData(response);
        data.put("id", "" + id);
        response = sc.sendMSG("GET_USER_ITEMS " + id);
        if (response.startsWith("KO")) {
            return response;
        }
        String[] split = response.split(";");
        itemsStatus = new HashMap<String, ItemStatus>();
        for (int i = 0; i < split.length; i++) {
            String[] s = split[i].split(" ");
            itemsStatus.put(s[0], new ItemStatus(s[1], s[2]));
        }
        response = sc.sendMSG("GET_USER_TEAMS " + id);
        if (response.startsWith("KO")) {
            return response;
        }
        split = response.split(";");
        teamsStatus = new HashMap<String, TeamStatus>();
        for (int i = 0; i < split.length; i++) {
            String[] s = split[i].split(" ");
            teamsStatus.put(s[0], new TeamStatus(s[1], s[2]));
        }
        return "OK";
    }

    @Override
    public String updateData() {
        ServerConnection sc = ServerConnection.getInstance();
        String s = "UPDATE_USER ";
        s += data.get("id") + " ";
        s += data.get("name") + " ";
        s += data.get("lastname") + " ";
        s += data.get("address") + " ";
        s += data.get("city") + " ";
        s += data.get("email") + " ";
        s += data.get("phone") + " ";
        s += data.get("professia");
        return sc.sendMSG(s);
    }

    @Override
    public String getName() {
        return data.get("name");
    }

    @Override
    public String getLastname() {
        return data.get("lastname");
    }

    @Override
    public HashMap<String, ItemStatus> getItems() {
        return itemsStatus;
    }

    @Override
    public String getAddress() {
        return data.get("address");
    }

    @Override
    public String getCity() {
        return data.get("city");
    }

    @Override
    public String getEmail() {
        return data.get("email");
    }

    @Override
    public String getPhone() {
        return data.get("phone");
    }

    @Override
    public String getRole() {
        return data.get("role");
    }

    @Override
    public String getProfessia() {
        return data.get("professia");
    }

    @Override
    public String getGroup() {
        return data.get("group");
    }

    @Override
    public HashMap<String, TeamStatus> getTeams() {
        return teamsStatus;
    }

    @Override
    public void setName(String name) {
        data.put("name", name);
    }

    @Override
    public void setLastName(String lastName) {
        data.put("lastname", lastName);
    }

    @Override
    public void setAddress(String address) {
        data.put("address", address);
    }

    @Override
    public void setCity(String city) {
        data.put("city", city);
    }

    @Override
    public void setEmail(String email) {
        data.put("email", email);
    }

    @Override
    public void setPhone(String phone) {
        data.put("phone", phone);
    }

    @Override
    public void setProfessia(String professia) {
        data.put("professia", professia);
    }

    @Override
    public int getId() {
        return Integer.valueOf(data.get("id"));
    }

    @Override
    public String setTeam(int idTeam) {
        ServerConnection sc = ServerConnection.getInstance();
        String response = sc.sendMSG("USER_IN_TEAM " + this.getId() + " " + idTeam);
        if (response.startsWith("KO")) {
            return response;
        }
        return "OK";
    }

    @Override
    public String delTeam(int idTeam) {
        ServerConnection sc = ServerConnection.getInstance();
        String response = sc.sendMSG("USER_OUT_TEAM " + this.getId() + " " + idTeam);
        if (response.startsWith("KO")) {
            return response;
        }
        return "OK";
    }

    @Override
    public String setTeamState(int idTeam, boolean confirmed) {
        ServerConnection sc = ServerConnection.getInstance();
        String response = sc.sendMSG("SET_TEAM_CONFIRMED " + teamsStatus.get("" + idTeam).getIdBind() + " " + confirmed);
        if (response.startsWith("KO")) {
            return response;
        }
        return "OK";
    }
}
