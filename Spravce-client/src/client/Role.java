/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.swing.JMenuItem;

/**
 *
 * @author radim
 */
public interface Role {
    // MainFrame iterates over menu items and adds them to Functions top level menu.
    // Role symbolic constant definitions. Copied from server.DBConnection.

    static final int ADMIN = 1, MANAGER = 2, HR = 3, EMPLOYEE = 4;// TODO verify EMPLOYEE with DBConnection

    Iterable<JMenuItem> getMenuItems();
}
