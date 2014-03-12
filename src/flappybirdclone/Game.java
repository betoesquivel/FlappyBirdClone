/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybirdclone;

/**
 *
 * @author ppesq
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import javax.swing.JFrame;

/**
 * El applet AppletAnimacion muestra una animación en pantalla.
 */
public class Game extends JFrame implements Constants, Runnable, KeyListener, MouseListener {

    private Image dbImage;    // Imagen a proyectar
    private Graphics dbg;	// Objeto grafico
    private Image background;   //Background image
    private LinkedList lista;   //List for pipes
    private Pipes pipe;     //Objeto Pipes

    
    public Game(){
        init(); 
    }
    
    public void init(){
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        lista = new LinkedList();
        int contPipes = 0;
        while(contPipes < TOTAL_PIPES) {
            //URL rURL = this.getClass().getResource("imagenesMalo/perro1.gif");
            pipe = new Pipes();
            
            lista.push(pipe);
            contPipes++;
        }
        
        //Posiciona al gordo en la mitad derecha del applet en la parte de hasta abajo.
//        gordo.setPosX(3 * getWidth() / 4 - gordo.getAncho() / 2);
//        gordo.setPosY(getHeight() - gordo.getAlto() - gordo.getAlto() / 2 + 4);
//
//        burger = crearMalo(1);
//
//        //inicializo el marcador en 0
//        score = 0;
//
//        //el juego no esta pausado
//        pausado = false;
//
//        //se cargan los sonidos
//        sonido = new SoundClip(saURL);
//        bomb = new SoundClip(baURL);
//
//        background = Toolkit.getDefaultToolkit().getImage(backgroundURL);
//        //Pinta el fondo del Applet con una imagen		
//        setBackground(Color.white);
//        addKeyListener(this);
//        addMouseListener(this);
    }
    
    public Bird createBird(int posX, int posY){
        Animacion flapping = new Animacion();
        
        Bird newBird = new Bird(posX, posY, flapping);
        
        return newBird; 
    }
    
    public void start() {
        
    }
    
    public void stop() {
        
    }
    
    public void destroy() {
        
    }

    public void run() {
        //Guarda el tiempo actual del sistema
            //tiempoActual = System.currentTimeMillis();
            while (true) {
                //update();
                //checkCollision();
                repaint();    // Se actualiza el <code>Applet</code> repintando el contenido.
                try	{
                        // El thread se duerme.
                        Thread.sleep (20);
                }
                catch (InterruptedException ex)	{
                        System.out.println("Error en " + ex.toString());
                }
            }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update() {
        
    }
    
    public void checkCollision() {
        
    }
    /**
    * Metodo <I>paint</I> sobrescrito de la clase <code>Applet</code>,
    * heredado de la clase Container.<P>
    * En este metodo lo que hace es actualizar el contenedor
    * @param g es el <code>objeto grafico</code> usado para dibujar.
    */
   public void paint(Graphics g) {
           // Inicializan el DoubleBuffer
           if (dbImage == null){
                   dbImage = createImage (this.getSize().width, this.getSize().height);
                   dbg = dbImage.getGraphics ();
           }

           // Actualiza la imagen de fondo.
           dbg.setColor(getBackground ());
           dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

           // Actualiza el Foreground.
           dbg.setColor(getForeground());
           paint1(dbg);

           // Dibuja la imagen actualizada
           g.drawImage (dbImage, 0, 0, this);
   }
   
   public void paint1(Graphics g) {
        if ( lista != null) { //niño != null &&
                    
            //Dibuja la imagen en la posicion actualizada
            //g.drawImage(niño.getImagenI(), niño.getPosX(),niño.getPosY(), this);
            for(int i = 0; i < lista.size(); i++) {
                pipe = (Pipes)(lista.get(0));
                g.drawImage(pipe.getPipeUp(), pipe.getPosX(),pipe.getPosY(), this);
                g.drawImage(pipe.getPipeDown(), pipe.getPosX(),pipe.getPosY() + GAP_Y_LVL_1, this);
            }

            g.drawString("Score: " , 10, 10);

        } else {
                //Da un mensaje mientras se carga el dibujo	
                g.drawString("No se cargo la imagen..", 20, 20);
        }
    } 
    //KEYBOARD CONTROLS
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
