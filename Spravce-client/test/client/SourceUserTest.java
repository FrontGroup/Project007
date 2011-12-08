/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

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
     * Test of loadData method, of class SourceUser.
     */
    @Test
    public void testLoadData_int() {
        System.out.println("loadData");
        int id = 0;
        SourceUser instance = new SourceUser();
        String expResult = "";
        String result = instance.loadData(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadData method, of class SourceUser.
     */
    @Test
    public void testLoadData_0args() {
        System.out.println("loadData");
        SourceUser instance = new SourceUser();
        String expResult = "";
        String result = instance.loadData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTeam method, of class SourceUser.
     */
    @Test
    public void testSetTeam() {
        System.out.println("setTeam");
        int idUser = 0;
        int idTeam = 0;
        SourceUser instance = new SourceUser();
        String expResult = "";
        String result = instance.setTeam(idUser, idTeam);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delTeam method, of class SourceUser.
     */
    @Test
    public void testDelTeam() {
        System.out.println("delTeam");
        int idUser = 0;
        int idTeam = 0;
        SourceUser instance = new SourceUser();
        String expResult = "";
        String result = instance.delTeam(idUser, idTeam);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTeamConfirmed method, of class SourceUser.
     */
    @Test
    public void testSetTeamConfirmed() {
        System.out.println("setTeamConfirmed");
        int idUser = 0;
        int idTeam = 0;
        boolean confirmed = false;
        SourceUser instance = new SourceUser();
        String expResult = "";
        String result = instance.setTeamConfirmed(idUser, idTeam, confirmed);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setItemState method, of class SourceUser.
     */
    @Test
    public void testSetItemState() {
        System.out.println("setItemState");
        int idUser = 0;
        int idItem = 0;
        boolean state = false;
        SourceUser instance = new SourceUser();
        String expResult = "";
        String result = instance.setItemState(idUser, idItem, state);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        assertEquals("UPDATE_USER 6 Honza Krabat null null null null null" ,sc.message);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class SourceUser.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        int id = 0;
        SourceUser instance = new SourceUser();
        User expResult = null;
        User result = instance.getUser(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
