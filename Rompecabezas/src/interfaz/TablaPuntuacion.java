package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TablaPuntuacion extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel labelFelicitaciones;

    public TablaPuntuacion() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 400);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(204, 204, 255));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        labelFelicitaciones = new JLabel("¡Felicidades " + MenuInicio.jugador + "!");
        labelFelicitaciones.setFont(new Font("Tahoma", Font.PLAIN, 22));
        labelFelicitaciones.setHorizontalAlignment(SwingConstants.CENTER);
        labelFelicitaciones.setBounds(53, 61, 272, 36);
        contentPane.add(labelFelicitaciones);
        
        JButton btnJugarDeNuevo = new JButton("Jugar de nuevo");
        btnJugarDeNuevo.setBounds(95, 236, 192, 42);
        contentPane.add(btnJugarDeNuevo);
        
        btnJugarDeNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MenuInicio().setVisible(true);
                dispose(); // Cerrar ventana
            }
        });
    }
}


