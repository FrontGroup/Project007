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

    public SourceItem() {
    }

    @Override
    public String loadData(int id) {
        ServerConnection sc = ServerConnection.getInstance();
        String response = sc.sendMSG("GET_ITEMS");
        if (response.startsWith("KO")) {
            return response;
        }
        data = new HashMap<Integer, Item>();
        String[] items = response.split(";");
        for (int i = 0; i < items.length; i++) {
            String[] item = items[i].split(" ");
            data.put(Integer.valueOf(item[0]), new Item(item[1], item[2]));
        }
        return "OK";
    }


    @Override
    public Item getItem(int id) {
        return data.get(id);
    }

    @Override
    public String addItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String delItem(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String updateItem(int id, Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
