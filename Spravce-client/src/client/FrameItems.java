/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class FrameItems extends javax.swing.JFrame {

    HashMap<String, String> map = new HashMap<String, String>();
    JButton save;
    JButton delete;
    JComboBox box;
    JTextField name;
    JLabel lBox = new JLabel("Item:");
    JLabel lName = new JLabel("Name:");

    public FrameItems() {
        initComponents();
    }
    
    public static void main(String[] args) {
        FrameItems fi = new FrameItems();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Global items");
        setSize(300, 300);
        save = new JButton("Save");
        delete = new JButton("Delete");
        box = new JComboBox();
        box.addItem("                          ");
        name = new JTextField(10);
        JPanel p1 = new JPanel();
        p1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        JPanel p2 = new JPanel();
        p2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        JPanel p3 = new JPanel();
        p3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        getContentPane().setLayout(new java.awt.GridLayout(5, 1));
        p1.add(lBox);
        p1.add(box);
        p2.add(lName);
        p2.add(name);
        p3.add(save);
        p3.add(delete);
        getContentPane().add(new JPanel());
        getContentPane().add(p1);
        getContentPane().add(p2);
        getContentPane().add(p3);
        getContentPane().add(new JPanel());
        setVisible(true);
    }
}
