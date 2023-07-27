package co.com.Conversor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class ConversorDivisas extends JPanel {

    // Componentes para el Conversor de Divisas
    private JTextField txtMonto;
    private JComboBox<String> comboDivisaOrigen;
    private JComboBox<String> comboDivisaDestino;
    private JLabel lblResultado;

    // Tasas de conversión de divisas (Dólar, Euro, Yen, Peso Colombiano, Peso
    // Mexicano)
    private double[] tasasDivisas = {1.0, 0.85, 110.66, 3874.0, 20.20};
    private String[] nombresDivisas = {"Dólar", "Euro", "Yen", "Peso Colombiano", "Peso Mexicano"};

    public ConversorDivisas() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Monto:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        txtMonto = new JTextField(10);
        add(txtMonto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Divisa Origen:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        comboDivisaOrigen = new JComboBox<>(nombresDivisas);
        add(comboDivisaOrigen, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Divisa Destino:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        comboDivisaDestino = new JComboBox<>(nombresDivisas);
        add(comboDivisaDestino, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        JButton btnConvertir = new JButton("Convertir");
        add(btnConvertir, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        lblResultado = new JLabel();
        add(lblResultado, gbc);

        btnConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double monto = Double.parseDouble(txtMonto.getText());
                    double resultado = convertirDivisas(monto);
                    lblResultado.setText("Resultado: " + formatoDecimal(resultado) + " " + comboDivisaDestino.getSelectedItem());
                } catch (NumberFormatException ex) {
                    lblResultado.setText("Error: Ingrese un monto válido");
                }
            }
        });
    }

    private double convertirDivisas(double monto) {
        int indiceOrigen = comboDivisaOrigen.getSelectedIndex();
        int indiceDestino = comboDivisaDestino.getSelectedIndex();
        double tasaOrigen = tasasDivisas[indiceOrigen];
        double tasaDestino = tasasDivisas[indiceDestino];
        return monto * tasaDestino / tasaOrigen;
    }

    private String formatoDecimal(double valor) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(valor);
    }
}