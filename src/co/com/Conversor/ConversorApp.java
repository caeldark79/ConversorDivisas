package co.com.Conversor;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class ConversorApp extends JFrame {
    public ConversorApp() {
        this.setTitle("Conversor de Divisas, Medidas y Temperatura");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(3);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Conversor de Divisas", new ConversorDivisas());
        tabbedPane.addTab("Conversor de Medidas", new ConversorMedidas());
        tabbedPane.addTab("Conversor de Temperatura", new ConversorTemperatura());
        this.add(tabbedPane);
    }
}