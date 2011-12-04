/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import org.junit.Ignore;
import client.AdminRole.Pair;
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
        Role r = new AdminRole();
        Iterable<JMenuItem> is = r.getMenuItems();
        assertEquals("Add user", is.iterator().next().getText());
    }

    @Test
    //@Ignore
    public void testProcessGetGroupsResponse() {

        Pair[] p = AdminRole.processGetGroupsResponse("1 zednik;2 pridavac");
        assertEquals("pridavac", p[1].name);
    }

    @Test
    public void testPairConstructor() {
        Pair p = new Pair("1 kosmonaut");
        assertEquals(1, p.id);
        assertEquals("kosmonaut", p.name);
    }

    @Test
    public void testSplit() {
        String s = "1 zednik;2 pridavac";
        String[] expected = {"1 zednik", "2 pridavac"};
        String[] result = s.split(";");
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSplitWithLimit() {
        String s = "1 2 3 4 5";
        String[] exp = {"1", "2 3 4 5"};
        String[] res = s.split(" ", 2);
        assertArrayEquals(exp, res);
    }
}
