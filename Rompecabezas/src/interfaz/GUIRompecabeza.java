package interfaz;

import negocio.JuegoRompecabeza;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public class GUIRompecabeza extends JFrame implements KeyListener {
    //private static final long serialVersionUID = 1L;
    private JButton[][] botones;
    private JuegoRompecabeza logicaJuego;
    private int filas = 4, columnas = 4;
    private int variableMov = 0;
    private int filaVacia, colVacia; // Para rastrear la posición del botón vacío
    private JLabel contadorMovimientos; // Etiqueta para el contador de movimientos

    public GUIRompecabeza() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logicaJuego = new JuegoRompecabeza();
        botones = new JButton[filas][columnas];
        
        iniciarComponentes();
        setTitle("Rompecabeza");
        setLocationRelativeTo(null);
        setResizable(false);
        
        
    }
    
    private void iniciarComponentes() {
        // Cambiar el layout a BorderLayout para poder separar la parte del contador
        setLayout(new BorderLayout());
        
        // Panel principal para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(filas, columnas));
        inicializarBoton(panelBotones);
        add(panelBotones, BorderLayout.CENTER);
        
        // Panel inferior para el contador de movimientos
        JPanel panelInferior = new JPanel();
        inicializarContadorMovimiento(panelInferior);
        add(panelInferior, BorderLayout.SOUTH);
        
        setBounds(100, 100, 480, 640); // Ajustar tamaño de la ventana
        
        
        addKeyListener(this);  // Agregar KeyListener
        setFocusable(true);    // Hacer que la ventana escuche eventos de teclado
        requestFocusInWindow(); // Solicitar enfoque en la ventana
    }

    private void inicializarContadorMovimiento(JPanel panelInferior) {
        // Inicializar el JLabel para el contador de movimientos
        contadorMovimientos = new JLabel("Movimientos: " + variableMov);
        panelInferior.add(contadorMovimientos); // Añadir el contador al panel inferior
    }

    private void inicializarBoton(JPanel panelBotones) {
        // Inicializar los botones y agregarlos
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j] = new JButton();
                actualizarBoton(i, j);
                
             // Deshabilitar el enfoque por mouse y eventos del mouse
                botones[i][j].setFocusable(false);
                botones[i][j].setRolloverEnabled(false); // Desactiva el efecto de rollover

                // Remover todos los listeners del mouse
                for (MouseListener ml : botones[i][j].getMouseListeners()) {
                    botones[i][j].removeMouseListener(ml);
                }
                for (MouseMotionListener mml : botones[i][j].getMouseMotionListeners()) {
                    botones[i][j].removeMouseMotionListener(mml);
                }
                for (MouseWheelListener mwl : botones[i][j].getMouseWheelListeners()) {
                    botones[i][j].removeMouseWheelListener(mwl);
                }

                // Añadir un listener vacío para evitar cualquier comportamiento por defecto
                botones[i][j].addMouseListener(new MouseAdapter() {});
                panelBotones.add(botones[i][j]); // Añadir cada botón al panel de botones
            }
        }
    }

    private void actualizarBoton(int fila, int col) {
        int valor = logicaJuego.getValorEn(fila, col);
        if (valor == -1) {
            botones[fila][col].setText("");
            filaVacia = fila; // Guardar la posición vacía
            colVacia = col;
        } else {
            botones[fila][col].setText(String.valueOf(valor));
        }
    }

    private void moverPieza(int fila, int col) {
        // Verificar si la celda seleccionada es adyacente al botón vacío
        if ((Math.abs(fila - filaVacia) == 1 && col == colVacia) || (Math.abs(col - colVacia) == 1 && fila == filaVacia)) {
            logicaJuego.intercambiarPiezas(fila, col, filaVacia, colVacia);
            filaVacia = fila; // Actualizar la posición del botón vacío
            colVacia = col;
            actualizarTablero();
            actualizarMovimiento();
            verificarVictoria();
        }
    }

    private void actualizarMovimiento() {
        variableMov++; // Incrementar el número de movimientos
        contadorMovimientos.setText("Movimientos: " + variableMov); // Actualizar el texto del JLabel
    }

    private void actualizarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                actualizarBoton(i, j);
            }
        }
    }

    private void verificarVictoria() {
        if (logicaJuego.esVictoria()) {
            JOptionPane.showMessageDialog(this, "¡Felicidades " + MenuInicio.jugador + ", has ganado!");
            new TablaPuntuacion().setVisible(true);
            dispose(); // Cerrar la ventana actual
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                moverTeclado(filaVacia - 1, colVacia); // Mover hacia arriba (botón vacío sube)
                break;
            case KeyEvent.VK_DOWN:
                moverTeclado(filaVacia + 1, colVacia); // Mover hacia abajo (botón vacío baja)
                break;
            case KeyEvent.VK_LEFT:
                moverTeclado(filaVacia, colVacia - 1); // Mover hacia la izquierda (botón vacío a la izquierda)
                break;
            case KeyEvent.VK_RIGHT:
                moverTeclado(filaVacia, colVacia + 1); // Mover hacia la derecha (botón vacío a la derecha)
                break;
        }
    }

    private void moverTeclado(int nuevaFila, int nuevaCol) {
        // Verificar si la nueva posición está dentro de los límites del tablero
        if (nuevaFila >= 0 && nuevaFila < filas && nuevaCol >= 0 && nuevaCol < columnas) {
            moverPieza(nuevaFila, nuevaCol); // Mover la pieza seleccionada
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}


