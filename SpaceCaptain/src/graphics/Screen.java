package graphics;

//import org.w3c.dom.css.RGBColor;
//import java.awt.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Klasa odpowiadajaca za koncowy wyglad okna
 */
public class Screen {
    public static int WIDTH;
    public static int HEIHGT;
    public BufferedImage image;
    private int[] pixels;

    /**
     * Konstruktor klasy Screen
     * @param w szerokosc okna
     * @param h wysokosc okna
     */
    public Screen(int w, int h){
        WIDTH=w;
        HEIHGT= h;
        image= new BufferedImage(WIDTH,HEIHGT, BufferedImage.TYPE_INT_RGB);
        pixels= ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    }

    /**
     * Funkcja odpowiedzialna za zabarwienie calego ekranu wybranym kolorem
     * @param color przechowuje szesnastkowa definicje uzytego koloru
     */
    public void clear(int color) {
        for(int i=0; i<WIDTH*HEIHGT; i++)
            pixels[i]=color;
    }

    /**
     * Funkcja pobierajaca wartosc zmiennej image
     * @return zwraca wartosc zmiennej image
     */
    public BufferedImage getImage(){
        return image;
    }

    /**
     * Funkcja odpowiedzialna za wyswietlenie danego Sprite'a
     * @param px dotyczy x-ego pixela na ktorym ma zostać wyswietlony nasz Spirte
     * @param py dotyczy y-ego pixela na ktorym ma zostać wyswietlony nasz Sprite
     * @param s wybiera zadeklarowany przez nas Sprite, ktory ma wyswietlic funkcja renderSprite
     */
    public void renderSprite(int px, int py, Sprite s){//wyświetla sprite, jakiś nasz obrazek
        for(int y=0; y<s.height ;y++)
            for(int x=0; x<s.width ; x++){
                pixel(px+x,py+y, s.sp.pixels[s.x+x+(s.y+y)*s.sp.WIDTH]);
            }
    }

    /**
     * Funkcja rysująca prostokąt
     * @param px pozycja gdzie ma byc narysowany nasz prostokąt (x-owa wspolrzedna)
     * @param py pozycja gdzie ma byc narysowany nasz prostokąt (y-owa wpsolrzedna)
     * @param w okresla szerokosc naszego prostokata
     * @param h okresla wysokosc naszego prostokata
     * @param color okresla kolor naszego prostokata
     */
    public void frect(int px, int py, int w, int h, int color){
        for(int y=0; y<h ;y++)
            for(int x=0; x<w ; x++)
                pixel(x + px, y + py, color);
    }

    /**
     * Funkcja pozwalajaca nam zapobiec bledom podczas wyjscia rysowanej rzeczy poza granice wyznaczonego obszaru
     * @param x okresla pozycje x
     * @param y okresla pozycje y
     * @param color wyznacza dany kolor ktory nasza gra ma pomijac   podczas renderowania danego Sprite'a
     */
    private void pixel (int x, int y, int color){//zapobiega błędom podczas wyjścia rysowanej rzeczy za granice wyznaczonego obszaru
        if(x<0 || x>=WIDTH || y<0 || y>=HEIHGT || color==0xffff00ff || color==0xff000000)// color==0xffff00ff czyści obrazek (.png) z kolory ff00ff
            return;

        pixels[x+y*WIDTH]=color;
    }



}

