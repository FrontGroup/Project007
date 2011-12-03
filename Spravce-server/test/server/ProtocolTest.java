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
public class ProtocolTest {

    public ProtocolTest() {
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
     * Test of process method, of class Protocol.
     */
    @Test
    public void testProcess1() {
        System.out.println("test testing connection");
        String msg = "TEST";
        Protocol instance = Protocol.getInstance();
        String expResult = "OK";
        String result = instance.process(msg);
        assertEquals(expResult, result);
    }

    @Test
    public void testProcess2() {
        System.out.println("test wrong requirement");
        String msg = "LOGIN 666";
        Protocol instance = Protocol.getInstance();
        String expResult = "KO Wrong requirement!";
        String result = instance.process(msg);
        assertEquals(expResult, result);
    }

    @Test
    public void testProcess3() {
        System.out.println("test login ADMIN");
        String msg = "LOGIN 666 admin";
        Protocol instance = Protocol.getInstance();
        String expResult = "ADMIN";
        String result = instance.process(msg);
        assertEquals(expResult, result);
    }
}
