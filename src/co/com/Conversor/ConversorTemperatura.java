package co.com.Conversor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class ConversorTemperatura extends JPanel {

    // Componentes para el Conversor de Temperatura
    private JTextField txtTemperatura;
    private JComboBox<String> comboUnidadesOrigen;
    private JComboBox<String> comboUnidadesDestino;
    private JLabel lblResultado;

    public ConversorTemperatura() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Temperatura:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        txtTemperatura = new JTextField(10);
        add(txtTemperatura, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Unidad Origen:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        comboUnidadesOrigen = new JComboBox<>(new String[]{"°Celcius", "°Farenheit", "Kelvin"});
        add(comboUnidadesOrigen, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Unidad Destino:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        comboUnidadesDestino = new JComboBox<>(new String[]{"°Celcius", "°Farenheit", "Kelvin"});
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
                    double temperatura = Double.parseDouble(txtTemperatura.getText());
                    double resultado = convertirTemperatura(temperatura);
                    lblResultado.setText("Resultado: " + formatoDecimal(resultado) + " " + comboUnidadesDestino.getSelectedItem());
                } catch (NumberFormatException ex) {
                    lblResultado.setText("Error: Ingrese una temperatura válida");
                }
            }
        });
    }

    private double convertirTemperatura(double temperatura) {
        String unidadOrigen = comboUnidadesOrigen.getSelectedItem().toString();
        String unidadDestino = comboUnidadesDestino.getSelectedItem().toString();

        // Convertir la temperatura a Celsius como valor intermedio
        if (unidadOrigen.equals("°Farenheit")) {
            temperatura = convertirAFahrenheitACelsius(temperatura);
        } else if (unidadOrigen.equals("Kelvin")) {
            temperatura = convertirAKelvinACelsius(temperatura);
        }

        // Convertir la temperatura de Celsius a la unidad destino
        if (unidadDestino.equals("°Farenheit")) {
            return convertirACelsiusAFahrenheit(temperatura);
        } else if (unidadDestino.equals("Kelvin")) {
            return convertirACelsiusAKelvin(temperatura);
        }

        return temperatura; // Si la unidad de origen y destino es la misma, retornar la temperatura sin cambios.
    }

    // Métodos de conversión desde Celsius
    private double convertirACelsiusAFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;
    }

    private double convertirACelsiusAKelvin(double celsius) {
        return celsius + 273.15;
    }

    // Métodos de conversión hacia Celsius
    private double convertirAFahrenheitACelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double convertirAKelvinACelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private String formatoDecimal(double valor) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(valor);
    }
}