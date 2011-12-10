package client;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.awt.event.*;
import java.lang.*;

public class ViewProfile extends JPanel implements Role {

    JLabel id;
    JLabel tid;
    JLabel name;
    JLabel tname;
    JLabel lastname;
    JLabel tlastname;
    JLabel address;
    JLabel taddress;
    JLabel city;
    JLabel tcity;
    JLabel email;
    JLabel temail;
    JLabel phone;
    JLabel tphone;
    SourceUser su;
    private int idUser;
    private User user;

    /* empty constructor for children */
    protected ViewProfile() {
    }

    public ViewProfile(int idUser) {
        this.idUser = idUser;
        su = new SourceUser();
        su.loadData(idUser);
        user = su.getUser(idUser);
        initComponents();
    }

    private void initComponents() {

        JPanel info0 = new JPanel(), info1 = new JPanel(), info2 = new JPanel(), info3 = new JPanel(), info4 = new JPanel(), info5 = new JPanel(), info6 = new JPanel(), info7 = new JPanel();
        this.setLayout(new GridLayout(8, 1));
        id = new JLabel("ID:");
        name = new JLabel("Name:");
        lastname = new JLabel("Last Name:");
        address = new JLabel("Address:");
        city = new JLabel("City:");
        email = new JLabel("E-mail:");
        phone = new JLabel("Phone:");
        tid = new JLabel(""+user.getId());
        if(0==(tid.getText().compareTo("null"))){
            tid.setText("");
        }
        tname = new JLabel(user.getName());
        if(0==(tname.getText().compareTo("null"))){
            tname.setText("");
        }
        tlastname = new JLabel(user.getLastName());
        if(0==(tlastname.getText().compareTo("null"))){
            tlastname.setText("");
        }
        taddress = new JLabel(user.getAddress());
        if(0==(taddress.getText().compareTo("null"))){
            taddress.setText("");
        }
        tcity = new JLabel(user.getCity());
        if(0==(tcity.getText().compareTo("null"))){
            tcity.setText("");
        }
        temail = new JLabel(user.getEmail());
        if(0==(temail.getText().compareTo("null"))){
            temail.setText("");
        }
        tphone = new JLabel(user.getPhone());
        if(0==(tphone.getText().compareTo("null"))){
            tphone.setText("");
        }
        info0.setLayout(new FlowLayout());
        info1.setLayout(new FlowLayout());
        info2.setLayout(new FlowLayout());
        info3.setLayout(new FlowLayout());
        info4.setLayout(new FlowLayout());
        info5.setLayout(new FlowLayout());
        info6.setLayout(new FlowLayout());
        info7.setLayout(new FlowLayout());
        info0.add(id);
        info0.add(tid);
        info1.add(name);
        info1.add(tname);
        info2.add(lastname);
        info2.add(tlastname);
        info3.add(address);
        info3.add(taddress);
        info4.add(city);
        info4.add(tcity);
        info5.add(email);
        info5.add(temail);
        info6.add(phone);
        info6.add(tphone);
        this.add(info0);
        this.add(info1);
        this.add(info2);
        this.add(info3);
        this.add(info4);
        this.add(info5);
        this.add(info6);
        this.add(info7);        
    }

    @Override
    public Iterable<JMenuItem> getMenuItems() {
        JMenuItem edit = new JMenuItem("Edit Profile");
        final ViewProfile v = this;
        ActionListener akce = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeFrame(idUser, v);
            }
        };
        edit.addActionListener(akce);
        return Arrays.asList(edit);
    }

    void updateData(){
        su.loadData(idUser);
        user = su.getUser(idUser);
        tname.setText(user.getName());
        tlastname.setText(user.getLastName());
        taddress.setText(user.getAddress());
        tcity.setText(user.getCity());
        temail.setText(user.getEmail());
        tphone.setText(user.getPhone());
    }
}
