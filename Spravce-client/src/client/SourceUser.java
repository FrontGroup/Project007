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
    private String[] allItems = null;

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
        response = sc.sendMSG("ALLITEMS");
        allItems = response.split(";");
        return "OK";
    }

    @Override
    public String updateData() {
        ServerConnection sc = ServerConnection.getInstance();
        String s = "UPDATE ";
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
    public int[] getTeams() {
        String temp = data.get("team");
        String[] split = temp.split(",");
        int[] ret = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ret[i] = Integer.valueOf(split[i]);
        }
        return ret;
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
    public String getId() {
        return data.get("id");
    }
}
