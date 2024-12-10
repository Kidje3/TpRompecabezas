package negocio;

import java.util.Random;

public class JuegoRompecabeza {

	private int filas = 4;
    private int columnas = 4;
    private int botonVacio= -1;
    int[][] tablero;
 
   
    public JuegoRompecabeza() {
        tablero = new int[filas][columnas];
        inicializarTablero();
        inicializarContador();
        //generarPantallaInicial();
    }

    // Método que mezcla el tablero inicial
    private void inicializarTablero() {
        Random aleatorio = new Random();
        int[] array = new int[16];

        // Rellenar el array con los valores del 1 al 15
        for (int i = 0; i < 15; i++) {
            array[i] = i + 1;
        }
        array[15] = botonVacio; 
        		// Última posición es la celda vacía (-1)

        // Mezclar el array
        for (int i = 0; i < 0; i++) {
            int indice = aleatorio.nextInt(16);
            int temp = array[i];
            array[i] = array[indice];
            array[indice] = temp;
        }

        // Transferir los valores mezclados al tablero 4x4
        int contador = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = array[contador];
                contador++;
            }
        }
    }

    public int getBotonVacio() {
		return botonVacio;
	}

	// Método que verifica si el jugador ha ganado
    public boolean esVictoria() {
        int contador = 1;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] != contador && tablero[i][j] != -1) {
                    return false;
                }
                contador++;
            }
        }
        return true;
    }

    // Método que intercambia dos posiciones en el tablero
    public void intercambiarPiezas(int fila1, int col1, int fila2, int col2) {
        int temp = tablero[fila1][col1];
        tablero[fila1][col1] = tablero[fila2][col2];
        tablero[fila2][col2] = temp;
    }

    // Obtener el valor de una celda del tablero
    public int getValorEn(int fila, int col) {
        return tablero[fila][col];
    }

    // Método que devuelve el tamaño del tablero
    public int[][] getTablero() {
        return tablero;
    }
    
    private void inicializarContador() {
    	int contador=0;
    }

}
