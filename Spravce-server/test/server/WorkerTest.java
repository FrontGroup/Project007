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

}
