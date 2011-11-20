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

    Iterable<JMenuItem> getMenuItems();
}
