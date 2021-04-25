package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Klasa odpowiadajaca za pobieranie obrazow z pliku
 */
public class Spritesheet {

    public static final Spritesheet def1= new Spritesheet("/graphics/Spritesheet.png");
    public static final Spritesheet def2= new Spritesheet("/graphics/Spritesheet1.png");
    public static final Spritesheet def3= new Spritesheet("/graphics/Spritesheet2.png");
    public static final Spritesheet background =new Spritesheet("/graphics/background.png");
    public static final Spritesheet lvl1 =new Spritesheet("/graphics/lvl1_czarne_tlo.png");
    public static final Spritesheet lvl2 =new Spritesheet("/graphics/lvl2.png");
    public static final Spritesheet lvl3 =new Spritesheet("/graphics/lvl3.png");
    public static final Spritesheet lvl4 =new Spritesheet("/graphics/lvl4.png");
    public static final Spritesheet lvl5 =new Spritesheet("/graphics/lvl5.png");
    public static final Spritesheet lvl6 =new Spritesheet("/graphics/lvl6.png");
    public static final Spritesheet lvl7 =new Spritesheet("/graphics/lvl7.png");
    public static final Spritesheet lvl8 =new Spritesheet("/graphics/lvl8.png");
    public static final Spritesheet lvl9 =new Spritesheet("/graphics/lvl9.png");
    public static final Spritesheet lvl10 =new Spritesheet("/graphics/lvl10.png");
    public static final Spritesheet wygranko =new Spritesheet("/graphics/wygranko.jpg");
    public static final Spritesheet  punktacja = new Spritesheet("/graphics/punktacja.png");
    public static final Spritesheet backgroundHELP = new Spritesheet("/graphics/backgroundHELP.png");
    public static final Spritesheet pauza = new Spritesheet("/graphics/backgroundPAUZa.png");

    
    public int WIDTH, HEIGHT;
    public int[] pixels;

    /**
     * Konstruktor klasy Spritesheet pobierajacy nasze obrazy z pliku
     * @param path zawiera sciezke do pliku, z ktorego chcemy korzystac
     */
    public Spritesheet(String path){
        try{
            BufferedImage image = ImageIO.read(Spritesheet.class.getResource(path));
            WIDTH=image.getWidth();
            HEIGHT=image.getHeight();
            pixels= new int [WIDTH * HEIGHT];

            image.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
