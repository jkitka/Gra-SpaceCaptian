package core;

import graphics.Screen;
import input.GameConfig;
import input.GameConfig;
import input.Keyboard;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

/**
 * Glowna klasa inicjujaca
 */
public class Main extends Canvas implements Runnable {

    private int WIDTH, HEIGHT;

    public static final String TITLE="  Space Captain  ";
    public static final int FRAME_RATE=60;
    private boolean RUNNING=false;
    private JFrame frame;
    public Screen screen;
    private Keyboard keyboard = new Keyboard();
    private GameStateManager  gsm;

    /**
     * Konstruktor Klasy Main odpowiadajacy za tworzenie okna ze startowymi parametrami
     */
    public Main (){

        HEIGHT=GameConfig.getHEIGHT();
        WIDTH=GameConfig.getWIDTH();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new Keyboard());

        frame.add(this, new BorderLayout().CENTER);

        frame.pack();//To jest takie przyklepanie tych nowych ustawień okienka "zastosuj"

        frame.setLocationRelativeTo(null);//wyświetlanie okienka na środku ekaranu
        frame.setResizable(true);// rozszerzanie okienkaaaa
        frame.setVisible(true);

        int ilosc_obrazkow=35;
        screen = new Screen(16*ilosc_obrazkow,9*ilosc_obrazkow);// zapewnia obrazek 16:9, nw czy potrzebne

        gsm= new GameStateManager();

    }


    /**
     * Funkcja sprawdzajaca czy nasza gra w ogole wystartowala
     */
    private void start(){
        if(RUNNING)return;
        RUNNING=true;

        new Thread(this,"Game"+TITLE).start();//wykład o wątkach
    }

    /**
     * Funkcja odpowiadajaca za zatrzymanie dzialania naszej gry
     */
    private void stop(){
        if(!RUNNING)return;
        RUNNING=false;
        frame.dispose();
        System.exit(0);
    }


    private double timer= System.currentTimeMillis();
    private int FPS =0, UPS=0;
    private double delta;
    private double FRAME_TIME = 1000000000/FRAME_RATE;
    private long timeNOW=System.nanoTime();
    private long timeLAST=System.nanoTime();

    /**
     * Funkcja odpowiadajaca za plynne funkcjonowanie naszej gry oraz wyliczanie FPS-ow i UPS-ow podczas gdy nasza gra jest wlączona
     */
    public void run(){
        while(RUNNING && !gsm.exit ){
            timeNOW=System.nanoTime();
            delta+= (timeNOW-timeLAST)/FRAME_TIME;
            timeLAST=timeNOW;

            while (delta>=1) {
                update();
                delta -= 1;
                UPS++;
            }
            render();
            FPS++;

            if(System.currentTimeMillis()-timer>=1000){
                timer=System.currentTimeMillis();
                frame.setTitle("Space Captain"+"   FPS: " +FPS + "   UPS: "+ UPS);
                FPS=0;
                UPS=0;

            }
        }
        stop();// funckja stop jest określona powyżej
    }

    /**
     * Funkcja odpowiedzialna za przeprowadzanie wszystkich działan w funkcjach update w poszczegolnych klasach
     */
    private void update(){
        keyboard.update();
        gsm.update();
    }

    //public static BufferedImage src=new BufferedImage(Screen.WIDTH,Screen.HEIHGT, BufferedImage.TYPE_INT_RGB);

    /**
     * Funkcja odpowiedzialna za wyglad naszego okna, mozliwosc ujzenia dodanych przez nas grafik oraz zajmuje się skalowaniem okienka
     */
    private void render(){
        BufferStrategy bs =getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3);//chyba takie jakby odświeżanie, na 1 nie nadąża. 2 jest okej, 3 to już bezpiecznie
            return;
        }

        Graphics g=bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);
        
        screen.clear(0x000000);
        gsm.render(screen);

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform saveTransform = g2d.getTransform();
        AffineTransform scaleMatrix = new AffineTransform();
        float sx =(1f+(getSize().width-WIDTH)/(float)WIDTH);
        float sy =(1f+(getSize().height-HEIGHT)/(float)HEIGHT);
        scaleMatrix.scale(sx, sy);
        g2d.setTransform(scaleMatrix);
        g=g2d;

        g.drawImage(screen.getImage(), 0, 0 , WIDTH, HEIGHT, null);
        g.dispose();
        bs.show();
        gsm.set_image(screen.getImage());

        //System.out.println(screen.getImage().getRGB(10,300));// do sprawdzania jaki pixel ma kolod dla getRGB szary: -8355712
    }

    /**
     * Funkcja ustalajaca wartosc szerokosci
     * @param WIDTH zmienna przechwujaca wartosc szerokosci
     */
    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    /**
     * Funkcja ustalajaca wartosc wysokosci
     * @param HEIGHT zmienna przechowujaca wartosc szerokosci
     */
    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    /**
     * Funkcja odpowiedzialna ogolnie za dzialanie naszej gry i wszystkie dzialania przerpowadzane na jej ekranie podczas jej trwania
     */
    public static void main(String[] args) {
        GameConfig config= new GameConfig("config.txt");// wczytuje dane z config.txt
        new Main().start();

    }


}
