package game_world;

import graphics.Screen;
import graphics.Sprite;
import graphics.Spritesheet;
import input.GameConfig;
import math.Vec2;

/**
 * Klasa sluząca do tworzenia oraz obslugi rakiety
 */
public class Rocket {

    public Vec2 pos;
    public Vec2 vel; //velocity- przyśpiesznie
    public int HP;
    public Sprite anim_now;
    public Sprite anim;

    /**
     * Konstruktor klasy Rocket
     * @param pos wektor poczatkowej pozycji rakiety wyswietlanej na ekranie
     * @param anim_now przekazujemy aktualnie wykonywana animacje rakiety
     */
    public Rocket(Vec2 pos, Sprite anim_now){
        this.pos=pos;
        vel= new Vec2(GameConfig.getVelx(), GameConfig.getVely());
        this.anim_now=anim_now;
        anim=new Sprite( 8*16,0,2*16,3*16, Spritesheet.def1);
    }

    /**
     * akualizajca aktualnie wyswietlanej rakiety
     * @param m podajemy Map aby odczytac grawitacje na konkretnej mapie, co wplywa na zamiane polozenia rakiety
     */
    public void update(Map m){
        pos.y+=m.gravity.y;
        if(pos.y>0)// sprawdzamy czy rakieta nie wylatuje za pixel y=0,
         pos.y+=vel.y;
        else{
            pos.y=1;
            vel.y=0;
        }
        pos.x+=vel.x;
        anim_now=anim;
    }


    /**
     * renderowanie obrazu z uwzglednieniem nowego polozenia rakiety
     * @param s podajemy obraz na ktorym na zostac wyswietlona rakieta
     */
    public void render(Screen s){
        s.renderSprite((int)pos.x, (int)pos.y, anim_now);
    }

}

