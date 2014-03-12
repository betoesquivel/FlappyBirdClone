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
import javax.swing.JFrame;

/**
 * El applet AppletAnimacion muestra una animación en pantalla.
 */
public class Game extends JFrame implements Constants, Runnable, KeyListener, MouseListener {

    private Image dbImage;    // Imagen a proyectar
    private Graphics dbg;	// Objeto grafico
    private Image background;

    
    public Game(){
        init(); 
    }
    
    public void init(){
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        
        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update() {
        
    }
    
    public void checkCollision() {
        
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
