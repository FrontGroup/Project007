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

/** Test SourceGroup - a class, that handles exchange of information between groups.
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
     * Results of this method can be indirectly observed using getGroup or getAllGroups.
     */
    @Test
    //@Ignore
    public void testLoadDataAndGetGroup() {
        System.out.println("loadDataAndAddGroup");
        SCMockup sc = new SCMockup("2 grp2name 1 3;");
        SourceGroup instance = new SourceGroup(sc);
        String result = instance.loadData();
        assertEquals("OK", result);
        assertEquals("GET_GROUPS", sc.message);

        //now test getGroup
        Group g = instance.getGroup(2);
        assertEquals("grp2name", g.getName()); // partial test is good enough

        //do the same with getAllGroups - repeat the test
        g = instance.getAllGroups().get(2);
        assertEquals("grp2name", g.getName()); // partial test is good enough
    }

    /**
     * Server connection mockup class. Enables us to intercept sent messages and forge responses to suit test needs.
     */
    static class SCMockup implements ServerConnectionInterface {

        @Override
        public void close() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public String connect(String id, String pass) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public SCMockup() {
        }

        public SCMockup(String cresponse) {
            response = cresponse;
        }
        String message = null;
        String response = "OK";

        @Override
        public String sendMSG(String msg) {
            message = msg;
            return response;
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
    //@Ignore
    public void testUpdateGroup() {
        System.out.println("updateGroup");
        SCMockup sc = new SCMockup();
        int id = 1234;
        Group group = new Group(1234, "grupen", new int[]{5, 6});
        SourceGroup instance = new SourceGroup(sc);
        String result = instance.updateGroup(id, group);
        assertEquals("OK", result);
        assertEquals("UPDATE_GROUP 1234 grupen 2 5 6", sc.message);
    }
}
