package client;

import client.Role;
import client.ServerConnection;
import java.util.Arrays;
import javax.swing.JMenuItem;

class AdminRole implements Role {

    AdminRole(ServerConnection s) {
    }

    AdminRole() {
        
    }
    @Override
    public Iterable<JMenuItem> getMenuItems() {
        return Arrays.asList(new JMenuItem("Add user"), new JMenuItem("Delete user"), new JMenuItem("Edit user"));
    }
}
