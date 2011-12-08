package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JMenuItem;

import client.AdminRole.CreateUserDialog;

public class PMRole extends ViewProfile {
	
	int id;

    public PMRole(int userId) {
        super(userId);
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
                new FramePMEditTeam(id).setVisible(true);

            }
        });
        
        List<JMenuItem> m = new LinkedList<JMenuItem>();
        for(JMenuItem i: super.getMenuItems()) {
            m.add(i);
        }
        m.add(show);
        m.add(create);
        return m;
    }
}
