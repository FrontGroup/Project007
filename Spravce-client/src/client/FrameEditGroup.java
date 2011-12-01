/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class FrameEditGroup extends javax.swing.JFrame {

    private static HashMap<String, Item> map = new HashMap<String, Item>();
    private String id = null;
    JButton save;
    JLabel lName = new JLabel("Name:");
    JTextField name;

    public FrameEditGroup(String id) {
        this.id = id;
        initComponents();
    }

    public static void main(String[] args) {
        map.put("Item 1", new Item("Item 1", "false"));
        map.put("Item 2", new Item("Item 2", "false"));
        map.put("Item 3", new Item("Item 3", "true"));
        map.put("Item 4", new Item("Item 4", "false"));
        map.put("Item 5", new Item("Item 5", "true"));
        map.put("Item 6", new Item("Item 6", "false"));
        map.put("Item 7", new Item("Item 7", "true"));
        map.put("Item 8", new Item("Item 8", "false"));
        map.put("Item 9", new Item("Item 9", "false"));
        map.put("Item 10", new Item("Item 10", "false"));
        map.put("Item 11", new Item("Item 5", "true"));
        map.put("Item 12", new Item("Item 6", "false"));
        map.put("Item 13", new Item("Item 7", "true"));
        map.put("Item 14", new Item("Item 8", "false"));
        map.put("Item 15", new Item("Item 9", "false"));
        map.put("Item 16", new Item("Item 10", "false"));
        FrameEditGroup fg = new FrameEditGroup(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        if (id == null) {
            setTitle("NEW group");
        } else {
            setTitle("EDIT group");
        }
        setSize(500, 300);
        save = new JButton("Save");
        name = new JTextField(10);
        JPanel p1 = new JPanel();
        p1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        JPanel p2 = new JPanel();
        p2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        JPanel p3 = new JPanel();
        p3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        JPanel p4 = new JPanel();
        p4.setLayout(new java.awt.GridLayout(7, 1));
        p1.add(lName);
        p2.add(name);
        p3.add(save);
        p4.add(new JPanel());
        p4.add(new JPanel());
        p4.add(p1);
        p4.add(p2);
        p4.add(new JPanel());
        p4.add(new JPanel());
        p4.add(p3);
        getContentPane().setLayout(new java.awt.GridLayout(1, 2));
        getContentPane().add(getScrollpane());
        getContentPane().add(p4);
        setVisible(true);
        //pack();
    }

    public JScrollPane getScrollpane() {
        JPanel panel = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints constraint = new GridBagConstraints();
        panel.setLayout(gridbag);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 1;
        int i = 0;
        for (Item item : map.values()) {
            constraint.gridx = 0;
            constraint.gridy = i;
            JCheckBox jCB = new JCheckBox(item.getName(), item.isState());
            gridbag.setConstraints(jCB, constraint);
            panel.add(jCB);
            i++;
        }
        JScrollPane jsp = new JScrollPane(panel);
        return jsp;
    }
}
