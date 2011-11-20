/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Arrays;
import javax.swing.JMenuItem;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author radim
 */
public class AdminRoleTest {

    public AdminRoleTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testAdminRole() {
        class AdminRole implements Role {

            AdminRole(ServerConnection s) {
            }

            @Override
            public Iterable<JMenuItem> getMenuItems() {
                return Arrays.asList(new JMenuItem("Add user"), new JMenuItem("Delete user"), new JMenuItem("Edit user"));
            }
        }
        Role r = new AdminRole((ServerConnection) null);
        Iterable<JMenuItem> is = r.getMenuItems();
        assertEquals("Add user", is.iterator().next().getText());
    }
}
