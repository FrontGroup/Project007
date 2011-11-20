/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class LoginFrame extends javax.swing.JFrame {

    JPanel panel;
    JMenuBar menubar;
    JTextField tuser;
    JPasswordField tpass;
    JLabel warn;

    public LoginFrame() {
        initComponents();
    }

    // TODO min-size, max-size
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Systém SPRÁVCE");
        setSize(500, 500);
        menubar = new JMenuBar();
        setJMenuBar(menubar);
        JMenu system = new JMenu("System");
        menubar.add(system);
        JMenu help = new JMenu("Help");
        menubar.add(help);
        help.add(Main.getAboutMenuItem());
        JMenuItem test = new JMenuItem("Test connection");
        system.add(test);
        JMenuItem exit = new JMenuItem("EXIT");
        system.add(exit);
        panel = new JPanel();
        setLayout(new FlowLayout(FlowLayout.CENTER));
        getContentPane().add(panel);
        // login panel
        JPanel r0 = new JPanel(), r1 = new JPanel(), r2 = new JPanel(), r3 = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        JLabel luser = new JLabel("User ID");
        JLabel lpass = new JLabel("Password");
        tuser = new JTextField(20);
        tpass = new JPasswordField(20);
        JButton ok = new JButton("Login");
        JButton cancel = new JButton("cancel");
        warn = new JLabel("");
        warn.setForeground(Color.red);
        r1.setLayout(new FlowLayout());
        r2.setLayout(new FlowLayout());
        r0.setLayout(new FlowLayout());
        r0.add(warn);
        r1.add(luser);
        r1.add(tuser);
        r2.add(lpass);
        r2.add(tpass);
        r3.add(ok);
        r3.add(cancel);
        panel.add(r0);
        panel.add(r1);
        panel.add(r2);
        panel.add(r3);
        pack();
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                warn.setText("");
                ServerConnection s = ServerConnection.getInstance();
                if (!tuser.getText().matches("^\\d+$")) {
                    warn.setText("User ID must be a number.");
                    return;
                }
                if (tpass.getPassword().length < 1) {
                    warn.setText("You must specify a password.");
                    return;
                }
                String ret = s.connect(tuser.getText(), new String(tpass.getPassword()));
                if (ret.startsWith("KO")) {
                    warn.setText(ret);
                } else { // show new frame
                    setVisible(false);
                    dispose();
                    new MainFrame().setVisible(true);
                }
            }
        });

        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tuser.setText("");
                tpass.setText("");
                warn.setText("");
            }
        });
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        test.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                testConection();
            }
        });
    }

    private void testConection() {
        ServerConnection sc = ServerConnection.getInstance();
        if (sc.connect(null, null).contains("OK")) {
            JOptionPane.showMessageDialog(null, "Server is READY",
                    "Testing connection", JOptionPane.INFORMATION_MESSAGE);
            sc.close();
        } else {
            JOptionPane.showMessageDialog(null, "Server is UNAVAILABLE!",
                    "Testing connection", JOptionPane.ERROR_MESSAGE);
        }

    }
}
