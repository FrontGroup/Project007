/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.swing.JMenuItem;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author radim
 */
public class RoleTest {

    public RoleTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    //TODO every role shares employee's capabilities
    //role: integrace do MainFrame
    //switch v LoginFrame

    @Test
    public void testRole() {
        Role r = new Role() {

            @Override
            public Iterable<JMenuItem> getMenuItems() {
                return null;
            }
        };
        Iterable<JMenuItem> menu = r.getMenuItems();
    }
}
