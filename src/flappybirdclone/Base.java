package flappybirdclone;

/**
 * Clase Base
 *
 * source: Antonio Mejorado
 *
 * @author José Alberto Esquivel Patiño
 * @version 1.00 2008/6/13
 */
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * Esta clase es una base para personajes dentro de un applet de java. La clase
 * debe de ser inicializada con al menos una animación no nula.
 *
 * @author ppesq
 */
public class Base {

    //control de movimiento
    private int posX;    //posicion en x.       
    private int posY;	//posicion en y.

    //control de animaciones


    /* CONSTRUCTORES */
    /**
     * Constructor method to initiaze object position in 0s
     *
     */
    public Base() {
        this.posX = 0;
        this.posY = 0;
    }
    
    /**
     * Constructor 
     *
     */
    public Base(int x, int y) {
        this.posX = x;
        this.posY = y;
    }


}// Fin de la clase Base