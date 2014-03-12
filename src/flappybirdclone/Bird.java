/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybirdclone;

import java.awt.Image;

/**
 *
 * @author ppesq
 */
public class Bird extends Base implements Constants {
    //movement
    private int ySpeed = 0; 

    //animations
    Animacion basic; 
    
    public Bird(int posX, int posY, Animacion a1) {
        setPosX(posX);
        setPosY(posY);
        ySpeed = 0; 
        basic = a1; 
    }
    
    public void resetPosition() {
        setPosX(BIRD_DEFAULTX);
        setPosY(BIRD_DEFAULTY);
        setySpeed(0);
    }
    public void flap(){
        setySpeed(-30);
    }
    
    public void updateAnimation(long tiempoTranscurrido){
        basic.actualiza(tiempoTranscurrido);
    }
    
    public void move(double timer){
        double newYSpeed = getySpeed() + timer * G;  
        setySpeed( (int)Math.round(newYSpeed) );
        setPosY((getPosY() + getySpeed()));
    }

    //getters and setters
    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }
    
    public Image getImage(){
        return basic.getImagen(); 
    }
}//Fin de la clase Bird
