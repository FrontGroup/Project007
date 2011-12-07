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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class FrameItems extends javax.swing.JFrame {

    private JButton save;
    private JButton delete;
    private JComboBox box;
    private JTextField name;
    private JLabel lBox = new JLabel("Item:");
    private JLabel lName = new JLabel("Name:");
    private SourceItem si = new SourceItem();
    private HashMap<Integer, Item> allItems;
    private ActionListener al;

    public FrameItems() {
        initComponents();
        lookData();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Global items");
        setSize(300, 300);
        save = new JButton("Save");
        delete = new JButton("Delete");
        delete.setEnabled(false);
        box = new JComboBox();
        box.addItem("     ..insert new..       ");
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

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                saveItem();
            }
        });

        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                deleteItem();
            }
        });
        al = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (box.getSelectedIndex() != 0) {
                    name.setText(((Item) box.getSelectedItem()).getName());
                    delete.setEnabled(true);
                } else {
                    name.setText("");
                    delete.setEnabled(false);
                }
            }
        };
        setVisible(true);
    }

    private void lookData() {
        String response = si.loadData();
        if (response.startsWith("KO")) {
            JOptionPane.showMessageDialog(null, response.substring(3),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        allItems = si.getAllItems();
        System.out.println("all:" + allItems.size());
        box.removeActionListener(al);
        box.removeAllItems();
        box.addItem("     ..insert new..       ");
        Collection<Item> values = allItems.values();
        for (Item i : values) {
            box.addItem(i);
        }
        box.addActionListener(al);
    }

    private void saveItem() {
        if (box.getSelectedIndex() == 0) {
            Item temp = new Item(name.getText());
            String response = si.addItem(temp);
            if (response.startsWith("KO")) {
                JOptionPane.showMessageDialog(null, response.substring(3),
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                JOptionPane.showMessageDialog(null, "SAVED!",
                        this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            Item temp = (Item) box.getSelectedItem();
            temp.setName(name.getText());
            String response = si.updateItem(temp.getId(), temp);
            if (response.startsWith("KO")) {
                JOptionPane.showMessageDialog(null, response.substring(3),
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                JOptionPane.showMessageDialog(null, "SAVED!",
                        this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
            }
        }
        lookData();
    }

    private void deleteItem() {
        Item temp = (Item) box.getSelectedItem();
        String response = si.delItem(temp.getId());
        if (response.startsWith("KO")) {
            JOptionPane.showMessageDialog(null, response.substring(3),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(null, "DELETED!",
                    this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
        }
        name.setText("");
        lookData();
    }
}
