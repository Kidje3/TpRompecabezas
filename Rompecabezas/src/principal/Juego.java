package principal;

import javax.swing.JOptionPane;

import interfaz.MenuInicio;
import negocio.JuegoRompecabeza;

public class Juego {
	
	JuegoRompecabeza miJuegoRompecabeza;
	
	public Juego() {
		miJuegoRompecabeza = new JuegoRompecabeza();
		presentarVentana();
	}

	private void presentarVentana() {
		MenuInicio miMenuInicio = new MenuInicio();
		miMenuInicio.setVisible(true);
		
		
	}
	

}
