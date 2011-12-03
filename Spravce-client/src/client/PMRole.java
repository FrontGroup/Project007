package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JMenuItem;

import client.AdminRole.CreateUserDialog;

public class PMRole implements Role {

    private SourceUser user;

    public PMRole(SourceUser user) {
        super();
        this.user = user;
    }

    public PMRole(int userId) {
        //TODO implement this - called from LoginFrame
    }

    @Override
    public Iterable<JMenuItem> getMenuItems() {
        JMenuItem show = new JMenuItem("Show teams");
        show.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new FramePMShowTeams(user).setVisible(true);
            }
        });
        JMenuItem create = new JMenuItem("Create new team");
        create.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new FramePMEditTeam(user);

            }
        });
        return Arrays.asList(show, create);
    }
}
