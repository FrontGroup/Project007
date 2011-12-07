/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author lucas
 */
public interface DBCInt {

    public String login(String string, String string0);

    public String getInfo(String string);

    public String getUsers();

    public String addUser(int valueOf, String string0, String string1, String string2, String string3, String string4, String string5, String string6, int valueOf0);

    public String deleteUser(String string);

    public String changePass(String string, String string0, String string1);

    public String adminChangePass(String string, String string0);

    public String getItems();

    public String getTeams();

    public String getUserItems(String string);

    public String getUserTeams(String string);

    public String getGroups();

    public String addItem(String string);

    public String delItem(String string);

    public String updateItem(String string, String string0);

    public String updateUser(String string, String string0, String string1, String string2, String string3, String string4, String string5, String string6);

    public String addTeam(String string, String string0, String string1, String string2, String string3);

    public String updateTeam(String string, String string0, String string1, String string2, String string3, String string4);

    public String delTeam(String string);

    public String userInTeam(String string, String string0);

    public String userOutTeam(String string, String string0);

    public String setTeamConfirmed(String string, String string0, boolean confirmed);

    public String setItemState(String string, String string0, boolean state);

    public String addGroup(String string, int[] items);

    public String updateGroup(String string, String string0, int[] items);

    public String delGroup(String string);

    public String getPMTeams(String string);

    public String getTeamUsers(String string);
}
