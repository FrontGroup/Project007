/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.HashMap;
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
public class SourceItemTest {

    public SourceItemTest() {
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
     * Test of loadData and getItem method, of class SourceItem.
     */
    @Test
    public void testLoadDataAndGetItem() {
        System.out.println("loadDataAndGetItem");
        SCMockup sc = new SCMockup("1 name;");
        SourceItem instance = new SourceItem(sc);
        String result = instance.loadData();
        assertEquals("OK", result);
        assertEquals("GET_ITEMS", sc.message);

        Item i = instance.getItem(1);
        assertEquals("name", i.getName());

        i = instance.getAllItems().get(1);
        assertEquals("name", i.getName());
    }

    /**
     * Test of addItem method, of class SourceItem.
     */
    @Test
    public void testAddItem() {
        System.out.println("addItem");
        SCMockup sc = new SCMockup();
        Item item = new Item("name");
        SourceItem instance = new SourceItem(sc);
        instance.addItem(item);
        String result = instance.addItem(item);
        assertEquals("OK", result);
        assertEquals("ADD_ITEM name", sc.message);
    }

    /**
     * Test of delItem method, of class SourceItem.
     */
    @Test
    public void testDelItem() {
        System.out.println("delItem");
        int id = 1;
        SCMockup sc = new SCMockup();
        SourceItem instance = new SourceItem(sc);
        String result = instance.delItem(id);
        assertEquals("OK", result);
        assertEquals("DEL_ITEM 1", sc.message);
    }

    /**
     * Test of updateItem method, of class SourceItem.
     */
    @Test
    public void testUpdateItem() {
        System.out.println("updateItem");
        int id = 1;
        Item item = new Item("newName");
        SCMockup sc = new SCMockup();
        SourceItem instance = new SourceItem(sc);
        String result = instance.updateItem(id, item);
        assertEquals("OK", result);
        assertEquals("UPDATE_ITEM 1 newName", sc.message);
    }
}
