/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author lucas
 */
public class FrameGroups extends javax.swing.JFrame {

    HashMap<String, String> map = new HashMap<String, String>();
    JButton groups;
    JButton edit;
    JButton delete;
    JComboBox box;

    public FrameGroups() {
        initComponents();
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
        box.addItem("                          ");
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
    }
}
