/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybirdclone;

import java.awt.Toolkit;
import java.net.URL;
import java.awt.Image;
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
    
    
    public Pipes(int xParam, int yParam) {
        super(xParam, yParam);
        int x = WINDOW_WIDTH;
        int y = (-1)*((int)(Math.random()*218) + 125);
        this.setPosX(this.getPosX() + x);
        this.setPosY(y);
        pipeUp = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(IMG_UPPERGREENPIPE)));
        pipeDown = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(IMG_LOWERGREENPIPE)));
    }
    
    public Image getPipeUp() {
        return pipeUp.getImage();
    }
    
    public Image getPipeDown() {
        return pipeDown.getImage();
    }
    
    public boolean checkPipeCollision() {
        return (this.getPosX() + this.getAncho() < 0);
    }
    
    public boolean checkPipeUpCollision(Base obj) {
        
        //if(obj.getPosY() < )
        return false;
    }
    
    public boolean checkPipeDownCollision() {
        
        return false;
    }
    
    public void move() {
        //It's only necessary to move the pipe on x
        //The Y position stays always the same
        
        this.setPosX(this.getPosX() - GAME_SPEED);
    }


}
