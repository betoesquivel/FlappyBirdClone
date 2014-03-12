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
public class Base implements Constants {

    //control de movimiento
    private int posX;    //posicion en x.       
    private int posY;	//posicion en y.
    
    //control de animaciones
    private Animacion animacion;

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

    /**
     * Access method for the position in X
     * @return posX of type <code>int</code>
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Modifier method setPosX for the position in X
     * @param posX of type <code>int</code>
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }
    
    /**
     * Access method for the position in X
     * @return posX of type <code>int</code>
     */
    public int getPosY() {
        return posY;
    }
    
    /**
     * Modifier method setPosX for the position in X
     * @param posX of type <code>int</code>
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    /**
    * Metodo de acceso que regresa el ancho del icono 
    * @return un objeto de la clase <code>ImageIcon</code> que es el ancho del icono.
    */
   public int getAncho() {
           //return icono.getIconWidth();
           return (new ImageIcon(animacion.getImagen())).getIconWidth();
   }

   /**
    * Metodo de acceso que regresa el alto del icono 
    * @return un objeto de la clase <code>ImageIcon</code> que es el alto del icono.
    */
   public int getAlto() {
           //return icono.getIconHeight();
       return (new ImageIcon(animacion.getImagen())).getIconHeight();
   }

   public Rectangle getPerimetro(int ancho, int alto){
            return new Rectangle(getPosX(), getPosY(), ancho, alto);
    }
}// Fin de la clase Base
