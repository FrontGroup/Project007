package client;

import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SourceTeamTest {

    /**
     *
     * @author petr
     */
    public SourceTeamTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    class MultiSCMockup implements ServerConnectionInterface {

        List<String> messages = new LinkedList<String>();
        String[] responses;
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

    @Test
    public void testLoadDataAndGetTeam() {
        System.out.println("LoadDataAndGetTeam");

        MultiSCMockup sc = new MultiSCMockup(new String[]{"2 13 Mrakodrap Barak_v_PZE   1"/*,
                "3 12 MzkyOK Mzdovy_program  Vytvorit_mzdovy_program 1"*/

                }); //GET_TEAMS

        SourceTeam instance = new SourceTeam(sc);
        String sret = instance.loadData();
        assertEquals("OK", sret);
        assertEquals("GET_TEAMS", sc.messages.get(0));

        Team tres = instance.getTeam(2);
        assertEquals(2, tres.getId());
        assertEquals("Mrakodrap", tres.getName());
        assertEquals("Barak_v_PZE", tres.getGoal());
        assertEquals("", tres.getInfo());
        assertEquals("", tres.getProject());
        assertEquals(13, tres.getPm());
        assertTrue(tres.isActive());

    }

    @Test
    public void testLoadDataFromPM() {
        System.out.println("LoadDataFromPM");

        MultiSCMockup sc = new MultiSCMockup(new String[]{"2 13 Mrakodrap Barak_v_PZE   1;",
                    "6 13 Zkouska Zkouska_zkousky   0"}); //GET_PM_TEAMS

        SourceTeam instance = new SourceTeam(sc);
        String sret = instance.loadDataFromPM(13);
        assertEquals("OK", sret);
        assertEquals("GET_PM_TEAMS 13", sc.messages.get(0));
    }

    @Test
    public void testLoadUserStatusInTeam() {
        System.out.println("LoadUserStatusInTeam");

        MultiSCMockup sc = new MultiSCMockup(new String[]{"6 0;", "8 0;", "10 1;"}); //GET_TEAM_USERS

        SourceTeam instance = new SourceTeam(sc);
        String sret = instance.loadUserStatusInTeam(2);
        assertEquals("OK", sret);
        assertEquals("GET_TEAM_USERS 2", sc.messages.get(0));
    }

    @Test
    public void testAddTeam() {
        System.out.println("AddTeam");
        SCMockup sc = new SCMockup();
        SourceTeam instance = new SourceTeam(sc);
        Team tres = new Team();
        tres.setName("Name");
        tres.setGoal("Goal");
        tres.setPm(2);
        String result = instance.addTeam(tres);
        assertEquals("OK", result);
        assertEquals("ADD_TEAM 2 Name null null Goal", sc.message);
    }

    @Test
    public void testDelTeam() {
        System.out.println("DelTeam");
        SCMockup sc = new SCMockup();
        SourceTeam instance = new SourceTeam(sc);
        String result = instance.delTeam(2);
        assertEquals("OK", result);
        assertEquals("DEL_TEAM 2", sc.message);
    }

    @Test
    public void testUpdateTeam() {
        System.out.println("UpdateTeam");
        SCMockup sc = new SCMockup();
        SourceTeam instance = new SourceTeam(sc);
        Team tres = new Team(10);
        tres.setName("Name");
        tres.setGoal("Goal");
        tres.setPm(2);
        tres.isActive(false);
        String result = instance.updateTeam(tres.getId(), tres);
        assertEquals("OK", result);
        assertEquals("UPDATE_TEAM 10 2 Name null null Goal 0", sc.message);
    }
}
