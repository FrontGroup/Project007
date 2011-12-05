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

    /**
     * Test of getWorker method, of class Worker.
     */
    @Test
    public void testGetWorker() {
        System.out.println("getWorker ADD_USER");
        String msg = "ADD_USER";
        DBConnection dbc = null;
        Worker expResult = new Worker.addUser();
        Worker result = Worker.getWorker(msg, dbc);
        assertTrue("NO",result instanceof Worker.addUser);
    }
}
