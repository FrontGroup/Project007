package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JMenuItem;

import client.AdminRole.CreateUserDialog;

public class PMRole implements Role {
	
	int id;

    public PMRole(int userId) {
        super();
        id = userId;
    }

    @Override
    public Iterable<JMenuItem> getMenuItems() {
        JMenuItem show = new JMenuItem("Show teams");
        show.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new FramePMShowTeams(id).setVisible(true);
            }
        });
        JMenuItem create = new JMenuItem("Create new team");
        create.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new FramePMEditTeam(id);

            }
        });
        return Arrays.asList(show, create);
    }
}
