package game_world;

import graphics.Screen;
import graphics.Sprite;
import graphics.Spritesheet;
import input.GameConfig;
import math.Vec2;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Klasa sluzaca do tworzenia oraz obslugi asteroid
 */
public class Asteroid {

    public Vec2 pos;
    public Vec2 vel; //velocity- przyśpiesznie
    public Sprite anim;

    private int asteroid_color0= GameConfig.getAsteroid_color0();
    private int asteroid_color1= GameConfig.getAsteroid_color1();
    private int asteroid_color2= GameConfig.getAsteroid_color2();


    static int randomBeetwen(int start, int end){
        Random random=new Random();
        int a1=random.nextInt(end-start);
        int a2=a1+start;
        return a2;
    }

    public static final Sprite s_asteroid_left = new Sprite( 5*16,16,16,16, Spritesheet.def1);
    public static final Sprite s_asteroid_right = new Sprite( 6*16,16,16,16, Spritesheet.def1);
    public static final Sprite s_boom_mini = new Sprite( 4*16,16,16,16, Spritesheet.def1);
    Random random=new Random();
    int left_right=random.nextInt(2);
    int velocity_random=random.nextInt(15);
    float velocity_x= (float) (0.2+(velocity_random/10));
    /**
     * Konstruktor klasy Asteroid
     * Pos to wektor poczatkowej pozycji rakiety wyswietlanej na ekranie
     */
    public Asteroid(){
        if(left_right==1) {
            pos = new Vec2(randomBeetwen(30,150), 17);
            vel = new Vec2(velocity_x, GameConfig.getVely());
            anim = s_asteroid_right;
        }else{
            pos = new Vec2(randomBeetwen(400, 550), 13);
            vel = new Vec2(-velocity_x, GameConfig.getVely());
            anim = s_asteroid_left;
        }
    }

    int all_asteroid_width=11, all_asteroid_lenght=12;
    int asteroid_width=3, asteroid_lenght=7;
    private int background_color= GameConfig.getBackground_color();

    /**
     * Sprawdzanie czy nie doszlo do kolizji asteroidy z planeta
     * @param imag pozwala odczytac obecny wyglad gry, polozenie planety, statku, pozostalych asteroid
     * @return true gdy dochodzi do kolizji
     */
    public boolean colider_color(BufferedImage imag){
        if(pos.y>17)
            for(asteroid_width=5; asteroid_width<all_asteroid_width; asteroid_width++ )
                for(asteroid_lenght=9 ; asteroid_lenght<all_asteroid_lenght; asteroid_lenght++ )
                    if(!(background_color == imag.getRGB((int) pos.x + asteroid_width, (int) pos.y + asteroid_lenght)))
                    if(!(asteroid_color0 == imag.getRGB((int) pos.x + asteroid_width, (int) pos.y + asteroid_lenght)))
                        if(!(asteroid_color1 == imag.getRGB((int) pos.x + asteroid_width, (int) pos.y + asteroid_lenght)))
                            if(!(asteroid_color2 == imag.getRGB((int) pos.x + asteroid_width, (int) pos.y + asteroid_lenght)))
                                return true;


        return false;// 0,2 sub string
    }


    /**
     * akualizajca aktualnie wyswietlanej asteroidy
     * @param m podajemy Map aby odczytac grawitacje na konkretnej mapie, co wplywa na zamiane polozenia asteroidy
     */
    public void update(Map m){
        pos.y+=m.gravity.y;
        pos.y+=vel.y;
        pos.x+=vel.x;

    }


    boolean nie_wybuch =true;
    /**
     * renderowanie obrazu z uwzglednieniem nowego polozenia asteroidy
     * @param s podajemy obraz na ktorym na zostac wyswietlona rakieta
     */
    public void render(Screen s){

        if (colider_color(s.getImage()) || (!nie_wybuch)){
            vel.x=0;
            vel.y=0;
            s.renderSprite((int) pos.x, (int) pos.y, s_boom_mini);
            nie_wybuch =false;
        }else if(nie_wybuch) {
            s.renderSprite((int) pos.x, (int) pos.y, anim);

        }
    }

}

