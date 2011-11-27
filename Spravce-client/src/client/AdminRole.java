package client;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

class AdminRole implements Role {

    AdminRole(ServerConnection s) {
    }

    AdminRole() {
    }

    @Override
    public Iterable<JMenuItem> getMenuItems() {
        // TODO bind specific action handlers to these menu items
        JMenuItem add = new JMenuItem("Add user");

        JMenuItem del = new JMenuItem("Delete user");
        JMenuItem manageItems = new JMenuItem("Manage items");
        JMenuItem manageGroups = new JMenuItem("Manage groups");

        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateUserDialog().setVisible(true);
            }
        });

        manageItems.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new FrameItems();
            }
        });
        manageGroups.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new FrameGroups();
            }
        });
        return Arrays.asList(add, del, new JMenuItem("Edit user"), manageItems, manageGroups);
    }

    static class DeleteUserDialog extends JDialog {

        DeleteUserDialog() {
            setTitle("Delete user");
            setModalityType(ModalityType.APPLICATION_MODAL);
            //TODO show user table - id, name, contact
            //setLayout(new GridLayout);
            pack();
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
    }

    // Combobox (id, value) pair
    public static class Pair {

        final int id;
        final String name;

        Pair(int i, String n) {
            id = i;
            name = n;
        }

        Pair(String string) {
            String[] s = string.split(" ", 2);
            id = Integer.valueOf(s[0]);
            name = s[1];
        }

        @Override
        public String toString() { // Default combobox renderer consults toString method of unknown objects
            return name;
        }
    }

    // This expects one line string description of roles in system as per Protocol GET_GROUPS description
    static Pair[] processGetGroupsResponse(String response) { // Initialize from server response
        String[] tokens = response.split(";");
        Pair[] pairs = new Pair[tokens.length];
        int pos = 0;
        for (String i : tokens) {
            pairs[pos++] = new Pair(i);
        }
        return pairs;
    }
    //TODO use for edit as well

    static class CreateUserDialog extends JDialog {

        JLabel warn;
        JComboBox role, group;
        JPasswordField pass, pass2;
        JButton create, cancel;

        public CreateUserDialog() {
            setTitle("Create user");
            setModalityType(ModalityType.APPLICATION_MODAL);
            warn = new JLabel();
            warn.setForeground(Color.red);

            role = new JComboBox(new Object[]{"Employee", "Project Manager"});
            group = new JComboBox(new Object[]{new Pair(1, "Zednik"), new Pair(2, "Pridavac")}); // TODO fill from database, enable based on role
            pass = new JPasswordField(10);
            pass2 = new JPasswordField(10);
            create = new JButton("Create user");
            cancel = new JButton("Cancel");

            setLayout(new GridLayout(7, 1));
            add(new JLabel("User ID will be automatically generated."));
            add(warn);
            add(new JPanel(new FlowLayout()) {

                {
                    add(new JLabel("User role"));
                    add(role);
                }
            });
            add(new JPanel(new FlowLayout()) {

                {
                    add(new JLabel("Password")); // TODO refactor, duplicate code in ChangePassDialog and LoginFrame
                    add(pass);
                }
            });
            add(new JPanel(new FlowLayout()) {

                {
                    add(new JLabel("Password again"));
                    add(pass2);
                }
            });
            add(new JPanel(new FlowLayout()) {

                {
                    add(new JLabel("group"));
                    add(group);
                }
            });
            add(new JPanel(new FlowLayout()) {

                {
                    add(create);
                    add(cancel);
                }
            });
            cancel.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    dispose();
                }
            });
            create.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("ADD " + ((Pair) group.getSelectedItem()).id);
                    // TODO ADD
                }
            });
            // TODO create handler possibly outside view
            // TODO create response - either warn or ok box
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            pack();
        }
    }

    public static void main(String[] args) {
        new CreateUserDialog().setVisible(true);
    }
}
