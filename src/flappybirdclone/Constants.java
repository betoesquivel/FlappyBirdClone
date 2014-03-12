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
    //image urls strings
    public final String IMG_BLUEBIRDMID = "images/blueBirdMid.png";
    public final String IMG_BLUEBIRDHIGH = "images/blueBirdHigh.png";
    public final String IMG_BLUEBIRDLOW = "images/blueBirdLow.png";
    public final String IMG_GREENBIRDMID = "images/blueBirdMid.png";
    public final String IMG_GREENBIRDHIGH = "images/blueBirdHigh.png";
    public final String IMG_GREENBIRDLOW = "images/blueBirdLow.png";
    
    public final String IMG_BACKGROUNDNIGHT = "images/backgroundNight.png";
    public final String IMG_BACKGROUNDDAY = "images/backgroundDay.png";
    public final String IMG_FLOOR = "images/Floor.png";
    
    public final String IMG_LOWERGREENPIPE = "images/lowerGreenPipe.png";
    public final String IMG_UPPERGREENPIPE = "images/upperGreenPipe.png";
    public final String IMG_LOWERREDPIPE = "images/lowerRedPipe.png";
    public final String IMG_UPPERREDPIPE = "images/upperRedPipe.png";
    
    public final String IMG_READY = "images/ready.png";
    public final String IMG_PLAYBUTTON = "images/playButton.png";
    public final String IMG_PAUSEBUTTON = "images/pauseButton.png";
    
    //sound urls strings
    public final String SND_FLAP = "sounds/flap.wav";
    public final String SND_FAIL = "sounds/fail.wav";
    public final String SND_POINT = "sounds/mariobroscoin.wav";
    //window
    public final int WINDOW_HEIGHT = 767;
    public final int WINDOW_WIDTH = 432;
    
    //flappy bird
    public final int BIRD_WIDTH = 51;
    public final int BIRD_HEIGHT = 36;
    public final int BIRD_DEFAULTX = 100; 
    public final int BIRD_DEFAULTY = WINDOW_HEIGHT / 2 - BIRD_HEIGHT / 2; 
    
    //pipes
    public final int PIPE_HEIGHT = 480; 
    public final int PIPE_WIDTH = 78; 
    public final int PIPE_GAP = 220; 
    
    //background
    
    //game settings
    public final double G = 2; //gravity
    public final int GAME_SPEED = 1; //Change in x of the whole game (background and pipes)
    public final int LEVEL1GAP = PIPE_WIDTH * 5; 
}
