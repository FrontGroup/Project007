package client;
import javax.swing.*;
import java.awt.*;

public class EmployeeFrame extends JFrame {

    JLabel id;
    JLabel name;
    JTextField tname;
    JLabel lastname;
    JTextField tlastname;
    JLabel address;
    JTextField taddress;
    JLabel city;
    JTextField tcity;
    JLabel email;
    JTextField temail;
    JLabel phone;
    JTextField tphone;
    JMenuBar menubar;
    JPanel panel;
    JButton back;

    public EmployeeFrame() {
        initComponents();
    }

    public static void main(String[] args) {
        EmployeeFrame ef = new EmployeeFrame();
    }

    private void initComponents() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Systém SPRÁVCE");
        setSize(700, 700);
        setMinimumSize(new Dimension(700, 700));
        setVisible(true);
        menubar = new JMenuBar();
        setJMenuBar(menubar);
        JMenu system = new JMenu("System");
        menubar.add(system);
        JMenu functions = new JMenu("Functions");
        menubar.add(functions);
        JMenu help = new JMenu("Help");
        menubar.add(help);
        help.add(Main.getAboutMenuItem());
        JMenuItem exit = new JMenuItem("EXIT");
        system.add(exit);
        panel = new JPanel();
        setLayout(new FlowLayout(FlowLayout.LEFT));
        getContentPane().add(panel);
        JPanel info0 = new JPanel(), info1 = new JPanel(), info2 = new JPanel(), info3 = new JPanel(), info4 = new JPanel(), info5 = new JPanel(), info6 = new JPanel(), info7 = new JPanel();
        panel.setLayout(new GridLayout(8, 1));
        id = new JLabel("ID:");
        name = new JLabel("Name:");
        lastname = new JLabel("Last Name:");
        address = new JLabel("Address:");
        city = new JLabel("City:");
        email = new JLabel("E-mail:");
        phone = new JLabel("Phone:");
        tname = new JTextField(20);
        tlastname = new JTextField(20);
        taddress = new JTextField(20);
        tcity = new JTextField(20);
        temail = new JTextField(20);
        tphone = new JTextField(20);
        back = new JButton("Back");
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
        info7.add(back);
        panel.add(info0);
        panel.add(info1);
        panel.add(info2);
        panel.add(info3);
        panel.add(info4);
        panel.add(info5);
        panel.add(info6);
        panel.add(info7);
        pack();
    }
}