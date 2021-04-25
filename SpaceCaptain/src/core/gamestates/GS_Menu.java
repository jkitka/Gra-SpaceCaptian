package core.gamestates;

import core.GameState;
import core.GameStateManager;
import graphics.Screen;
import graphics.Sprite;
import graphics.Spritesheet;
import input.Keyboard;

import java.awt.event.KeyEvent;

/**
 * Klasa odpowiadajaca za wszystkie dzialania przeprowadzane  w MENU gry, dziedziczy po klasie GameState
 */
public class GS_Menu extends GameState {
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad mapy w przypadku lvl1
     */
    private static final Sprite s_background =new Sprite(0,0, Spritesheet.background.WIDTH,Spritesheet.background.HEIGHT,Spritesheet.background);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad wskaznika
     */
    private static final Sprite s_POINTER =new Sprite(2*16,14*16,32,Spritesheet.def1);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad napisu NOWA GRA
     */
    private static final Sprite s_NOWA_GRA=new Sprite(0,6*16,10*16,2*16, Spritesheet.def1);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad napisu TABELA WYNIKOW
     */
    private static final Sprite s_TABELA_WYNIKOW=new Sprite(0,8*16,16*16,2*16, Spritesheet.def1);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad napisu POMOC(?)
     */
    private static final Sprite s_POMOC=new Sprite(0,10*16,9*16,2*16, Spritesheet.def1);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad napisu WYJSCIE
     */
    private static final Sprite s_WYJSCIE=new Sprite(0,12*16,8*16,2*16, Spritesheet.def1);
    private int choose= 0;
    private float pointerPosition=0;

    /**
     * Konstruktor klasy GS_MENU
     */
    public GS_Menu(){
    }

    /**
     * Funkcja odpowiadajaca za wszystkie dzialania przeprowadzane podczas trwajacego stanu gry MENU
     */
    public void update(){
        if(Keyboard.getKeyOnce(KeyEvent.VK_ENTER)) {
            if (choose==0) GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_DIFFICULTYLVL,0);
            if(choose==1)GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_RANK,0);
            if(choose==2) GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_HELP,0);
            if(choose==3) GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_EXIT,0);
        }

        if(Keyboard.getKeyOnce(KeyEvent.VK_S))choose++;
        if(Keyboard.getKeyOnce(KeyEvent.VK_W))choose--;
        if(choose>3)choose=0;
        if(choose<0)choose=3;
        if(choose==0)setPointer(0);//NOWA GRA
        if(choose==1)setPointer(32);//TABELA WYNIKOW
        if(choose==2)setPointer(64);//POMOC   64
         if(choose==3)setPointer(96);//WYJSCIE 96
    }

    /**
     * Funkcja odpowiadajaca za ustawienie pointera w odpowiednim miejscu
     * @param d przechowuje wartosc pozycji pointera
     */
    private void setPointer(int d){//funkcja do płynnych przejść pointera
        pointerPosition+=(d-pointerPosition)/3;
    }

    /**
     * Funkcja odpowiadajaca za wyswietlanie poszczegolnych fragmentow Sprietsheeta na ekranie
     * @param s zmienna dotyczaca obslugiwanego screena
     */
    public void render(Screen s){
        s.renderSprite(0,0,s_background);
        s.renderSprite(0, (int) pointerPosition,s_POINTER);
        s.renderSprite(2*16,0,s_NOWA_GRA);
        s.renderSprite(2*16,30,s_TABELA_WYNIKOW);
        s.renderSprite(2*16,64,s_POMOC);
        s.renderSprite(2*16,96,s_WYJSCIE);
    }
}
