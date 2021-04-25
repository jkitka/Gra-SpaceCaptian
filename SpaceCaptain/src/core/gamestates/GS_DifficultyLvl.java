package core.gamestates;

import core.GameState;
import core.GameStateManager;
import graphics.Screen;
import graphics.Sprite;
import graphics.Spritesheet;
import input.Keyboard;

import java.awt.event.KeyEvent;

/**
 * Klasa odpowiedzialnea za dostosowywanie parametrow w zaleznosci od poziomu trudnosci
 */

public class GS_DifficultyLvl extends GameState{
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad ekranu podczas wyboru poziomu trudnosci
     */
    private static final Sprite s_background =new Sprite(0,0, Spritesheet.background.WIDTH,Spritesheet.background.HEIGHT,Spritesheet.background);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad wskaznika
     */
    private static final Sprite s_POINTER =new Sprite(2*16,14*16,32,Spritesheet.def1);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad napisu LATWY
     */
    private static final Sprite s_easy =new Sprite(16,5*16,10*16,2*16, Spritesheet.def3);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad napisu SREDNI
     */
    private static final Sprite s_normal =new Sprite(16,7*16,10*16,2*16, Spritesheet.def3);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad napisu TRUDNY
     */
    private static final Sprite s_hard =new Sprite(16,9*16,9*16,2*16, Spritesheet.def3);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad napisu WYJSCIE
     */
    private static final Sprite s_WYJSCIE=new Sprite(0,12*16,8*16,2*16, Spritesheet.def1);

    private int difficulty_lvl = 0;
    private float pointerPosition=0;

    /**
     * Konstruktor klasy GS_DifficultyLvl
     */
    public GS_DifficultyLvl(int poziom, int punkty, int HP){

    }
    /**
     * Funkcja odpowiadajaca za dostosowywanie wartosci parametrow do danego poziomu trudnosci
     */
    public void update(){
        if(Keyboard.getKeyOnce(KeyEvent.VK_ENTER)) {
            if (difficulty_lvl ==0) GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_GAME,1);
            if(difficulty_lvl ==1) GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_GAME,2);
            if(difficulty_lvl ==2) GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_GAME,3);
            if(difficulty_lvl ==3) GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_MENU,0);
        }

        if(Keyboard.getKeyOnce(KeyEvent.VK_S)) difficulty_lvl++;
        if(Keyboard.getKeyOnce(KeyEvent.VK_W)) difficulty_lvl--;
        if(difficulty_lvl >3) difficulty_lvl =0;
        if(difficulty_lvl <0) difficulty_lvl =3;
        if(difficulty_lvl ==0)setPointer(0);
        if(difficulty_lvl ==1)setPointer(32);
        if(difficulty_lvl ==2)setPointer(64);
        if(difficulty_lvl ==3)setPointer(96);
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
        s.renderSprite(2*16,0, s_easy);
        s.renderSprite(2*16,32, s_normal);
        s.renderSprite(2*16,64, s_hard);
        s.renderSprite(2*16,96,s_WYJSCIE);
    }
}
