package client;

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
}
