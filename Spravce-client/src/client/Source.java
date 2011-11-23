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
public class Source implements SourceInt {

    private HashMap<String, String> data = null;
    private String[] allItems = null;

    public Source() {
    }

    private void saveData(String response) {
        String[] split = response.split(";");
        for (String s : split) {
            data.put(s.substring(1), s.substring(2));
        }
    }

    @Override
    public void loadData(int id) {
        ServerConnection sc = ServerConnection.getInstance();
        String response = sc.sendMSG("INFO " + id);
        data = new HashMap<String, String>();
        saveData(response);
        response = sc.sendMSG("ALLITEMS");
        allItems = response.split(";");
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
    public HashMap<String, Item> getItems() {
        HashMap<String, Item> map = new HashMap<String, Item>();
        for (String s : allItems) {
            if (data.get(s) != null) {
                map.put(s, new Item(s, data.get(s)));
            }
        }
        return map;
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
    public int getRole() {
        return Integer.valueOf(data.get("role"));
    }

    @Override
    public String getProfessia() {
        return data.get("professia");
    }

    @Override
    public int getGroup() {
        return Integer.valueOf(data.get("group"));
    }

    @Override
    public int[] getTeams() {
        String temp = data.get("team");
        String[] split = temp.split(",");
        int[] ret = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ret[i] = Integer.valueOf(split[i]);
        }
        return ret;
    }
}
