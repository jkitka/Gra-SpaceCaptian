package core.gamestates;

import core.GameState;
import core.GameStateManager;
import core.Nickname;
import graphics.Screen;
import graphics.Sprite;
import graphics.Spritesheet;
import input.Keyboard;
import input.RankingReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Klasa odpowiedzialna za dzialania wystepujace po przejsciu wszystkich poziomow zaoferowanych w grze
 */

public class GS_WIN extends GameState{
    private static final Sprite s_wygranko =new Sprite(0,0, Spritesheet.background.WIDTH,Spritesheet.background.HEIGHT,Spritesheet.wygranko);
    public static final Sprite s_powrot = new Sprite( 8*16,3*16,8*16,2*16, Spritesheet.def1);
    private static final Sprite s_POINTER =new Sprite(2*16,14*16,32,Spritesheet.def1);

    /**
     *  Konstruktor klasy GS_WIN pozwalajacy na wprowadzenie Nickname'u oraz wyswietlajacy wynik po przejsciu wszystkich poziomw gry
     * @param punkty parametr przekazujacy punkty uzyskane przez gracza
     */
    public GS_WIN(int punkty){
        Nickname nick = new Nickname(punkty);
        //nick.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nick.setLocationRelativeTo(null);
        nick.setResizable(true);
        nick.setVisible(true);
       // RankingReader rankingReader= new RankingReader("wynik.txt");
       // rankingReader.show();
       // System.out.println(punkty);


    }

    /**
     * Funkcja wyswietlajaca Sprite'a z instrukcjami po zakonczeniu wszystkich przygotowanych pozimow gry oraz pozwalajaca na powrot do menu
     */
    public void update(){
        if(Keyboard.getKeyOnce(KeyEvent.VK_ENTER)) {
            GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_MENU,0);
        }

    }

    /**
     * Funkcja odpowiadajaca za wyswietlenie grafiki z informacjami
     * @param s zmienna dotyczaca obsługiwanego screena
     */
    public void render(Screen s){
        s.renderSprite(0,0,s_wygranko);
        s.renderSprite(11*16, 265,s_POINTER);
        s.renderSprite(13*16,265,s_powrot);
        }




}




