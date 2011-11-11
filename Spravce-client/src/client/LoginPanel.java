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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author radim
 */
public class LoginPanel extends JPanel {

    JTextField tuser;
    JPasswordField tpass;
    JLabel warn;

    public LoginPanel() {
        JPanel r0=new JPanel(), r1 = new JPanel(), r2 = new JPanel(), r3 = new JPanel();
        setLayout(new GridLayout(4, 1));
        JLabel luser = new JLabel("User ID");
        JLabel lpass = new JLabel("Password");
        tuser = new JTextField(20);
        tpass = new JPasswordField(20);
        JButton ok = new JButton("Login");
        JButton cancel=new JButton("cancel");
        warn=new JLabel("");
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
        add(r0);
        add(r1);
        add(r2);
        add(r3);
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                warn.setText("");
                ServerConnection s=ServerConnection.getInstance();
                if(!tuser.getText().matches("^\\d+$")) {
                    warn.setText("User ID must be a number.");
                    return;
                }
                if(tpass.getPassword().length<1) {
                    warn.setText("You must specify a password.");
                    return;
                }
                String ret=s.connect(tuser.getText(), new String(tpass.getPassword()));
                if(ret.startsWith("KO")) {
                    warn.setText(ret);
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
    }
}
