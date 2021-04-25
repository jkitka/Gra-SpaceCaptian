package input;

        import java.awt.*;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.util.Scanner;

/**
 * Klasa zczytujaca konfiguracje calej naszej gry,zaczaynajac od pozycje startowe statku, pozycje ladowiska, przyspieszenie x-owe i y-owe statku itd
 */
public class GameConfig {

    private static File file;
    private static int WIDTH;
    private static int HEIGHT;
    private static int posx;
    private static int posy;
    private static float velx;
    private static float vely;
    private static int background_color;
    private static int asteroid_color0;
    private static int asteroid_color1;
    private static int asteroid_color2;
    private static Scanner scanner;

    private static int[]  iloczyn_paliwa= new int[4];
    private static int[]  asteroid_how_fast= new int[4];
    private static int[]  szansa_na_asteroide= new int[4];
    private static int[]  paliwo= new int[4];
    private static int[]  points_optimum= new int[4];
    private static int[]  rocketHP= new int[4];
    private static float max_landing_speed;

    private static int liczba_poziomow;
    private static int[] landing_x_right;
    private static int[] landing_x_left;
    private static int[] landing_y;

    /**
     * konstruktor odpowiedzialny za zczytywanie naszego pliku i parsowanie go
     * @param fileName dotyczy pliku z ktorego chcemy korzystac
     */
    public GameConfig(String fileName) {
        loadConfig(fileName);
        parseConfig();
    }

    /**
     * Funkcja wczytujaca nasz plik
     * @param fileName dotyczy pliku z ktorego chcemy korzystac
     */
    public static void loadConfig(String fileName) {

        file = new File("config.txt");

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**7
     * Funkcja odpowiedzialna za zmiane naszych danych ktore wczytalismy na odpowiednio inty, floaty, booleany itd.
     */
    public static void parseConfig () {
        String temp = scanner.nextLine();
        liczba_poziomow = Integer.parseInt(temp);
        landing_x_left=new int [liczba_poziomow];
        landing_x_right=new int [liczba_poziomow];
        landing_y=new int [liczba_poziomow];
        temp = scanner.nextLine();
        WIDTH = Integer.parseInt(temp);
        temp = scanner.nextLine();
        HEIGHT = Integer.parseInt(temp);
        temp = scanner.nextLine();
        posx = Integer.parseInt(temp);
        temp = scanner.nextLine();
        posy = Integer.parseInt(temp);
        temp = scanner.nextLine();
        velx = Float.parseFloat(temp);
        temp = scanner.nextLine();
        vely = Float.parseFloat(temp);
        temp = scanner.nextLine();
        for(liczba_poziomow=0; liczba_poziomow<10; liczba_poziomow++) {
            landing_y[liczba_poziomow] = Integer.parseInt(temp);
            temp = scanner.nextLine();
            landing_x_left[liczba_poziomow] = Integer.parseInt(temp);
            temp = scanner.nextLine();
            landing_x_right[liczba_poziomow] = Integer.parseInt(temp);
            temp = scanner.nextLine();
        }

        background_color=(int) Long.parseLong(temp,16);
        temp = scanner.nextLine();
        asteroid_color0=(int) Long.parseLong(temp,16);
        temp = scanner.nextLine();
        asteroid_color1=(int) Long.parseLong(temp,16);
        temp = scanner.nextLine();
        asteroid_color2=(int) Long.parseLong(temp,16);
        temp = scanner.nextLine();
        for(int poziom_trudnosci=1; poziom_trudnosci<4;poziom_trudnosci++) {
            iloczyn_paliwa[poziom_trudnosci] = Integer.parseInt(temp);
            temp = scanner.nextLine();
            asteroid_how_fast[poziom_trudnosci] = Integer.parseInt(temp);
            temp = scanner.nextLine();
            szansa_na_asteroide[poziom_trudnosci] = Integer.parseInt(temp);
            temp = scanner.nextLine();
            paliwo[poziom_trudnosci] = Integer.parseInt(temp);
            temp = scanner.nextLine();
            points_optimum[poziom_trudnosci] = Integer.parseInt(temp);
            temp = scanner.nextLine();
            rocketHP[poziom_trudnosci] = Integer.parseInt(temp);
            temp = scanner.nextLine();
        }
        max_landing_speed=Float.parseFloat(temp);
        System.out.println(WIDTH + "  "+ HEIGHT+ "  "+ posx+ "  "+ posy+ "  " + velx+ "  " + vely+ "  "+ landing_y[0] + "  " +landing_x_left[0]+ "  "+ landing_x_right[0]+ "  "+  landing_y[1] + "  " +landing_x_left[1]+ "  "+ landing_x_right[1] +"  "+  landing_y[2] + "  " +landing_x_left[2]+ "  "+ landing_x_right[2]+ "  "+  landing_y[3] + "  " +landing_x_left[3]+ "  "+ landing_x_right[3] + "  "+  landing_y[4] + "  " +landing_x_left[4]+ "  "+ landing_x_right[4] + "  "+  landing_y[5] + "  " +landing_x_left[5]+ "  "+ landing_x_right[5] + "  "+  landing_y[6] + "  " +landing_x_left[6]+ "  "+ landing_x_right[6] + "  "+  landing_y[7] + "  " +landing_x_left[7]+ "  "+ landing_x_right[7] + "  "+  landing_y[8] + "  " +landing_x_left[8]+ "  "+ landing_x_right[8] + "  "+  landing_y[9] + "  " +landing_x_left[9]+ "  "+ landing_x_right[9] + "  " + Integer.toHexString(background_color)+"  " + Integer.toHexString(asteroid_color0)+"  " + Integer.toHexString(asteroid_color1)+"  " + Integer.toHexString(asteroid_color2));

    }

    public static int getWIDTH() {//te 1111 żeby odróżnić od innych geterów
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getPosx(){ return posx; }

    public static int getPosy(){ return posy;}

    public static float getVelx(){ return velx;}

    public static float getVely(){ return vely;}

    public static int getLanding_y(int y) { return landing_y[y]; }

    public static int getLanding_x_left(int xl) { return landing_x_left[xl]; }

    public static int getLanding_x_right(int xp) { return landing_x_right[xp]; }

    public static int getBackground_color() { return background_color; }

    public static int getAsteroid_color0() { return asteroid_color0;}

    public static int getAsteroid_color1() {
        return asteroid_color1;
    }

    public static int getAsteroid_color2() {
        return asteroid_color2;
    }

    public static int getIloczyn_paliwa(int x){
        return iloczyn_paliwa[x];
    }

    public static int getRocketHP(int x) {
        return rocketHP[x];
    }

    public static int getSzansa_na_asteroide(int x) {
        return szansa_na_asteroide[x];
    }

    public static int getPaliwo(int x) {
        return paliwo[x];
    }

    public static int getPoints_optimum(int x) {
        return points_optimum[x];
    }

    public static int getAsteroid_how_fast(int x) {
        return asteroid_how_fast[x];
    }

    public static float getMax_landing_speed() {
        return max_landing_speed;
    }

}