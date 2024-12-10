package negocio;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Teclado extends JFrame {

    private JPanel panel;

    public Teclado() {
        // Configuración del JFrame
        setTitle("Ejemplo de KeyListener");
        setBounds(100, 100, 480, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        // Crear e inicializar el panel
        panel = new JPanel();
        panel.setBounds(0, 0, 480, 640);
        getContentPane().add(panel);

        // Agregar KeyListener al panel
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Detectar la tecla presionada
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        JOptionPane.showMessageDialog(null, "Tecla UP presionada");
                        break;
                    case KeyEvent.VK_DOWN:
                        JOptionPane.showMessageDialog(null, "Tecla DOWN presionada");
                        break;
                    case KeyEvent.VK_LEFT:
                        JOptionPane.showMessageDialog(null, "Tecla LEFT presionada");
                        break;
                    case KeyEvent.VK_RIGHT:
                        JOptionPane.showMessageDialog(null, "Tecla RIGHT presionada");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Otra tecla presionada");
                        break;
                }
            }
        });
        
        // Hacer que el panel pueda recibir el foco
        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Teclado frame = new Teclado ();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
