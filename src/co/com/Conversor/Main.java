package co.com.Conversor;

import javax.swing.SwingUtilities;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                (new ConversorApp()).setVisible(true);
            }
        });
    }
}