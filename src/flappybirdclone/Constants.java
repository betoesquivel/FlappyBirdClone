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
public interface Constants {
    //window
    public final int WINDOW_HEIGHT = 767;
    public final int WINDOW_WIDTH = 432;
    
    //flappy bird
    public final int BIRD_WIDTH = 51;
    public final int BIRD_HEIGHT = 36;
    
    //pipes
    public final int PIPE_HEIGHT = 480; 
    public final int PIPE_WIDTH = 78; 
    public final int PIPE_GAP = 220; 
    
    //background
    
    //game settings
    public final int G = 2; //gravity
    public final int GAME_SPEED = 1; //Change in x of the whole game (background and pipes)
    public final int LEVEL1GAP = PIPE_WIDTH * 5; 
}
