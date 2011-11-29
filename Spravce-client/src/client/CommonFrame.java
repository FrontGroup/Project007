package client;
import javax.swing.*;
import java.awt.*;

public class CommonFrame extends JFrame {

    JLabel id;
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

    public CommonFrame() {
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
        tname = new JLabel(su.getName());
        tlastname = new JLabel(su.getLastname());
        taddress = new JLabel(su.getAddress());
        tcity = new JLabel(su.getCity());
        temail = new JLabel(su.getEmail());
        tphone = new JLabel(su.getPhone());
        info0.setLayout(new FlowLayout());
        info1.setLayout(new FlowLayout());
        info2.setLayout(new FlowLayout());
        info3.setLayout(new FlowLayout());
        info4.setLayout(new FlowLayout());
        info5.setLayout(new FlowLayout());
        info6.setLayout(new FlowLayout());
        info7.setLayout(new FlowLayout());
        info0.add(id);
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
        pack();
    }
}
