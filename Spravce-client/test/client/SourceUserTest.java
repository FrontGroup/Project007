/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import org.junit.Ignore;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author radim
 */
public class SourceUserTest {

    public SourceUserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of methods getUser and loadData of class SourceUser.
     */
    @Test
    //@Ignore
    public void testLoadDataAndGetUser() {
        System.out.println("LoadDataAndGetUser");
        int id = 0;
        class MultiSCMockup implements ServerConnectionInterface {

            List<String> messages = new LinkedList<String>();
            String[] responses;// = new LinkedList<String>();
            int iresp = 0;

            public MultiSCMockup(String[] cresponses) {
                responses = cresponses;
            }

            @Override
            public void close() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String connect(String id, String pass) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String sendMSG(String msg) {
                messages.add(msg);
                return responses[iresp++];
            }
        }
        MultiSCMockup sc = new MultiSCMockup(new String[]{"2;13;Jan;Jeliman;adresa;city;email;phone;professia", //reply to GET_INFO
                    "3 false;5 true;", //reply to GET_USER_ITEMS
                    "15 false;"//reply to GET_USER_TEAMS
                });
        SourceUser instance = new SourceUser(sc);
        String sret = instance.loadData(6);
        assertEquals("OK", sret);
        assertEquals("GET_INFO 6", sc.messages.get(0));

        User ures = instance.getUser(6);
        //Test several values from getUser, getTeams, getItems.
        //User uexp = new User(6, 2, 13, "Jan", "Jeliman", "adresa", "city", "email", "phone", "professia"); --equals not implemented
        assertEquals("Jan", ures.getName());
        assertEquals(13, ures.getGroup());

        Map<Integer, ItemStatus> ires = ures.getItems();
        assertEquals(2, ires.size());
        assertTrue(ires.containsKey(3));
        assertFalse(ires.get(3).isState());

        Map<Integer, TeamStatus> tres = ures.getTeams();
        assertEquals(1, tres.size());
        assertTrue(tres.containsKey(15));
    }

    /**
     * Test of setTeam method, of class SourceUser.
     */
    @Test
    //@Ignore
    public void testSetTeam() {
        System.out.println("setTeam");
        SCMockup sc = new SCMockup();
        SourceUser instance = new SourceUser(sc);
        String result = instance.setTeam(6, 17);
        assertEquals("OK", result);
        assertEquals("USER_IN_TEAM 6 17", sc.message);
    }

    /**
     * Test of delTeam method, of class SourceUser.
     */
    @Test
    //@Ignore
    public void testDelTeam() {
        System.out.println("delTeam");
        SCMockup sc = new SCMockup();
        SourceUser instance = new SourceUser(sc);
        instance.delTeam(7, 17);
        assertEquals("USER_OUT_TEAM 7 17", sc.message);
    }

    /**
     * Test of setTeamConfirmed method, of class SourceUser.
     */
    @Test
    //@Ignore
    public void testSetTeamConfirmed() {
        System.out.println("setTeamConfirmed");
        SCMockup sc = new SCMockup();
        SourceUser instance = new SourceUser(sc);
        String result = instance.setTeamConfirmed(10, 20, true);
        assertEquals("OK", result);
        assertEquals("SET_TEAM_CONFIRMED 10 20 true", sc.message);
    }

    /**
     * Test of setItemState method, of class SourceUser.
     */
    @Test
    //@Ignore
    public void testSetItemState() {
        System.out.println("setItemState");
        SCMockup sc = new SCMockup();
        SourceUser instance = new SourceUser(sc);
        String result = instance.setItemState(16, 6, false);
        assertEquals("OK", result);
        assertEquals("SET_ITEM_STATE 16 6 false", sc.message);
    }

    /**
     * Test of addUser method, of class SourceUser.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        SCMockup sc = new SCMockup();
        User user = new User(2, 3, "kocka");
        SourceUser instance = new SourceUser(sc);
        String expResult = "ADD_USER 2 3 kocka";
        String result = instance.addUser(user);
        //assertEquals(expResult, result);
        assertEquals(expResult, sc.message);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of delUser method, of class SourceUser.
     */
    @Test
    public void testDelUser() {
        System.out.println("delUser");
        SCMockup sc = new SCMockup();
        SourceUser instance = new SourceUser(sc);
        instance.delUser(44);
        assertEquals("DEL_USER 44", sc.message);
    }

    /**
     * Test of updateUser method, of class SourceUser.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        SCMockup sc = new SCMockup();
        int idUser = 0;
        User user = new User(6, 2, 3, "Honza", "Krabat", null, null, null, null, null);//new User(6, 3, "heslo", "Jmeno", "Prijmeni", "adresa", "mesto", "", "", "");
        SourceUser instance = new SourceUser(sc);
        instance.updateUser(6, user);
        assertEquals("UPDATE_USER 6 Honza Krabat null null null null null", sc.message);
    }
}
