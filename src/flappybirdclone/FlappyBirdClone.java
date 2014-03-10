/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybirdclone;

import javax.swing.JFrame;

/**
 *
 * @author ppesq
 */
public class FlappyBirdClone {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game flappyClone = new Game();
        flappyClone.setVisible(true);
        flappyClone.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
