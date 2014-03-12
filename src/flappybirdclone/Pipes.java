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
    private int gap;
    //private int xPipeUp;
    //private int xPipeDown;
    //private int yPipeUp;
    //private int yPipeDown;

    public Pipes(int xParam, int yParam) {
        super(xParam, yParam);
        int x = WINDOW_WIDTH;
        int y = (-1) * ((int) (Math.random() * 210));
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

    public Rectangle getPerimetro(int x, int y) {
        return new Rectangle(x, y, PIPE_WIDTH, PIPE_HEIGHT);
    }

    public boolean intersecta(int x, int y, Base obj) {
        return getPerimetro(x, y).intersects(obj.getPerimetro(BIRD_WIDTH, BIRD_HEIGHT));
    }

    public boolean checkPipeUpCollision(Base obj) {
        return intersecta(getPosX(), getPosY(), obj);
    }

    public boolean checkPipeDownCollision(Base obj) {
        return intersecta(getPosX(), getPosY() + getGap(), obj);
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public void move() {
        //It's only necessary to move the pipe on x
        //The Y position stays always the same

        this.setPosX(this.getPosX() - GAME_SPEED);
    }

}
