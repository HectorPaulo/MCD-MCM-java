import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculadora extends JFrame {
    private JTextField numero1Field;
    private JTextField numero2Field;
    private JTextArea resultadoArea;

    public Calculadora() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel numero1Label = new JLabel("PrimerNúmero:");
        panel.add(numero1Label);

        numero1Field = new JTextField();
        panel.add(numero1Field);

        JLabel numero2Label = new JLabel("SegundoNúmero:");
        panel.add(numero2Label);

        numero2Field = new JTextField();
        panel.add(numero2Field);

        JButton calcularButton = new JButton("Calcular");
        calcularButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });
        panel.add(calcularButton);

        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        panel.add(scrollPane);

        add(panel);
        setVisible(true);
    }

    private void calcular() {
        try {
            int numero1 = Integer.parseInt(numero1Field.getText());
            int numero2 = Integer.parseInt(numero2Field.getText());

            int opcion = JOptionPane.showOptionDialog(null, "Seleccione una operación:",
                    "Operaciones", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, new String[]{"Máximo Común Divisor", "División", "Números Perfectos"}, null);

            switch (opcion) {
                case 0:
                    calcularMCD(numero1, numero2);
                    break;
                case 1:
                    calcularDivision(numero1, numero2);
                    break;
                case 2:
                    calcularNumerosPerfectos(numero1, numero2);
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese números enteros válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calcularMCD(int numero1, int numero2) {
        try {
            int mcd = calcularMCDRecursivo(Math.abs(numero1), Math.abs(numero2));
            resultadoArea.setText("Máximo Común Divisor de " + numero1 + " y " + numero2 + " es: " + mcd);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int calcularMCDRecursivo(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("No se puede calcular el MCD de un número con 0.");
        }
        return (b == 0) ? a : calcularMCDRecursivo(b, a % b);
    }

    private void calcularDivision(int numero1, int numero2) {
        try {
            if (numero2 == 0) {
                throw new IllegalArgumentException("No se puede dividir por 0.");
            }
            double division = (double) numero1 / numero2;
            resultadoArea.setText("La división de " + numero1 + " entre " + numero2 + " es: " + division);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calcularNumerosPerfectos(int numero1, int numero2) {
        try {
            StringBuilder perfectos = new StringBuilder("Números perfectos en el rango de " + numero1 + " a " + numero2 + ":\n");
            for (int i = numero1; i <= numero2; i++) {
                if (esNumeroPerfecto(i)) {
                    perfectos.append(i).append(", ");
                }
            }
            resultadoArea.setText(perfectos.toString());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean esNumeroPerfecto(int numero) {
        if (numero <= 1) {
            return false;
        }
        int suma = 1;
        for (int i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                suma += i;
                if (i * i != numero) {
                    suma += numero / i;
                }
            }
        }
        return suma == numero;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculadora();
            }
        });
    }
}
