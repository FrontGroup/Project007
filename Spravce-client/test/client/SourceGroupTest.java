/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import org.junit.Ignore;
import java.util.HashMap;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author radim
 */
public class SourceGroupTest {

    ServerConnection sc;

    public SourceGroupTest() {
        sc = ServerConnection.getInstance();
        String ret = sc.connect("666", "admin");
        if (!ret.equals("ADMIN")) {
            fail("Failed to connect to server: " + ret);
        }
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of loadData method, of class SourceGroup.
     */
    @Test
    @Ignore
    public void testLoadData() {
        System.out.println("loadData");
        SourceGroup instance = new SourceGroup();
        String expResult = "";
        String result = instance.loadData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroup method, of class SourceGroup.
     */
    @Test
    @Ignore
    public void testGetGroup() {
        System.out.println("getGroup");
        int id = 0;
        SourceGroup instance = new SourceGroup();
        Group expResult = null;
        Group result = instance.getGroup(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addGroup method, of class SourceGroup.
     */
    @Test
    @Ignore
    public void testAddGroup() {
        System.out.println("addGroup");
        Group group = null;
        SourceGroup instance = new SourceGroup();
        String expResult = "";
        String result = instance.addGroup(group);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delGroup method, of class SourceGroup.
     */
    @Test
    @Ignore
    public void testDelGroup() {
        System.out.println("delGroup");
        int id = 0;
        SourceGroup instance = new SourceGroup();
        String expResult = "";
        String result = instance.delGroup(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateGroup method, of class SourceGroup.
     */
    @Test
    @Ignore
    public void testUpdateGroup() {
        System.out.println("updateGroup");
        int id = 0;
        Group group = null;
        SourceGroup instance = new SourceGroup();
        String expResult = "";
        String result = instance.updateGroup(id, group);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllGroups method, of class SourceGroup.
     */
    @Test
    //@Ignore
    public void testGetAllGroups() {
        System.out.println("getAllGroups");
        SourceGroup instance = new SourceGroup();
        instance.loadData();

        HashMap expResult = null;
        HashMap result = instance.getAllGroups();
        assertNotNull(result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
