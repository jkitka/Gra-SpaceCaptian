package core.gamestates;

import core.GameState;
import core.GameStateManager;
import graphics.Screen;
import graphics.Sprite;
import graphics.Spritesheet;
import input.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Klasa odpowiedzialna za stan gry po kliknieciu w przycisk POMOC(?), dziedziczy po klasie GameState
 */
public class GS_Help extends GameState{
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajacy za wygląd tla
     */
    private static final Sprite s_backgroundHELP =new Sprite(0,0, Spritesheet.background.WIDTH,Spritesheet.background.HEIGHT,Spritesheet.backgroundHELP);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad przycisku POWROT
     */
    public static final Sprite s_powrot = new Sprite( 8*16,3*16,8*16,2*16, Spritesheet.def1);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad wskaznika
     */
    private static final Sprite s_POINTER =new Sprite(2*16,14*16,32,Spritesheet.def1);
    ;

    /**
     * Konstruktor klasy GS_Help
     */
    public GS_Help(){
    }

    /**
     * Funkcja odowiadajaca za wszystkie dzialania odbywajace się po klinieciu w przycisk POMOC(?)
     */
    public void update(){
        if(Keyboard.getKeyOnce(KeyEvent.VK_ENTER)) {
            GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_MENU,0);
        }
    }

    /**
     * Funkcja odpowiadajaca za wyswietlanie poszczegolnych fragmentow Spritesheeta na ekranie
     * @param s zmienna dotyczaca obsługiwanego screena
     */
    public void render(Screen s){
        s.renderSprite(0,0,s_backgroundHELP);
        s.renderSprite(19*16, 272,s_POINTER);
        s.renderSprite(20*16,272,s_powrot); }




}



