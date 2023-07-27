package co.com.Conversor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class ConversorMedidas extends JPanel {

    // Componentes para el Conversor de Medidas
    private JTextField txtMedida;
    private JComboBox<String> comboUnidadesOrigen;
    private JComboBox<String> comboUnidadesDestino;
    private JLabel lblResultado;

    // Factores de conversión de medidas (cm, pulgadas, metros, pies)
    private double[] factoresMedidas = {1.0, 0.393701, 0.01, 0.0328084};
    private String[] nombresUnidades = {"cm", "pulgadas", "metros", "pies"};

    public ConversorMedidas() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Medida:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        txtMedida = new JTextField(10);
        add(txtMedida, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Unidad Origen:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        comboUnidadesOrigen = new JComboBox<>(nombresUnidades);
        add(comboUnidadesOrigen, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Unidad Destino:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        comboUnidadesDestino = new JComboBox<>(nombresUnidades);
        add(comboUnidadesDestino, gbc);

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
                    double medida = Double.parseDouble(txtMedida.getText());
                    double resultado = convertirMedida(medida);
                    lblResultado.setText("Resultado: " + formatoDecimal(resultado) + " " + comboUnidadesDestino.getSelectedItem());
                } catch (NumberFormatException ex) {
                    lblResultado.setText("Error: Ingrese una medida válida");
                }
            }
        });
    }

    private double convertirMedida(double medida) {
        int indiceOrigen = comboUnidadesOrigen.getSelectedIndex();
        int indiceDestino = comboUnidadesDestino.getSelectedIndex();
        double factorOrigen = factoresMedidas[indiceOrigen];
        double factorDestino = factoresMedidas[indiceDestino];
        return medida * factorDestino / factorOrigen;
    }

    private String formatoDecimal(double valor) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(valor);
    }
}