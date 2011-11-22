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
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateUserDialog().setVisible(true);
            }
        });
        JMenuItem del = new JMenuItem("Delete user");
        return Arrays.asList(add, del, new JMenuItem("Edit user"));
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
            group = new JComboBox(new Object[]{"Zednik"}); // TODO fill from database, enable based on role
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
                    add(new JLabel("Password")); // TODO refactor, dupicate code in ChangePassDialog and LoginFrame
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
