package interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuInicio extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nombreUsuario;
    
    public static String jugador; // Nombre del jugador

    public MenuInicio() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
        iniciarComponentes();
        
        setTitle("Rompecabezas");
        
		setLocationRelativeTo(null);// para fijar la ventana al centro de la pantalla
		setResizable(false);//
        
    }
    private void iniciarComponentes() {
    	
        setBounds(100, 100, 480, 640);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(231, 224, 95));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitulo = new JLabel("ROMPECABEZA DESLIZABLE");
        lblTitulo.setFont(new Font("Segoe UI Variable", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(36, 11, 356, 34);
        contentPane.add(lblTitulo);
      
        
      
        
        nombreUsuario = new JTextField("Ingrese su nombre");
        
        //Borra "ingrese su nombre" cuando hace click       
        nombreUsuario.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		nombreUsuario.setText("");
        	}
        });
        
        //Deja seleccionado "ingrese su nombre"
        nombreUsuario.addFocusListener(new FocusAdapter() {            
            public void focusGained(FocusEvent e) {
                // Seleccionar todo el texto cuando gana foco
                if (nombreUsuario.getText().equals("Ingrese su nombre")) {
                    nombreUsuario.selectAll();
                }		
            }
        });		
        
        
        nombreUsuario.setHorizontalAlignment(SwingConstants.LEFT);
        nombreUsuario.setBounds(123, 81, 195, 20);
        contentPane.add(nombreUsuario);
        nombreUsuario.setColumns(10);
        
        JButton btnInicioJuego = new JButton("Iniciar Juego!");
        btnInicioJuego.setBounds(114, 258, 223, 121);
        contentPane.add(btnInicioJuego);
        
        JComboBox selectorDificultad = new JComboBox();
        selectorDificultad.setBounds(123, 124, 195, 22);
        contentPane.add(selectorDificultad);
        selectorDificultad.setModel(new DefaultComboBoxModel(new String[] {"(Nivel de dificultad)","Facil","Medio","Dificil"}));
        
       
        
        btnInicioJuego.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jugador = nombreUsuario.getText(); // Guardar el nombre del jugador
                int numeroDificultad = selectorDificultad.getSelectedIndex();
                
                String valorNivel=(String)selectorDificultad.getSelectedItem(); // para guardar el nivel de dificultad
                GUIRompecabeza rompecabeza = new GUIRompecabeza(); // Lanzar el juego
                rompecabeza.setVisible(true);
                dispose(); // Cerrar el menú de inicio
            }
        });
    	
    }
}
