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
    //images Bird
    public final String IMG_BLUEBIRDMID = "flappybirdclone/images/blueBirdMid.png";
    public final String IMG_BLUEBIRDHIGH = "flappybirdclone/images/blueBirdHigh.png";
    public final String IMG_BLUEBIRDLOW = "flappybirdclone/images/blueBirdLow.png";
    public final String IMG_GREENBIRDMID = "flappybirdclone/images/blueBirdMid.png";
    public final String IMG_GREENBIRDHIGH = "flappybirdclone/images/blueBirdHigh.png";
    public final String IMG_GREENBIRDLOW = "flappybirdclone/images/blueBirdLow.png";
    
    //images Background
    public final String IMG_BACKGROUNDNIGHT = "flappybirdclone/images/backgroundNight.png";
    public final String IMG_BACKGROUNDDAY = "flappybirdclone/images/backgroundDay.png";
    public final String IMG_FLOOR = "flappybirdclone/images/backgroundDay.png";
    
    //images Pipes
    public final String IMG_LOWERGREENPIPE = "flappybirdclone/images/lowerGreenPipe.png";
    public final String IMG_UPPERGREENPIPE = "flappybirdclone/images/upperGreenPipe.png";
    public final String IMG_LOWERREDPIPE = "flappybirdclone/images/lowerRedPipe.png";
    public final String IMG_UPPERREDPIPE = "flappybirdclone/images/upperRedPipe.png";
    
    //images buttons and ready sign
    public final String IMG_READY = "flappybirdclone/images/ready.png";
    public final String IMG_PLAYBUTTON = "flappybirdclone/images/playButton.png";
    public final String IMG_PAUSEBUTTON = "flappybirdclone/images/pauseButton.png";
    
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
    public final int TOTAL_PIPES = 10;
    
    //Gaps en eje Y between pipes 
    public final int GAP_Y_LVL_1 = 660;
    public final int GAP_Y_LVL_2 = 624;
    public final int GAP_Y_LVL_3 = 588;
    
    //Gaps en eje X between pipes 
    public final int GAP_X_LVL_1 = 280;
    public final int GAP_X_LVL_2 = 230;
    public final int GAP_X_LVL_3 = 180;
}
