package core.gamestates;

import core.GameState;
import core.GameStateManager;
import graphics.Screen;
import graphics.Sprite;
import graphics.Spritesheet;
import input.Keyboard;
import input.RankingReader;

import java.awt.event.KeyEvent;

/**
 * Klasa odpowiedzialna za dzialania dostrzegane po kliknieciu w przycisk TABLICA WYNIKOW w menu glownym
 */
public class GS_Rank extends GameState {
    private static final Sprite s_punktacja =new Sprite(0,0, Spritesheet.background.WIDTH,Spritesheet.background.HEIGHT,Spritesheet.punktacja);
    public static final Sprite s_powrot = new Sprite( 8*16,3*16,8*16,2*16, Spritesheet.def1);
    private static final Sprite s_POINTER =new Sprite(2*16,14*16,32,Spritesheet.def1);

    /**
     * Konstruktor klasy GS_Rank wczytujacy wyniki z pliku
     */
    public GS_Rank() {

       RankingReader rankingReader = new RankingReader("wynik.txt");
       rankingReader.show();

    }

    /**
     * Funkcja pozwalajaca na powrot do menu
     */
    public void update(){
        if(Keyboard.getKeyOnce(KeyEvent.VK_ENTER)) {
            GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_MENU,0);
        }

    }

    /**
     * Funckja odpowedzialna za wysiwetlanie grafiki z zasadami punktacji
     * @param s zmienna dotyczaca obsługiwanego screena
     */
    public void render(Screen s){
        s.renderSprite(0,0,s_punktacja);
        s.renderSprite(11*16, 265,s_POINTER);
        s.renderSprite(13*16,265,s_powrot);
    }



}
