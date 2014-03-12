/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybirdclone;

import java.awt.Toolkit;
import java.net.URL;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author ppesq
 */
public class Pipes extends Base implements Constants {
    
        //variable imagen
    private ImageIcon pipeUp;
    private ImageIcon pipeDown;
    
    //private int xPipeUp;
    //private int xPipeDown;
    //private int yPipeUp;
    //private int yPipeDown;
    
    
    public Pipes() {
        super();
        int x = WINDOW_WIDTH;
        int y = (int)(Math.random()*343);
        this.setPosY(y);
        pipeUp = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(IMG_UPPERGREENPIPE)));
        pipeDown = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(IMG_LOWERGREENPIPE)));
    }
    
    public Image getPipeUp() {
        return (new ImageIcon(pipeUp.getImage())).getImage();
    }
    
    public Image getPipeDown() {
        return (new ImageIcon(pipeDown.getImage())).getImage();
    }
    
    public Rectangle getPerimetro(){
            return new Rectangle(getPosX(),getPosY(),getAncho(),getAlto());
    }
    
    public boolean intersecta(Base obj){
            return getPerimetro().intersects(obj.getPerimetro());
    }
    
    public boolean checkPipeUpCollision(Base obj) {
        
        return false;
    }
    
    public boolean checkPipeDownCollision() {
        
        return false;
    }
    
    public void move() {
        //It's only necessary to move the pipe on x
        //The Y position stays always the same
        
        this.setPosX(this.getPosX() - 1);
    }


}
