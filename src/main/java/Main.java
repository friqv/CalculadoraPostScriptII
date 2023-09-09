import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                crearVentana();
            }
        });
    }

    private static void crearVentana() {
        JFrame ventana = new JFrame("CALCULADORA POSTSCRIPT");
        ventana.setSize(1000, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);

        Color colorFondo = new Color(255, 248, 220);

        JButton botonSolicitarExpresion = new JButton("Solicitar Expresión");
        JLabel titulo = new JLabel("CALCULADORA");
        titulo.setFont(new Font("Arial", Font.BOLD, 36));

        ventana.getContentPane().setBackground(colorFondo);

        botonSolicitarExpresion.setBackground(Color.DARK_GRAY);
        botonSolicitarExpresion.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        JPanel panelSuperior = new JPanel();

        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));

        panelSuperior.add(titulo);
        panel.add(botonSolicitarExpresion);

        // Agregar un JLabel para mostrar el resultado
        JLabel resultadoLabel = new JLabel("Resultado: ");
        resultadoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(resultadoLabel);

        ImageIcon imagen = new ImageIcon("C:\\Users\\CM\\OneDrive\\Escritorio\\Calculadora PostScript II\\src\\main\\java\\kisspng-calculator-computer-file-vector-calculator-5a6bf4a2d92bb1.9008657415170244188895-removebg-preview (1).png");
        JLabel imagenLabel = new JLabel(imagen);
        panel.add(imagenLabel);

        panelSuperior.setBackground(colorFondo);
        panel.setBackground(colorFondo);

        ventana.add(panelSuperior, BorderLayout.NORTH);
        ventana.add(panel);

        ventana.setLocationRelativeTo(null);

        //Agregar ActionListener para el botón "Solicitar Expresión"
        botonSolicitarExpresion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String expresion = JOptionPane
                        .showInputDialog(ventana, "Ingrese la expresión PostScript:");
                if (expresion != null && !expresion.isEmpty()) {
                    PostScript postScript = new PostScript();
                    double resultado = postScript.ejecutar(expresion);

                    // Registrar el resultado
                    logger.info("Resultado: " + resultado);

                    // Mostrar el resultado en la etiqueta
                    resultadoLabel.setText("Resultado: " + resultado);
                } else {
                    logger.warn("El usuario no ingresó una expresión válida.");
                }
            }
        });

        ventana.setVisible(true);
    }
}
