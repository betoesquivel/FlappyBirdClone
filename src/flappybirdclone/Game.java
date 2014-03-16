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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeMap;
import javax.swing.JOptionPane;

/**
 * El applet AppletAnimacion muestra una animación en pantalla.
 */
public class Game extends JFrame implements Constants, Runnable, KeyListener, MouseListener {

    private Image dbImage;    // Imagen a proyectar
    private Graphics dbg;	// Objeto grafico
    private Image background;   //Background image
    private LinkedList<Pipes> lista;   //List for pipes
    private Pipes pipe;     //Objeto Pipes

    private URL backgroundDay = this.getClass().getResource(IMG_BACKGROUNDDAY);
    private URL backgorundNight = this.getClass().getResource(IMG_BACKGROUNDNIGHT);

    //floor
    private ImageIcon floor; //floor image
    private URL floorURL = this.getClass().getResource(IMG_FLOOR);
    private int floorPos; //contains the x position of the floor

    //pause
    private ImageIcon pause; //pause image
    private ImageIcon play; //play image
    private URL pauseURL = this.getClass().getResource(IMG_PLAYBUTTON);
    private URL playURL = this.getClass().getResource(IMG_PAUSEBUTTON);
    private int playCounter; //counter of cycles the play image stays on after pressing space

    //Variables de control de tiempo de la animación
    private long tiempoActual;
    private long tiempoInicial;
    private double timer;

    boolean pausado;
    boolean crashed;
    boolean crashAnimation;
    boolean scored; //to control the reproduction of soundClip when earning points
    private int score;

    //characters
    private Bird flappy;

    //Bird URLs
    private URL greenBird1 = this.getClass().getResource(IMG_GREENBIRDMID);
    private URL greenBird2 = this.getClass().getResource(IMG_GREENBIRDHIGH);
    private URL greenBird3 = this.getClass().getResource(IMG_GREENBIRDLOW);
    private URL blueBird1 = this.getClass().getResource(IMG_BLUEBIRDMID);
    private URL blueBird2 = this.getClass().getResource(IMG_BLUEBIRDHIGH);
    private URL blueBird3 = this.getClass().getResource(IMG_BLUEBIRDLOW);

    //Sound
    SoundClip flapClip;
    SoundClip failClip;
    SoundClip pointClip;

    //player name
    private String playerName;

    /**
     * Constructor from the Game class that calls init and start methods.
     */
    public Game() {
        init();
        start();
    }

    public void init() {
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        resetPipes();

        pausado = true;
        crashed = false;
        crashAnimation = false;
        scored = false;
        floorPos = 0;
        playCounter = 0;

        //sound effects
        flapClip = new SoundClip(SND_FLAP);
        failClip = new SoundClip(SND_FAIL);
        pointClip = new SoundClip(SND_POINT);

        //create bird onscreen default pos
        flappy = createBird(BIRD_DEFAULTX, BIRD_DEFAULTY);

        background = Toolkit.getDefaultToolkit().getImage(backgroundDay);
        floor = new ImageIcon(Toolkit.getDefaultToolkit().getImage(floorURL));
        pause = new ImageIcon(Toolkit.getDefaultToolkit().getImage(pauseURL));
        play = new ImageIcon(Toolkit.getDefaultToolkit().getImage(playURL));

        addKeyListener(this);
        //Get the player's name
        playerName = JOptionPane.showInputDialog(null, "Please enter your name:", "Brick Breaker, Version 1.2", JOptionPane.QUESTION_MESSAGE);
        if (playerName == null) {
            System.exit(0);
        }
//        if (playerName.toUpperCase().equals("TY") || playerName.toUpperCase().equals("TYKELLEY") || playerName.toUpperCase().equals("TYLUCAS") || playerName.toUpperCase().equals("TYLUCASKELLEY") || playerName.toUpperCase().equals("TY-LUCAS") || playerName.toUpperCase().equals("TY-LUCAS KELLEY") || playerName.toUpperCase().equals("TY KELLEY")) {
//            score += 1000;
//            JOptionPane.showMessageDialog(null, "You unlocked the secret 1,000 point bonus! Nice name choice by the way.", "1,000 Points", JOptionPane.INFORMATION_MESSAGE);
//        }
//
//        //Start Screen that displays information and asks if the user wants music or not, stores that choice
//        String[] options = {"Yes", "No"};
//        withSound = JOptionPane.showOptionDialog(null, "Brick Breaker, Version 1.2\nTy-Lucas Kelley\nVisit www.tylucaskelley.com for more projects.\n\nControls\n    Spacebar: Start game, Pause/Resume while in game.\n    Left/Right arrow keys: Move paddle\nItems\n    Green Item: Expand paddle\n    Red Item: Shrink paddle\nScoring\n    Block: 50 points\n    Level-up: 100 points\n    Life Loss: -100 points\n\n\n     Do you want background music?", "About the Game", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
//        playMusic(trackList, withSound, level);
    }

    public Bird createBird(int posX, int posY) {
        Animacion flapping = new Animacion();
        flapping.sumaCuadro(Toolkit.getDefaultToolkit().getImage(greenBird1), 100);
        flapping.sumaCuadro(Toolkit.getDefaultToolkit().getImage(greenBird2), 100);
        flapping.sumaCuadro(Toolkit.getDefaultToolkit().getImage(greenBird1), 100);
        flapping.sumaCuadro(Toolkit.getDefaultToolkit().getImage(greenBird3), 100);

        Bird newBird = new Bird(posX, posY, flapping);

        return newBird;
    }

    public void pipeReset(int numPipe) {
        pipe = (Pipes) (lista.get(numPipe));
        pipe.setPosX(pipe.getPosX() + RETURN_RIGHT);
        int y = (-1) * ((int) (Math.random() * 218) + 125);
        pipe.setPosY(y);
    }

    public void resetPipes() {

        lista = new LinkedList();
        int contPipes = 0;
        int gapX = 0;
        while (contPipes < TOTAL_PIPES) {
            //URL rURL = this.getClass().getResource("imagenesMalo/perro1.gif");
            pipe = new Pipes(gapX, 0);
            pipe.setGap(GAP_Y_LVL_1);
            lista.push(pipe);
            contPipes++;
            gapX += GAP_X_LVL_1;
        }
    }

    public void start() {
        //Crea el thread
        Thread hilo = new Thread(this);
        //Inicializa el thread
        hilo.start();
    }

    public void stop() {

    }

    public void destroy() {

    }

    public void run() {
        //Guarda el tiempo actual del sistema
        tiempoActual = System.currentTimeMillis();

        //Ciclo principal del Applet. Actualiza y despliega en pantalla la animación hasta que el Applet sea cerrado
        while (true) {
            if (!pausado) {
                //Updates characters
                update();

                //Manda a llamar checa colision
                checkCollision();
            } else {
                updateJustAnimation();
            }
            //Manda a llamar al método paint() para mostrar en pantalla la animación
            repaint();

            //Hace una pausa de 100 milisegundos
            try {
                Thread.sleep(65);
            } catch (InterruptedException ex) {
            }
        }
    }

    //updates position of all elements
    public void update() {
        //Determina el tiempo que ha transcurrido desde que el Applet inicio su ejecución
        long tiempoTranscurrido
                = System.currentTimeMillis() - tiempoActual;

        //Guarda el tiempo actual
        tiempoActual += tiempoTranscurrido;
        if (!crashAnimation) {
            for (int i = 0; i < lista.size(); i++) {
                pipe = (Pipes) (lista.get(i));

                pipe.move();
            }
        }

        timer += 1;
        flappy.move(timer);
        flappy.updateAnimation(tiempoTranscurrido);

        if (!crashAnimation) {
            if (floorPos <= floor.getIconWidth() * -1) {
                floorPos = 0;
            } else {
                floorPos -= GAME_SPEED;
            }
        }

        for (Pipes pipe : lista) {
            if (score > 5) {
                pipe.setGap(GAP_Y_LVL_2);
            } else if (score > 10) {
                pipe.setGap(GAP_Y_LVL_3);
            }
        }
        try {
            Thread.sleep(0);
        } catch (InterruptedException ex) {
        }
    }

    //update animation on pause
    public void updateJustAnimation() {
        //Determina el tiempo que ha transcurrido desde que el Applet inicio su ejecución
        long tiempoTranscurrido
                = System.currentTimeMillis() - tiempoActual;

        //Guarda el tiempo actual
        tiempoActual += tiempoTranscurrido;
        flappy.updateAnimation(tiempoTranscurrido);

        if (floorPos <= floor.getIconWidth() * -1) {
            floorPos = 0;
        } else {
            floorPos -= GAME_SPEED;
        }
        try {
            Thread.sleep(0);
        } catch (InterruptedException ex) {
        }
    }

    public void checkCollision() {
        if (flappy.getPosY() >= WINDOW_HEIGHT - floor.getIconHeight() && !crashAnimation) {
            crashed = true;
            crashAnimation = true;
            failClip.play();
        }

        if (!crashAnimation) {
            for (Pipes pipe : lista) {
                if (pipe.checkPipeCollision(flappy)) {
                    crashed = true;
                    crashAnimation = true;
                    failClip.play();
                }

                //para el score
                //entro al pipe
                if (pipe.getPosX() + PIPE_WIDTH < flappy.getPosX() && !pipe.isPassed()) {
                    score += 1;
                    pipe.setPassed(true);
                    pointClip.play();
                }

                //for the resetting of pipes when reaching the end of the applet
                if (pipe.getPosX() + PIPE_WIDTH < 0) {
                    Pipes lastPipe = pipe;
                    int gapX = 0;
                    //get the last pipe to be drawn
                    for (Pipes nextPipe : lista) {
                        if (lastPipe.getPosX() < nextPipe.getPosX()) {
                            lastPipe = nextPipe;
                        }
                        gapX += GAP_X_LVL_1;
                    }
                    //I have the lastPipe drawn
                    pipe.resetThisPipe(lastPipe, gapX);
                }

            }

        }

        for (int i = 0; i < lista.size(); i++) {

        }

        if (crashAnimation && flappy.getPosY() >= WINDOW_HEIGHT) {
            pausado = true;
            flappy.resetPosition();
            resetPipes();
            score = 0;
        }

    }

    /**
     * Metodo <I>update</I> sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo lo que hace es actualizar el contenedor
     *
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint(Graphics g) {
        // Inicializan el DoubleBuffer
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        // Actualiza la imagen de fondo.
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
        dbg.drawImage(background, 0, 0, this);

        for (int i = 0; i < lista.size(); i++) {
            pipe = (Pipes) (lista.get(i));

            dbg.drawImage(pipe.getPipeUp(), pipe.getPosX(), pipe.getPosY(), this);
            dbg.drawImage(pipe.getPipeDown(), pipe.getPosX(), pipe.getPosY() + pipe.getGap(), this);
        }

        if (crashAnimation) {
            dbg.setColor(Color.BLACK);
            dbg.fillRect(0, 0, getWidth(), getHeight());
            dbg.setColor(Color.WHITE);
            dbg.drawString("Name: " + playerName + ", Score: " + score, getWidth() / 5, 20);
            dbg.drawString("Game Over! Did you make it onto the high score table?", getWidth() / 5, 50);
            try {
                printScores(dbg);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            dbg.drawString("Press the Spacebar twice to play again.", getWidth() / 5, getHeight() - 20);
        }

        // Actualiza el Foreground.
        dbg.setColor(getForeground());
        paintFront(dbg);

        // Dibuja la imagen actualizada
        g.drawImage(dbImage, 0, 0, this);

        paintFront(g);
    }

    /**
     * El método paint() muestra en pantalla la animación
     */
    public void paintFront(Graphics g) {
        // Muestra en pantalla el cuadro actual de la animación
        if (flappy != null) {

            if (pausado) {
                g.drawImage(pause.getImage(), WINDOW_WIDTH / 2 - pause.getIconWidth() / 2, WINDOW_HEIGHT / 2 - pause.getIconHeight() / 2, this);
            }
            if (playCounter > 0) {
                playCounter--;
                g.drawImage(play.getImage(), WINDOW_WIDTH / 2 - pause.getIconWidth() / 2, WINDOW_HEIGHT / 2 - pause.getIconHeight() / 2, this);
            }
            //draw bird
            g.drawImage(flappy.getImage(), flappy.getPosX(), flappy.getPosY(), this);

//            for (int i = 0; i < lista.size(); i++) {
//                pipe = (Pipes) (lista.get(i));
//
//                g.drawImage(pipe.getPipeUp(), pipe.getPosX(), pipe.getPosY(), this);
//                g.drawImage(pipe.getPipeDown(), pipe.getPosX(), pipe.getPosY() + pipe.getGap(), this);
//            }
            //draw floor
            g.drawImage(floor.getImage(), floorPos, WINDOW_HEIGHT - floor.getIconHeight(), this);
            g.drawImage(floor.getImage(), floor.getIconWidth() + floorPos, WINDOW_HEIGHT - floor.getIconHeight(), this);
            if (crashed) {
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                crashed = false;
            }

            //draw score
//            Font dataFont = g.getFont();
            g.drawString("Score: " + score, 40, 50);
            g.drawString("Name: " + playerName, 40, 65);

        } else {
            g.drawString("Cargando...", getWidth() / 2, getHeight() / 2);
        }

    }

    //KEYBOARD CONTROLS
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!crashAnimation) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                flappy.flap();
                flapClip.play();
                timer = 0;
                if (pausado) {
                    playCounter = 5;
                }
                pausado = false;
            } else if (e.getKeyCode() == KeyEvent.VK_P) {
                pausado = !pausado;
            }
        }else {
            if (e.getKeyCode() == KeyEvent.VK_SPACE){
                crashAnimation = false; 
            }
        }

    }

    //Makes sure the HighScores.txt file exists
    public void makeTable() throws IOException {
        String filename = "HighScores";
        File f = new File(filename + ".txt");
        if (f.createNewFile()) {
            try {
                writeFakeScores();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            //do nothing
        }
    }

    //if there was no previous high score table, this one inputs 10 fake players and scores to fill it
    public void writeFakeScores() throws IOException {
        Random rand = new Random();

        int numLines = 10;
        File f = new File("HighScores.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile()));
        for (int i = 1; i <= numLines; i++) {
            int score = rand.nextInt(2000);
            if (numLines - i >= 1) {
                bw.write("Name: " + "Player" + i + ", " + "Score: " + score + "\n");
            } else {
                bw.write("Name: " + "Player" + i + ", " + "Score: " + score);
            }
        }
        bw.close();
        try {
            sortTable();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    //Returns the player's name and score formatted correctly
    public String playerInfo() {
        return "Name: " + playerName + ", Score: " + score;
    }

    //returns the number of lines in the high score file
    public int linesInFile(File f) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(f.getAbsoluteFile()));
        int lines = 0;
        while (br.readLine() != null) {
            lines++;
        }
        br.close();
        return lines;
    }

    //Add game to high score file by appending it and getting line number from previous method
    public void saveGame() throws IOException {
        File f = new File("HighScores.txt");
        FileWriter fw = new FileWriter(f.getAbsoluteFile(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.append("\n" + playerInfo());
        bw.close();
    }

    //sorts the high score table high to low using maps and other fun things
    public void sortTable() throws IOException {
        File f = new File("HighScores.txt");
        File temp = new File("temp.txt");
        TreeMap<Integer, ArrayList<String>> topTen = new TreeMap<Integer, ArrayList<String>>();
        BufferedReader br = new BufferedReader(new FileReader(f.getAbsoluteFile()));
        BufferedWriter bw = new BufferedWriter(new FileWriter(temp.getAbsoluteFile()));

        String line = null;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            String[] scores = line.split("Score: ");
            Integer score = Integer.valueOf(scores[1]);
            ArrayList<String> players = null;

            //make sure two players with same score are dealt with
            if ((players = topTen.get(score)) == null) {
                players = new ArrayList<String>(1);
                players.add(scores[0]);
                topTen.put(Integer.valueOf(scores[1]), players);
            } else {
                players.add(scores[0]);
            }

        }

        for (Integer score : topTen.descendingKeySet()) {
            for (String player : topTen.get(score)) {
                try {
                    bw.append(player + "Score: " + score + "\n");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        br.close();
        bw.close();
        try {
            makeNewScoreTable();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    //save the sorted table to the high score file
    public void makeNewScoreTable() throws IOException {
        File f = new File("HighScores.txt");
        File g = new File("temp.txt");
        f.delete();
        g.renameTo(f);
    }

    //Print the top 10 scores, but first excecutes all other file-related methods
    public void printScores(Graphics g) throws IOException {
        try {
            makeTable();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            saveGame();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            sortTable();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        int h = 100;
        File fileToRead = new File("HighScores.txt");
        LineNumberReader lnr = new LineNumberReader(new FileReader(fileToRead));
        String line = lnr.readLine();
        while (line != null && lnr.getLineNumber() <= 10) {
            int rank = lnr.getLineNumber();
            g.drawString(rank + ". " + line, getWidth() / 5, h);
            h += 15;
            line = lnr.readLine();
        }
        lnr.close();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
