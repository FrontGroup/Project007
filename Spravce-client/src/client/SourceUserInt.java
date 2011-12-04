/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author lucas
 */
public interface SourceUserInt {

    //metoda, ktera naplni tridu vsemi uzivateli
    //vraci OK nebo KO chyba
    public String loadData();

    //metoda, ktera naplni tridu uzivatelem s idUser
    //vraci OK nebo KO chyba
    public String loadData(int idUser);

    public User getUser(int id);

    public String addUser(User user);

    public String delUser(int idUser);

    public String updateUser(int idUser, User user);

    public String setTeam(int idUser, int idTeam);

    public String delTeam(int idUser, int idTeam);

    public String setTeamConfirmed(int idUser, int idTeam, boolean confirmed);

    public String setItemState(int idUser, int idItem, boolean state);
}
