/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author lucas
 */
public class TestClassDBC implements DBCInt {

    @Override
    public String login(String string, String string0) {
        return "OK";
    }

    @Override
    public String getInfo(String string) {
        return "OK";
    }

    @Override
    public String getUsers() {
        return "OK";
    }

    @Override
    public String deleteUser(String string) {
        return "OK";
    }

    @Override
    public String changePass(String string, String string0, String string1) {
        return "OK";
    }

    @Override
    public String adminChangePass(String string, String string0) {
        return "OK";
    }

    @Override
    public String getItems() {
        return "OK";
    }

    @Override
    public String getTeams() {
        return "OK";
    }

    @Override
    public String getUserItems(String string) {
        return "OK";
    }

    @Override
    public String getUserTeams(String string) {
        return "OK";
    }

    @Override
    public String getGroups() {
        return "OK";
    }

    @Override
    public String addItem(String string) {
        return "OK";
    }

    @Override
    public String delItem(String string) {
        return "OK";
    }

    @Override
    public String updateItem(String string, String string0) {
        return "OK";
    }

    @Override
    public String updateUser(String string, String string0, String string1, String string2, String string3, String string4, String string5, String string6) {
        return "OK";
    }

    @Override
    public String addTeam(String string, String string0, String string1, String string2, String string3) {
        return "OK";
    }

    @Override
    public String updateTeam(String string, String string0, String string1, String string2, String string3, String string4, String string5) {
        return "OK";
    }

    @Override
    public String delTeam(String string) {
        return "OK";
    }

    @Override
    public String userInTeam(String string, String string0) {
        return "OK";
    }

    @Override
    public String userOutTeam(String string, String string0) {
        return "OK";
    }

    @Override
    public String setTeamConfirmed(String string, String string0, boolean confirmed) {
        return "OK";
    }

    @Override
    public String setItemState(String string, String string0, boolean state) {
        return "OK";
    }

    @Override
    public String addGroup(String string, int[] items) {
        return "OK";
    }

    @Override
    public String updateGroup(String string, String string0, int[] items) {
        return "OK";
    }

    @Override
    public String delGroup(String string) {
        return "OK";
    }

    @Override
    public String getPMTeams(String string) {
        return "OK";
    }

    @Override
    public String getTeamUsers(String string) {
        return "OK";
    }

    @Override
    public String addUser(int valueOf, String string0, int valueOf0) {
        return "OK";
    }
}
