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
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.util.LinkedList;

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

    public Game() {
        init();
        start();
    }

    public void init() {
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        lista = new LinkedList();
        int contPipes = 0;
        int gapX = 0;
        score = 0;

        while (contPipes < TOTAL_PIPES) {
            //URL rURL = this.getClass().getResource("imagenesMalo/perro1.gif");
            pipe = new Pipes(gapX, 0);
            pipe.setGap(GAP_Y_LVL_1);
            lista.push(pipe);
            contPipes++;
            gapX += GAP_X_LVL_2;
        }

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

    public Bird createBird(int posX, int posY) {
        Animacion flapping = new Animacion();
        flapping.sumaCuadro(Toolkit.getDefaultToolkit().getImage(greenBird1), 100);
        flapping.sumaCuadro(Toolkit.getDefaultToolkit().getImage(greenBird2), 100);
        flapping.sumaCuadro(Toolkit.getDefaultToolkit().getImage(greenBird1), 100);
        flapping.sumaCuadro(Toolkit.getDefaultToolkit().getImage(greenBird3), 100);

        Bird newBird = new Bird(posX, posY, flapping);

        return newBird;
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
                Thread.sleep(100);
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

        for (int i = 0; i < lista.size(); i++) {
            pipe = (Pipes) (lista.get(i));

            pipe.move();
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

//        for (Pipes pipe : lista) {
//            if (score > 5) {
//                pipe.setGap(GAP_Y_LVL_2);
//            } else if (score > 10) {
//                pipe.setGap(GAP_Y_LVL_3);
//            }
//        }

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

            }

        }

        if (crashAnimation && flappy.getPosY() >= WINDOW_HEIGHT) {
            crashAnimation = false;
            pausado = true;
            flappy.resetPosition();
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

            for (int i = 0; i < lista.size(); i++) {
                pipe = (Pipes) (lista.get(i));

                g.drawImage(pipe.getPipeUp(), pipe.getPosX(), pipe.getPosY(), this);
                g.drawImage(pipe.getPipeDown(), pipe.getPosX(), pipe.getPosY() + pipe.getGap(), this);
            }

            //draw floor
            g.drawImage(floor.getImage(), floorPos, WINDOW_HEIGHT - floor.getIconHeight(), this);
            g.drawImage(floor.getImage(), floor.getIconWidth() + floorPos, WINDOW_HEIGHT - floor.getIconHeight(), this);
            if (crashed) {
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                crashed = false;
            }

            //draw score
            g.drawString("Score: " + score, 40, 50);
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
        }

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
