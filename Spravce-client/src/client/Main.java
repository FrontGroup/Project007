package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Main trida pro klienta.
 * @author lucas
 */
public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
    // "About" menu common for all frames.
    private static JMenuItem about = null;

    public static JMenuItem getAboutMenuItem() {
        if (about == null) {
            about = new JMenuItem("About program");
            about.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    JOptionPane.showMessageDialog(null, "This program was developed by FrontGroup. \n\r Version 2011.",
                            "About program", JOptionPane.INFORMATION_MESSAGE);
                }
            });
        }
        return about;
    }
}
