package core;

import core.gamestates.*;
import graphics.Screen;

import java.awt.image.BufferedImage;

/**
 * Klasa odpowiadajca za zmiany dokonywane pomiedzy poszegolnymi stanami gry
 */
public class GameStateManager {

    public static final int GAME_STATE_EXIT=-1;
    public static final int GAME_STATE_MENU=0;
    public static final int GAME_STATE_DIFFICULTYLVL=1;
    public static final int GAME_STATE_GAME=4;
    public static final int GAME_STATE_HELP=2;
    public static final int GAME_STATE_WIN=3;
    public static final int GAME_STATE_RANK=5;
    private static GameState gs;
    public static boolean exit=false;


    /**
     * Funkcja odpowiadająca za nadanie wartosci zmiennej imag
     * @param im przechowuje chwilowy wyglad ekranu
     */
    public void set_image(BufferedImage im){
        gs.set_Image(im);
    }

    /**
     * Funkcja odpowiadajaca za zmiane stanu gry
     * @param ID pomaga manewrowac pomiedzy stanami gry
     */
    public static void ChangeGameState(int ID, int difficulty){
        if(ID==GAME_STATE_MENU) gs=new GS_Menu();
        if(ID==GAME_STATE_DIFFICULTYLVL) gs=new GS_DifficultyLvl(1, 0, 0);//gs=new GS_Game(1, 0, 0);
        if(ID==GAME_STATE_HELP) gs=new GS_Help();
        if(ID==GAME_STATE_WIN) gs=new GS_WIN(90);
        if(ID==GAME_STATE_EXIT) exit=true;
        if(ID==GAME_STATE_GAME) gs=new GS_Game(1, 0, 0, difficulty);
        if(ID==GAME_STATE_RANK) gs=new GS_Rank();

    }

    public static void ChangeGameState(int ID, int poziom, int punkty, int HP, int difficulty){
      //  System.out.println("DIFF "+difficulty);
        if(ID==GAME_STATE_MENU) gs=new GS_Menu();
        if(ID==GAME_STATE_GAME) gs=new GS_Game(poziom, punkty, HP, difficulty);
        if(ID==GAME_STATE_HELP) gs=new GS_Help();
        if(ID==GAME_STATE_WIN) gs=new GS_WIN(punkty);
        if(ID==GAME_STATE_EXIT) exit=true;
        if(ID==GAME_STATE_RANK) gs=new GS_Rank();
    }

    /**
     * Konstruktor klasy GameStateManager
     */
    public GameStateManager(){
        ChangeGameState(GAME_STATE_MENU, 1);
    }

    /**
     * Funkcja odpowiadajcca za dzialania przeprowadzane podczas zmiany stanu gry
     */
    public void update(){
        gs.update();
    }

    /**
     * Funkcja odpowiadajaca za zmiane wygladu ekranu podczas zmiany stanu gry
     * @param s zmienna dotyczaca danego obslugiwanego Screena
     */
    public void render(Screen s){
        gs.render(s);
    }

}
