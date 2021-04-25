package graphics;

/**
 * Klasa odpowiadajaca za wyodrebnienie poszczegolnych obiektow z pobranego wczesniej pliku
 */
public class Sprite {
    public int x,y,width, height;
    public Spritesheet sp;

    /**
     * Konstruktor klasy Sprite ustalajacy początkowe parametry obrazu
     * @param x pozycje x
     * @param y pozycje y
     * @param width szerokosc
     * @param height wysokosc
     * @param sp dany Spritesheet
     */
    public Sprite(int x, int y, int width, int height, Spritesheet sp)//obiekt leży na wsp x i y w obrazie, podajemy pobierane rozmiary obiektu
     {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.sp=sp;
    }

    /**
     * Drugi, prawie identyczny konstruktor klasy sprite, lecz pozwala na latwe pobranie kwadratowych obiektow
     * @param x pozycja x
     * @param y pozycja y
     * @param size rozmiar obrazu
     * @param sp dany Sprite
     */
    public Sprite(int x, int y, int size, Spritesheet sp)
    {
        this.x=x;
        this.y=y;
        this.width=size;
        this.height=size;
        this.sp=sp;
    }

}
