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
public class SourceGroup implements SourceGroupInt {

    HashMap<Integer, Group> data = null;
    ServerConnectionInterface conn;

    public SourceGroup() {
        conn = ServerConnection.getInstance();
    }

    /**
     * This constructor is provided for the sake of tests. TestCases can supply mockup server connections.
     * @param c
     */
    SourceGroup(ServerConnectionInterface c) {
        conn = c;
    }

    @Override
    public String loadData() {
        //ServerConnection sc = ServerConnection.getInstance();
        String response = conn.sendMSG("GET_GROUPS");
        if (response.startsWith("KO")) {
            return response;
        }
        data = new HashMap<Integer, Group>();
        String[] groups = response.split(";");
        for (int i = 0; i < groups.length; i++) {
            String[] group = groups[i].split(" ");
            int[] items = new int[Integer.valueOf(group[2])];
            for (int k = 0; k < items.length; k++) {
                items[k] = Integer.valueOf(group[3 + k]);
            }
            data.put(Integer.valueOf(group[0]), new Group(Integer.valueOf(group[0]), group[1], items));
        }
        return "OK";
    }

    @Override
    public Group getGroup(int id) {
        return data.get(id);
    }

    @Override
    public String addGroup(Group group) {
        //ServerConnection sc = ServerConnection.getInstance();
        String response = conn.sendMSG("ADD_GROUP " + group.getName() + " " + group.getIdItemsString());
        if (response.startsWith("KO")) {
            return response;
        }
        return "OK";
    }

    @Override
    public String delGroup(int id) {
        //ServerConnection sc = ServerConnection.getInstance();
        String response = conn.sendMSG("DEL_GROUP " + id);
        if (response.startsWith("KO")) {
            return response;
        }
        return "OK";
    }

    @Override
    public String updateGroup(int id, Group group) {
        //ServerConnection sc = ServerConnection.getInstance();
        String response = conn.sendMSG("UPDATE_GROUP " + group.getId() + " " + group.getName() + " " + group.getIdItemsString());
        if (response.startsWith("KO")) {
            return response;
        }
        return "OK";
    }

    @Override
    public HashMap<Integer, Group> getAllGroups() {
        return data;
    }
}
