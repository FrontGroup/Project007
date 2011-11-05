/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author lucas
 */
public class Frame extends javax.swing.JFrame {

    JPanel panel;
    JMenuBar menubar;

    public Frame() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Systém SPRÁVCE");
        setSize(500, 500);
        menubar = new JMenuBar();
        setJMenuBar(menubar);
        JMenu system = new JMenu("System");
        menubar.add(system);
        JMenu functions = new JMenu("Functions");
        menubar.add(functions);
        JMenu help = new JMenu("Help");
        menubar.add(help);
        JMenuItem about = new JMenuItem("About program");
        help.add(about);
        JMenuItem test = new JMenuItem("Test connection");
        system.add(test);
        JMenuItem exit = new JMenuItem("EXIT");
        system.add(exit);

        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null, "This program was developed by FrontGroup. \n\r Version 2011.",
                        "About program", JOptionPane.INFORMATION_MESSAGE);
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
