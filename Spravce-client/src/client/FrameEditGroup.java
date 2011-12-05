/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class FrameEditGroup extends javax.swing.JFrame {

    private static HashMap<Integer, Item> items = new HashMap<Integer, Item>();
    private JButton save;
    private JLabel lName = new JLabel("Name:");
    private JLabel lSave = new JLabel("");
    private JTextField name;
    private Group group = null;
    private SourceGroup sg = new SourceGroup();
    private SourceItem si = new SourceItem();

    public FrameEditGroup(Group group, SourceGroup sg) {
        this.group = group;
        this.sg = sg;
        downloadItems();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        if (group == null) {
            setTitle("NEW group");
        } else {
            setTitle("EDIT group");
        }
        setSize(500, 300);
        save = new JButton("Save");
        name = new JTextField(10);
        if (group != null) {
            name.setText(group.getName());
        }
        lSave.setForeground(Color.GREEN);
        JPanel p1 = new JPanel();
        p1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        JPanel p2 = new JPanel();
        p2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        JPanel p3 = new JPanel();
        p3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        JPanel p4 = new JPanel();
        p4.setLayout(new java.awt.GridLayout(7, 1));
        JPanel p5 = new JPanel();
        p5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        p1.add(lName);
        p2.add(name);
        p3.add(save);
        p5.add(lSave);
        p4.add(new JPanel());
        p4.add(new JPanel());
        p4.add(p1);
        p4.add(p2);
        p4.add(p5);
        p4.add(p3);
        p4.add(new JPanel());
        getContentPane().setLayout(new java.awt.GridLayout(1, 2));
        getContentPane().add(getScrollpane());
        getContentPane().add(p4);
        setVisible(true);

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                saveGroup();
            }
        });
    }

    public JScrollPane getScrollpane() {
        JPanel panel = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints constraint = new GridBagConstraints();
        panel.setLayout(gridbag);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridwidth = 1;
        int i = 0;
        for (Item item : items.values()) {
            constraint.gridx = 0;
            constraint.gridy = i;
            JCheckBox jCB = new JCheckBox(item.getName(), item.isState());
            jCB.setName("" + item.getId());
            jCB.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    JCheckBox source = (JCheckBox) arg0.getSource();
                    Item temp = items.get(Integer.valueOf(source.getName()));
                    temp.setState(source.isSelected());
                    items.put(temp.getId(), temp);
                }
            });
            gridbag.setConstraints(jCB, constraint);
            panel.add(jCB);
            i++;
        }
        JScrollPane jsp = new JScrollPane(panel);
        return jsp;
    }

    private void downloadItems() {
        String response = si.loadData();
        if (response.startsWith("KO")) {
            JOptionPane.showMessageDialog(null, response.substring(3),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            this.dispose();
            return;
        }
        items = si.getAllItems();
        if (group != null) {
            int[] idItems = group.getIdItems();
            for (int i : idItems) {
                Item temp = items.get(i);
                temp.setState(true);
                items.put(i, temp);
            }
        }
    }

    private void saveGroup() {
        String s = "";
        Collection<Item> values = items.values();
        for (Item i : values) {
            if (i.isState()) {
                s += i.getId() + " ";
            }
        }
        String[] split = s.split(" ");
        int[] idItems = new int[split.length];
        for (int k = 0; k < idItems.length; k++) {
            idItems[k] = Integer.valueOf(split[k]);
        }
        String response;
        if (group == null) {
            group = new Group(name.getText(), idItems);
            response = sg.addGroup(group);
        } else {
            group.setName(name.getText());
            group.setIdItems(idItems);
            response = sg.updateGroup(group.getId(), group);
        }
        if (response.startsWith("KO")) {
            lSave.setText("");
            JOptionPane.showMessageDialog(null, response.substring(3),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            lSave.setText("Saved!");
        }
    }
}
