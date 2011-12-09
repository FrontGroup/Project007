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
public class SourceItem implements SourceItemInt {

    private HashMap<Integer, Item> data = null;
    ServerConnectionInterface sc;

    public SourceItem() {
        sc = ServerConnection.getInstance();
    }

    public SourceItem(ServerConnectionInterface sc) {
        this.sc = sc;
    }

    @Override
    public String loadData() {
        //ServerConnection sc = ServerConnection.getInstance();
        String response = sc.sendMSG("GET_ITEMS");
        if (response.startsWith("KO")) {
            return response;
        }
        data = new HashMap<Integer, Item>();
        String[] items = response.split(";");
        for (int i = 0; i < items.length; i++) {
            String[] item = items[i].split(" ");
            data.put(Integer.valueOf(item[0]), new Item(Integer.valueOf(item[0]), item[1]));
        }

        return "OK";
    }

    @Override
    public Item getItem(int id) {
        return data.get(id);
    }

    @Override
    public String addItem(Item item) {
        //ServerConnection sc = ServerConnection.getInstance();
        String response = sc.sendMSG("ADD_ITEM " + item.getName());
        if (response.startsWith("KO")) {
            return response;
        }
        return "OK";
    }

    @Override
    public String delItem(int id) {
        //ServerConnection sc = ServerConnection.getInstance();
        String response = sc.sendMSG("DEL_ITEM " + id);
        if (response.startsWith("KO")) {
            return response;
        }
        return "OK";
    }

    @Override
    public String updateItem(int id, Item item) {
        //ServerConnection sc = ServerConnection.getInstance();
        String response = sc.sendMSG("UPDATE_ITEM " + id + " " + item.getName());
        if (response.startsWith("KO")) {
            return response;
        }
        return "OK";
    }

    @Override
    public HashMap<Integer, Item> getAllItems() {
        return data;
    }
}
