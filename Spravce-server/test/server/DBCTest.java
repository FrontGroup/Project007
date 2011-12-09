/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.*;
import mysqltest.DBConnection;
import org.junit.*;

/**
 *
 * @author beretis
 */
public class DBCTest {

    String db_address = "jdbc:mysql://beretis.sh.cvut.cz/skuska?"
            + "user=frontgroup&password=hesielko";
    int[] items = {
        1, 2, 3, 4
    };
    DBConnection db = new DBConnection(db_address);

    @org.junit.Test
    public void testIt() throws Exception {
        String result, id;
        Connection connection = db.getConnect();
        connection.setAutoCommit(false);
        int[] items = {
            1, 2, 3, 4
        };
        org.junit.Assert.assertEquals("OK", db.connect());
        org.junit.Assert.assertEquals("ADMIN", db.login("3", "user3"));
        org.junit.Assert.assertEquals("EMPLOYEE", db.login("2", "user2"));
        org.junit.Assert.assertEquals("MANAGER", db.login("5", "user5"));
        org.junit.Assert.assertEquals("HR", db.login("6", "user6"));
        String temp = "2 Ucetni 2 12 13;3 Programator 6 10 12 13 14 15 16;"
                + "4 Zednik 5 1 2 3 8 9;5 Ridic 4 8 9 12 13;"
                + "10 Elektrikar 2 12 13;9 Truhlar 2 12 13;"
                + "24 Architekt 2 12 13;25 Logistik 5 5 8 9 12 13;";
        org.junit.Assert.assertEquals(temp, db.getGroups());
        temp = "1 Vrtani;2 Rezani;5 Office;8 RP-A;9 RP-B;"
                + "10 JAVA;12 English;13 German;14 C++;15 Python;16 MySQL;";
        org.junit.Assert.assertEquals(temp, db.getItems());
        temp = "2 13 Mrakodrap Nejvyssi_barak_v_PZE null Postavit_nejvyssi_mrakodrap_v_Praze 1;"
                + "3 12 MzdyOK Mzdovy_program null Vytvorit_mzdovy_program_a_rozsirit_ho_na_trh 1;"
                + "4 13 Panelák Opravit_panelák null Opravit_panelák 0;";
        org.junit.Assert.assertEquals(temp, db.getTeams());
        temp = "2;3;4;5;6;";
        org.junit.Assert.assertEquals(temp, db.getUsers());
        temp = "4;2;Anna;Amesna;null;null;null;null;Mzdovy_ucetni";
        org.junit.Assert.assertEquals(temp, db.getInfo("2"));
        temp = "4 1;6 1;";
        org.junit.Assert.assertEquals(temp, db.getUserItems("3"));
        temp = "3 12 MzdyOK Mzdovy_program null Vytvorit_mzdovy_program_a_rozsirit_ho_na_trh 1;";
        org.junit.Assert.assertEquals(temp, db.getPMTeams("12"));
        org.junit.Assert.assertEquals("5 0;", db.getTeamUsers("3"));
        org.junit.Assert.assertEquals("99999", db.addUser("99999", 2, "0900", 2));
        org.junit.Assert.assertEquals("OK", db.addUser("99999", 2, "0900", 2));
        org.junit.Assert.assertEquals("OK", db.updateUser("99999", "Big", "Lebowski", "Pod mostom", "Praha", "kajinek@kajo.sk", "9119232342", "Astronaut"));
        org.junit.Assert.assertEquals("OK", db.addTeam("99999", "test", "test", "test", "test"));
        org.junit.Assert.assertEquals("OK", db.updateTeam(db.getIdOf("Teams", "test"), "99999", "Tunelostroje", "Tunel", "vykopat tunelisko", "pasovanie kokainu"));
        org.junit.Assert.assertEquals("OK", db.addItem("testovaci"));
        org.junit.Assert.assertEquals("OK", db.updateItem(db.getIdOf("Items", "testovaci"), "Muceni"));
        org.junit.Assert.assertEquals("OK", db.addGroup("test", items));
        org.junit.Assert.assertEquals("OK", db.itemToUser("99999", db.getIdOf("Items", "Muceni")));
        org.junit.Assert.assertEquals("OK", db.setItemState("99999", db.getIdOf("Items", "Muceni"), true));
        org.junit.Assert.assertEquals("OK", db.updateGroup(db.getIdOf("Groups", "test"), "Upratovaci", items));
        org.junit.Assert.assertEquals("OK", db.userInTeam("99999", db.getIdOf("Teams", "Tunelostroje")));
        org.junit.Assert.assertEquals("OK", db.setTeamConfirmed("99999", db.getIdOf("Teams", "Tunelostroje"), true));
        org.junit.Assert.assertEquals("OK", db.userOutTeam("99999", db.getIdOf("Teams", "Tunelostroje")));
        org.junit.Assert.assertEquals("OK", db.delItem(db.getIdOf("Items", "Muceni")));
        org.junit.Assert.assertEquals("OK", db.delTeam(db.getIdOf("Teams", "Tunelostroje")));
        org.junit.Assert.assertEquals("OK", db.delGroup(db.getIdOf("Groups", "Upratovaci")));
        org.junit.Assert.assertEquals("OK", db.deleteUser("99999"));
    }
}
