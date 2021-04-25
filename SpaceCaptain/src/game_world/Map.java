package game_world;

import core.GameStateManager;
import graphics.Screen;
import graphics.Sprite;
import graphics.Spritesheet;
import input.GameConfig;
import math.Vec2;

/**
 * Klasa odpowiedzialna za wysweitlanie mapy, na ktorej toczy się rozgrywka
 */
public class Map {//tutaj trzeba troche pomyśleć i zrobić mapy zależne od poziomu
    /**
     * wektor okreslajacy poczatkowa wartosc grawitacji na danej mapie (lvlu)
     */
    public Vec2 gravity;
    private int poziom;
    public int landing_y;
    public int landing_x_left;
    public int landing_x_right;

    /**
     * okresla wyglad mapy lvl1
     */
    private static final Sprite s_lvl1 =new Sprite(0,0, Spritesheet.lvl1.WIDTH,Spritesheet.lvl1.HEIGHT,Spritesheet.lvl1);
    private static final Sprite s_lvl2 =new Sprite(0,0, Spritesheet.lvl2.WIDTH,Spritesheet.lvl2.HEIGHT,Spritesheet.lvl2);
    private static final Sprite s_lvl3 =new Sprite(0,0, Spritesheet.lvl3.WIDTH,Spritesheet.lvl3.HEIGHT,Spritesheet.lvl3);
    private static final Sprite s_lvl4 =new Sprite(0,0, Spritesheet.lvl4.WIDTH,Spritesheet.lvl4.HEIGHT,Spritesheet.lvl4);
    private static final Sprite s_lvl5 =new Sprite(0,0, Spritesheet.lvl5.WIDTH,Spritesheet.lvl5.HEIGHT,Spritesheet.lvl5);
    private static final Sprite s_lvl6 =new Sprite(0,0, Spritesheet.lvl6.WIDTH,Spritesheet.lvl6.HEIGHT,Spritesheet.lvl6);
    private static final Sprite s_lvl7 =new Sprite(0,0, Spritesheet.lvl7.WIDTH,Spritesheet.lvl7.HEIGHT,Spritesheet.lvl7);
    private static final Sprite s_lvl8 =new Sprite(0,0, Spritesheet.lvl8.WIDTH,Spritesheet.lvl8.HEIGHT,Spritesheet.lvl8);
    private static final Sprite s_lvl9 =new Sprite(0,0, Spritesheet.lvl9.WIDTH,Spritesheet.lvl9.HEIGHT,Spritesheet.lvl9);
    private static final Sprite s_lvl10 =new Sprite(0,0, Spritesheet.lvl10.WIDTH,Spritesheet.lvl10.HEIGHT,Spritesheet.lvl10);
    private int punkty;

    /**
     * konstruktor klasy Map
     */
    public Map(int poziom,int punkty){
        this.poziom=poziom;
        gravity=new Vec2(0.0f, 0.0f);
        this.punkty=punkty;
    }

    /**
     * Wyswietlanie odpowiedniej 'planety' oraz dobor odpowienich wspolrzednych ladowiska
     * @param s pozwala na wyswietlenie obrazu na ekranie
     */
    public void render(Screen s){

        if(poziom==1) {
            s.renderSprite(0,0,s_lvl1);
             landing_y = GameConfig.getLanding_y(0);
             landing_x_left = GameConfig.getLanding_x_left(0);
             landing_x_right = GameConfig.getLanding_x_right(0);
        }
        if(poziom==2){
            s.renderSprite(0,0,s_lvl2);
            landing_y = GameConfig.getLanding_y(1);
            landing_x_left = GameConfig.getLanding_x_left(1);
            landing_x_right = GameConfig.getLanding_x_right(1);
        }
        if(poziom==3){
            s.renderSprite(0,0,s_lvl3);
            landing_y = GameConfig.getLanding_y(2);
            landing_x_left = GameConfig.getLanding_x_left(2);
            landing_x_right = GameConfig.getLanding_x_right(2);
        }
        if(poziom==4){
            s.renderSprite(0,0,s_lvl4);
            landing_y = GameConfig.getLanding_y(3);
            landing_x_left = GameConfig.getLanding_x_left(3);
            landing_x_right = GameConfig.getLanding_x_right(3);
        }
        if(poziom==5){
            s.renderSprite(0,0,s_lvl5);
            landing_y = GameConfig.getLanding_y(4);
            landing_x_left = GameConfig.getLanding_x_left(4);
            landing_x_right = GameConfig.getLanding_x_right(4);
        }
        if(poziom==6){
            s.renderSprite(0,0,s_lvl6);
            landing_y = GameConfig.getLanding_y(5);
            landing_x_left = GameConfig.getLanding_x_left(5);
            landing_x_right = GameConfig.getLanding_x_right(5);
        }
        if(poziom==7){
            s.renderSprite(0,0,s_lvl7);
            landing_y = GameConfig.getLanding_y(6);
            landing_x_left = GameConfig.getLanding_x_left(6);
            landing_x_right = GameConfig.getLanding_x_right(6);
        }
        if(poziom==8){
            s.renderSprite(0,0,s_lvl8);
            landing_y = GameConfig.getLanding_y(7);
            landing_x_left = GameConfig.getLanding_x_left(7);
            landing_x_right = GameConfig.getLanding_x_right(7);
        }
        if(poziom==9){
            s.renderSprite(0,0,s_lvl9);
            landing_y = GameConfig.getLanding_y(8);
            landing_x_left = GameConfig.getLanding_x_left(8);
            landing_x_right = GameConfig.getLanding_x_right(8);
        }
        if(poziom==10){
            s.renderSprite(0,0,s_lvl10);
            landing_y = GameConfig.getLanding_y(9);
            landing_x_left = GameConfig.getLanding_x_left(9);
            landing_x_right = GameConfig.getLanding_x_right(9);
        }
        if(poziom==11){//hangeGameState(int ID, int poziom, int punkty, int HP, int difficulty){
              GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_WIN, 10,punkty, 1,0);


        }

    }

    public void update(){
    }


}

