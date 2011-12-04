/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author lucas
 */
public class FrameGroups extends javax.swing.JFrame {

    private JButton groups;
    private JButton edit;
    private JButton delete;
    private JComboBox box;
    private SourceGroup sg = new SourceGroup();
    private HashMap<Integer, Group> allGroups;

    public FrameGroups() {
        initComponents();
        lookData();
    }

    public static void main(String[] args) {
        FrameGroups fg = new FrameGroups();

    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Groups");
        setSize(300, 300);
        groups = new JButton("NEW GROUP");
        edit = new JButton("Edit");
        delete = new JButton("Delete");
        box = new JComboBox();
        JPanel p1 = new JPanel();
        p1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        JPanel p2 = new JPanel();
        p2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        getContentPane().setLayout(new java.awt.GridLayout(4, 1));
        p1.add(groups);
        p2.add(box);
        p2.add(edit);
        p2.add(delete);
        getContentPane().add(new JPanel());
        getContentPane().add(p1);
        getContentPane().add(p2);
        getContentPane().add(new JPanel());
        setVisible(true);

        edit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                java.awt.EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        new FrameEditGroup((Group) box.getSelectedItem()).setVisible(true);
                    }
                });
            }
        });
        groups.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                java.awt.EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        new FrameEditGroup(null).setVisible(true);
                    }
                });
            }
        });
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                deleteGroup();
            }
        });
    }

    private void lookData() {
        String response = sg.loadData();
        if (response.startsWith("KO")) {
            JOptionPane.showMessageDialog(null, response.substring(3),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        allGroups = sg.getAllGroups();
        box.removeAllItems();
        Collection<Group> values = allGroups.values();
        for (Group g : values) {
            box.addItem(g);
        }
    }

    private void deleteGroup() {
        Group temp = (Group) box.getSelectedItem();
        String response = sg.delGroup(temp.getId());
        if (response.startsWith("KO")) {
            JOptionPane.showMessageDialog(null, response.substring(3),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}
