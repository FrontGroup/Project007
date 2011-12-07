/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lucas
 */
public class WorkerTest {

    public WorkerTest() {
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

    @Test
    public void testAddUser() {
        String msg = "ADD_USER 2 3 4";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("AddUser class failed", result instanceof Worker.AddUser);
        assertTrue("AddUser process failed", result.process().contains("OK"));
    }

    @Test
    public void testDelUser() {
        String msg = "DEL_USER 2";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("DelUser class failed", result instanceof Worker.DelUser);
        assertTrue("DelUser process failed", result.process().contains("OK"));
    }

    @Test
    public void testUpdateUser() {
        String msg = "UPDATE_USER 2 3 4 5 6 7 8 9";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("UpdateUser class failed", result instanceof Worker.UpdateUser);
        assertTrue("UpdateUser process failed", result.process().contains("OK"));
    }

    @Test
    public void testAddItem() {
        String msg = "ADD_ITEM 2";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("AddItem class failed", result instanceof Worker.AddItem);
        assertTrue("AddItem process failed", result.process().contains("OK"));
    }

    @Test
    public void testDelItem() {
        String msg = "DEL_ITEM 2";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("DelItem class failed", result instanceof Worker.DelItem);
        assertTrue("DelItem process failed", result.process().contains("OK"));
    }

    @Test
    public void testUpdateItem() {
        String msg = "UPDATE_ITEM 2 3";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("UpdateItem class failed", result instanceof Worker.UpdateItem);
        assertTrue("UpdateItem process failed", result.process().contains("OK"));
    }

    @Test
    public void testAddTeam() {
        String msg = "ADD_TEAM 2 3 4 5 6";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("AddTeam class failed", result instanceof Worker.AddTeam);
        assertTrue("AddTeam process failed", result.process().contains("OK"));
    }

    @Test
    public void testDelTeam() {
        String msg = "DEL_TEAM 2";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("DelTeam class failed", result instanceof Worker.DelTeam);
        assertTrue("DelTeam process failed", result.process().contains("OK"));
    }

    @Test
    public void testUpdateTeam() {
        String msg = "UPDATE_TEAM 2 3 4 5 6 7";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("UpdateTeam class failed", result instanceof Worker.UpdateTeam);
        assertTrue("UpdateTeam process failed", result.process().contains("OK"));
    }

    @Test
    public void testAddGroup() {
        String msg = "ADD_GROUP 2 3 4 5 6";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("AddGroup class failed", result instanceof Worker.AddGroup);
        assertTrue("AddGroup process failed", result.process().contains("OK"));
    }

    @Test
    public void testDelGroup() {
        String msg = "DEL_GROUP 2";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("DelGroup class failed", result instanceof Worker.DelGroup);
        assertTrue("DelGroup process failed", result.process().contains("OK"));
    }

    @Test
    public void testUpdateGroup() {
        String msg = "UPDATE_GROUP 2 3 4 5 6 7 8";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("UpdateGroup class failed", result instanceof Worker.UpdateGroup);
        assertTrue("UpdateGroup process failed", result.process().contains("OK"));
    }

    @Test
    public void testUserInTeam() {
        String msg = "USER_IN_TEAM 2 3";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("UserInTeam class failed", result instanceof Worker.UserInTeam);
        assertTrue("UserInTeam process failed", result.process().contains("OK"));
    }

    @Test
    public void testUserOutTeam() {
        String msg = "USER_OUT_TEAM 2 3";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("UserOutTeam class failed", result instanceof Worker.UserOutTeam);
        assertTrue("UserOutTeam process failed", result.process().contains("OK"));
    }

    @Test
    public void testSetTeamConfirmed() {
        String msg = "SET_TEAM_CONFIRMED 2 3 4";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("SetTeamConfirmed class failed", result instanceof Worker.SetTeamConfirmed);
        assertTrue("SetTeamConfirmed failed", result.process().contains("OK"));
    }

    @Test
    public void testSetItemState() {
        String msg = "SET_ITEM_STATE 2 3 4";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("SetItemState class failed", result instanceof Worker.SetItemState);
        assertTrue("SetItemState process failed", result.process().contains("OK"));
    }

    @Test
    public void testGetPMTeams() {
        String msg = "GET_PM_TEAMS 2";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("GetPMTeams class failed", result instanceof Worker.GetPMTeams);
        assertTrue("GetPMTeams process failed", result.process().contains("OK"));
    }

    @Test
    public void testGetTeamUsers() {
        String msg = "GET_TEAM_USERS 2";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("GetTeamUsers class failed", result instanceof Worker.GetTeamUsers);
        assertTrue("GetTeamUsers process failed", result.process().contains("OK"));
    }

    @Test
    public void testGetUserItems() {
        String msg = "GET_USER_ITEMS 2";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("GetUserItems class failed", result instanceof Worker.GetUserItems);
        assertTrue("GetUserItems process failed", result.process().contains("OK"));
    }

    @Test
    public void testGetUserTeams() {
        String msg = "GET_USER_TEAMS 2";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("GetUserTeams class failed", result instanceof Worker.GetUserTeams);
        assertTrue("GetUserTeams process failed", result.process().contains("OK"));
    }

    @Test
    public void testGetInfo() {
        String msg = "GET_INFO 2";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("GetInfo class failed", result instanceof Worker.GetInfo);
        assertTrue("GetInfo process failed", result.process().contains("OK"));
    }

    @Test
    public void testGetUsers() {
        String msg = "GET_USERS";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("GetUsers class failed", result instanceof Worker.GetUsers);
        assertTrue("GetUsers process failed", result.process().contains("OK"));
    }

    @Test
    public void testGetTeams() {
        String msg = "GET_TEAMS";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("GetTeams class failed", result instanceof Worker.GetTeams);
        assertTrue("GetTeams process failed", result.process().contains("OK"));
    }

    @Test
    public void testGetGroups() {
        String msg = "GET_GROUPS";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("GetGroups class failed", result instanceof Worker.GetGroups);
        assertTrue("GetGroups process failed", result.process().contains("OK"));
    }

    @Test
    public void testGetItems() {
        String msg = "GET_ITEMS";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("GetItems class failed", result instanceof Worker.GetItems);
        assertTrue("GetItems process failed", result.process().contains("OK"));
    }

    @Test
    public void testLogin() {
        String msg = "LOGIN 2 3";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("Login class failed", result instanceof Worker.Login);
        assertTrue("Login process failed", result.process().contains("OK"));
    }

    @Test
    public void testChangePass() {
        String msg = "CHANGE_PASS 2 3 4";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("ChangePass class failed", result instanceof Worker.ChangePass);
        assertTrue("ChangePass process failed", result.process().contains("OK"));
    }

    @Test
    public void testAdminChangePass() {
        String msg = "ADMIN_CHANGE_PASS 2 3";
        TestClassDBC dbc = new TestClassDBC();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("AdminChangePass class failed", result instanceof Worker.AdminChangePass);
        assertTrue("AdminChangePass process failed", result.process().contains("OK"));
    }
}
