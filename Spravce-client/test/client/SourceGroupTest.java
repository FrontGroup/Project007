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

    public SourceGroupTest() {
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

    static class SCMockup implements ServerConnectionInterface {

        @Override
        public void close() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public String connect(String id, String pass) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        String message = null;

        @Override
        public String sendMSG(String msg) {
            message = msg;
            return "OK";
        }
    }

    /**
     * Test of addGroup method, of class SourceGroup.
     */
    @Test
    //@Ignore
    public void testAddGroup() {

        System.out.println("addGroup");
        SCMockup sc = new SCMockup();
        Group group = new Group("jmeno", new int[]{1, 2, 3});
        SourceGroup instance = new SourceGroup(sc);
        instance.addGroup(group);
        String result = instance.addGroup(group);
        assertEquals("OK", result);
        assertEquals("ADD_GROUP jmeno 3 1 2 3", sc.message);
    }

    /**
     * Test of delGroup method, of class SourceGroup.
     */
    @Test
    //@Ignore
    public void testDelGroup() {
        System.out.println("delGroup");
        int id = 1;
        SCMockup sc = new SCMockup();
        SourceGroup instance = new SourceGroup(sc);
        String result = instance.delGroup(id);
        assertEquals("OK", result);
        assertEquals("DEL_GROUP 1", sc.message);
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
    @Ignore
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
