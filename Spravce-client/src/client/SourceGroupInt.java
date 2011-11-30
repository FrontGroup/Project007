/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author lucas
 */
public interface SourceGroupInt {

    //metoda, ktera naplni tridu vsemi dostupnymi skupinami
    //vraci OK nebo KO chyba
    public String loadData();

    public Group getGroup(int id);

    public String addGroup(Group group);

    public String delGroup(int id);

    public String updateGroup(int id, Group group);
}
