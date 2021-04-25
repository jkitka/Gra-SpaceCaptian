package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Klasa umozliwiaja    ca nam korzystanie z klawiatury
 */
public class Keyboard implements KeyListener {
    private static final int count=200;
    private static boolean keys[]=new boolean[count];// Ustawione na pricate bo jest funkcja getKey, mogło być public
    private static boolean keys_prev[]=new boolean[count];// To samo co up

    /**
     * Konstruktor klasy Keyboard
     */
    public Keyboard(){
        for(int i=0; i<count; i++) {
            keys[i] = false;
            keys_prev[i] = false;
        }
    }

    /**
     * Funkcja umozliwiajaca korzystanie z klawiatury
     */
    public void update(){
        for(int i=0; i<count; i++)
            if(!keys[i])
                keys_prev[i]=false;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()]=true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()]=false;
    }

    /**
     * Funkcja pozwalajaca nam na jednoktrotne skorzystanie z dzialania klawisza
     */
    public static boolean getKeyOnce(int Key){
        if(!keys_prev[Key] && keys[Key]){
            keys_prev[Key]=true;
            return true;
        }

        return false;
    }

    /**
     * Funkcja pozwalajaca na skorzystanie z danego klawisza
     */
    public static boolean getKey(int Key){
        return keys[Key];
    }

}
