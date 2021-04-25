package core.gamestates;

import core.GameState;
import core.GameStateManager;
import core.Nickname;
import game_world.Asteroid;
import game_world.Map;
import game_world.Rocket;
import graphics.Screen;
import graphics.Sprite;
import graphics.Spritesheet;
import input.GameConfig;
import input.Keyboard;
import math.Vec2;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Klasa odpowiadajaca za wszystkie dzialania po kliknieciu w przycisk NOWA GRA, dziedziczy po klasie GAMESTATE
 */
public class GS_Game extends GameState {
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad rakiety
     */
    public static final Sprite s_rocket = new Sprite( 14*16,0,2*16,3*16, Spritesheet.def1);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad rakiety lecacej w gorę
     */
    public static final Sprite s_rocket_up = new Sprite( 8*16,0,2*16,3*16, Spritesheet.def1);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad rakiety lecacej w lewo
     */
    public static final Sprite s_rocket_left = new Sprite( 10*16,0,2*16,3*16, Spritesheet.def1);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajaca za wyglad rakiety lecacej w prawo
     */
    public static final Sprite s_rocket_right = new Sprite( 12*16,0,2*16,3*16, Spritesheet.def1);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajacy za animacje wybuchu rakiety w przypadku rozbicia jej o skaly
     */
    public static final Sprite s_boom = new Sprite( 0,16,3*16,3*16, Spritesheet.def1);
    /**
     * stala zawierajaca fragment Spritesheeta odpowiadajacy za wyglad asteroidy w grze
     */

    private static final Sprite przegrales = new Sprite(0, 0,  16*16, 3*16, Spritesheet.def2);
    private static final Sprite pauza =new Sprite(0,0, 158 ,90,Spritesheet.pauza);
    public static final Sprite heart = new Sprite( 7*16,0,16,16, Spritesheet.def1);

    /**
     * Tworzenie obiektu Mapa
     */
    public Map mapa;
    /**
     * Tworzenie obiektu rakiety ze startowymi parametrami dotyczacymi poczatkowych koordynatow rakiety
     */
    public Rocket rocket = new Rocket(new Vec2((float) GameConfig.getPosx(),(float) GameConfig.getPosy() ), s_rocket);

    /**
     * Kolor tła pobierany z pliku
     */
    private int background_color= GameConfig.getBackground_color();
    /**
     * Jeden z kolorów asteroidy (nie jest barany pod uwagę podczas kolizji z rakietą), pobierany z pliku
     */
    private int asteroid_color0= GameConfig.getAsteroid_color0();
    /**
     * Jeden z kolorów asteroidy (nie jest barany pod uwagę podczas kolizji z rakietą), pobierany z pliku
     */
    private int asteroid_color1= GameConfig.getAsteroid_color1();
    /**
     * Jeden z kolorów asteroidy (barany pod uwagę podczas kolizji z rakietą), pobierany z pliku
     */
    private int asteroid_color2= GameConfig.getAsteroid_color2();

    String asteroid_color0_string= Integer.toHexString(asteroid_color0).substring(2);
    String asteroid_color1_string= Integer.toHexString(asteroid_color1).substring(2);
    String asteroid_color2_string= Integer.toHexString(asteroid_color2).substring(2);

    private int poziom;
    private int punkty;
    private int difficulty;
    private int iloczyn_paliwa;
    private int asteroid_how_fast;
    private int szansa_na_asteroide;//mnieszsza wartość tutaj, większa szansa na asterioide //trzeba ustalić zależnie od poziom trudności
    private int paliwo;
    /**
     * Zmienna pozwalająca na manipulowanie zdobytymi punktami zależnie od poziomu trudnosci
     */
    private int points_optimum;
    private float max_landing_speed;

    /**
     *Konstruktor klasy GS_Game
     * @param poziom identyfikacja, na ktorym poziomie jest gracz
     * @param punkty zdobyte punkty
     * @param HP_przekazane ilosc pozostalych zyc
     * @param difficulty poziom trudnosci wybrany przez gracza na poczatku
     */
    public GS_Game(int poziom, int punkty, int HP_przekazane, int difficulty) {
        this.difficulty=difficulty;
        this.poziom=poziom;
        this.punkty=punkty;
        if(poziom!=1)
            rocket.HP=HP_przekazane;

        mapa = new Map(poziom, punkty);
        max_landing_speed= GameConfig.getMax_landing_speed();

        choosen_difficulty(difficulty);
    }


    /**
     * zmienne okreslajace przyspieszenie rakiety w kierunku x i y
     */
    float Y_vel= rocket.vel.y, X_vel= rocket.vel.x;

    boolean czy_pauza=false;
    /**
     * Tworzenie obiektu klasy BufferImage z danymi parametrami
     */
    public static BufferedImage imag=new BufferedImage(Screen.WIDTH,Screen.HEIHGT, BufferedImage.TYPE_INT_RGB);

    /**
     * Funkcja odpowiadajaca za nadanie wartosci zmiennej imag
     * @param im  przechowujaca chwilowy obraz ekranu
     */
    public void set_Image(BufferedImage im){
        imag=im;
    }

    private int nr_asteroidy=0;

    /**
     * Random pomaga w losowym rozmieszczeniu oraz towrzeniu asteroid
     */
    Random random=new Random();

    boolean asteroid_0=false,asteroid_1=false,asteroid_2=false,asteroid_3=false,asteroid_4=false,asteroid_5=false,asteroid_6=false,asteroid_7=false,asteroid_8=false,asteroid_9=false,asteroid_10=false,asteroid_11=false,asteroid_12=false,asteroid_13=false,asteroid_14=false,asteroid_15=false;
    Asteroid asteroid0,asteroid1,asteroid2,asteroid3,asteroid4,asteroid5,asteroid6,asteroid7,asteroid8,asteroid9,asteroid10,asteroid11,asteroid12,asteroid13,asteroid14,asteroid15;

    /**
     * funckja update odpowiada za dzialania odbywajace się podczas dzialania stanu gry GS_GAME
     */
    public void update(){

            if(!(czy_pauza)) {
                if (Keyboard.getKeyOnce(KeyEvent.VK_P)) czy_pauza = true;
                add_asteroid();
                mapa.update();
                rocket.update(mapa);
                update_asteroid();


                if (paliwo > 0) {
                    sterowanie();
                }
                else {// gdy zabraknie paliwa
                    rocket.anim=s_rocket;
                    rocket.vel.y = Y_vel;
                    rocket.vel.x = X_vel;
                }
            }
        if (Keyboard.getKeyOnce(KeyEvent.VK_P)) czy_pauza = false;
    }



    /**
     * zmienna okreslajaca szerokosc rakiety
     */
    int rocket_width;


    boolean live=true, HP_change =true;
    /**
     * Funkcja odpowiada za wszystkie grafiki i animacje wyswietlane na ekranie komputera
     * @param s zmienna dotyczaca obsługiwanego Screena
     */
    public void render(Screen s) {

        mapa.render(s);

        if(czy_pauza){
            s.renderSprite(200, 20, pauza);
        } else {
            if(live){

            if (colider_color_planet()) {
                Y_vel=rocket.vel.y;
                rocket.vel.x = 0;
                rocket.vel.y = 0;
                if (colider_pixel()){
                    System.out.println(Y_vel);
                   if(Y_vel<=max_landing_speed) {
                       poziom++;
                       punkty = punkty + paliwo/points_optimum;
                       GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_GAME, poziom, punkty, rocket.HP, difficulty);
                   }else
                       lost(s,punkty);

                } else {
                  lost(s,punkty);
                }
            }
            }else{
                lost(s,punkty);
            }

            rocket.render(s);
          //  s.frect(50, 50, 3, 3, 0x7f3f3f);
            asteroid_render(s);
            if(colider_color_asteroid()) {
                if (rocket.HP > 0){
                    rocket.HP = rocket.HP-1;
                    HP_change = true;
                    System.out.println("kolizja z asteroida");
                    rocket.pos.x=(float) GameConfig.getPosx()+15;
                    rocket.pos.y=(float) GameConfig.getPosy()+15;
                }else{
                    lost(s,punkty);
                }
            }

            if(HP_change){
                for(int x=0; x<rocket.HP; x++)
                    s.renderSprite(530-17*x, 17, heart);
            }

        }
        //paski paliwa
        s.frect(450, 5, 100, 10, 0x7F0037);//pasek pod paliwem
        s.frect(452, 6, paliwo/(10*iloczyn_paliwa), 8, 0x4CFF00);//pasek pliwa

        rocket.render(s);
    }

    /////////////////////////////////////////   OBSZERNE FUNKCJE STOSOWANE W update i render    /////////////////////////////////////////





    /**
     * Fukcja odpowiadajaca za sprawdzanie czy rakieta nie wylatuje poza ekran
     * @return zwraca true jesli rakieta nie wyleci poza ekran
     */
    private boolean if_inside_screen(){
        if( rocket.pos.x>3 && rocket.pos.x<Screen.WIDTH-15)
            if(rocket.pos.y>7)
            return true;

        return false;
    }


    /**
     * Funkcja okreslajaca polozenie ladowiska na danej mapie
     * @return zwraca true w przypadku poprawnego ladowania
     */
    public boolean colider_pixel(){
        // if (rocket.pos.y>=landing_y-33)//Nie musimy sprawdzać wysokości, ponieważ lądowisko ma jeden poziom dizęki czemu wystarczy funkcja colider_color// 33 to wysokość rakiety|| TUTAJ BYLO 91-29
        if (rocket.pos.x >= mapa.landing_x_left && rocket.pos.x <= mapa.landing_x_right && rocket.pos.y >= mapa.landing_y )// szerokość rakiety to 29 ujęta w przypisaniu zmiennej|| TUTAJ BYLO 97 i 139 wiec 317=200+146-29
            return true;

        return false;
    }

    boolean game_finised=false;

    /**
     * Funkcja obsługująca zdarzenia po przegranej
     * @param s obkiety przekazany w celu możliwości wyświetlania inforacji o przegranje
     * @param punkty uzyskane punkty
     */
    void lost(Screen s, int punkty){
        live = false;
        rocket.anim = s_boom;
        s.renderSprite(155, 20, przegrales);
        rocket.vel.x = 0;
        rocket.vel.y = 0;
        if (Keyboard.getKeyOnce(KeyEvent.VK_ENTER))
            GameStateManager.ChangeGameState(GameStateManager.GAME_STATE_MENU, difficulty);

        if(!game_finised) {
            Nickname nick = new Nickname(punkty);
            nick.setLocationRelativeTo(null);
            nick.setResizable(true);
            nick.setVisible(true);
           // nick.actionPerformed(this.);

            game_finised=true;
        }


        }


    /*
    //Funkcja oddana w ETAPIE 3
    public boolean colider_color(){
        if(if_inside_screen())
            for(rocket_width=0 ; rocket_width<31; rocket_width++ )
                if(!(background_color == imag.getRGB((int) rocket.pos.x + rocket_width, (int) rocket.pos.y + 30)))   //-8355712 odpowiada kolorowi planety
                    return true;


        return false;// 0,2 sub string
    }*/

    //Funkcja jak w Etapie 3, ale nieczułą na kanał alfa
    private int rocket_lenght;
    String backgorund_color_string= Integer.toHexString(background_color).substring(2);
    /**
     * funkcja sprawdzajaca czy kolizja statku z planeta ma miejsce
     */
    private boolean colider_color_planet() {
        if (if_inside_screen()) {
            for (rocket_width = 4; rocket_width < 30; rocket_width++)
                if (!(backgorund_color_string.equals(Integer.toHexString(imag.getRGB((int) rocket.pos.x + rocket_width, (int) rocket.pos.y + 30)).substring(2))))
                    //wykrywanie czy jest na czarnym tle
                    if(!(asteroid_color0_string.equals(Integer.toHexString(imag.getRGB((int) rocket.pos.x + rocket_width, (int) rocket.pos.y + 30)).substring(2))))
                        //ignoruje komete, 1 kolor zolty
                        if(!(asteroid_color1_string.equals(Integer.toHexString(imag.getRGB((int) rocket.pos.x + rocket_width, (int) rocket.pos.y + 30)).substring(2))))
                            //ignoruje komete, 2 kolor czerwony
                            if(!(asteroid_color2_string.equals(Integer.toHexString(imag.getRGB((int) rocket.pos.x + rocket_width, (int) rocket.pos.y + 30)).substring(2)))){
                                //ignoruje komete, 3 kolor brazowy
                                System.out.println("kolizja z planeta");
                                return true;
                            }

           }
            return false;// 0,2 sub string
        }


    /**
     * Okresla czy dochodzi do kolizji asteroidy ze statkiem
     * @return zwraca true gdy statek uderzy w asteroide
     */
    private boolean colider_color_asteroid(){

        if(if_inside_screen()) {
            for (rocket_width = 1; rocket_width < 30; rocket_width++)
                if (asteroid_color0_string.equals(Integer.toHexString(imag.getRGB((int) rocket.pos.x + rocket_width, (int) rocket.pos.y + 32)).substring(2)))
                    return true;

            for (rocket_width = 8; rocket_width < 23; rocket_width++)
                if (asteroid_color0_string.equals(Integer.toHexString(imag.getRGB((int) rocket.pos.x + rocket_width, (int) rocket.pos.y)).substring(2)))
                    return true;

            for(rocket_lenght=3; rocket_lenght<20; rocket_lenght++) //sprawdzanie lewej strony rakiety
                if (asteroid_color0_string.equals(Integer.toHexString(imag.getRGB((int) rocket.pos.x + 3, (int) rocket.pos.y +rocket_lenght)).substring(2)))
                    return true;

            for(rocket_lenght=20; rocket_lenght<30; rocket_lenght++) //sprawdzanie lewej strony rakiety
                if (asteroid_color0_string.equals(Integer.toHexString(imag.getRGB((int) rocket.pos.x, (int) rocket.pos.y +rocket_lenght)).substring(2)))
                    return true;

            for(rocket_lenght=2; rocket_lenght<20; rocket_lenght++)
                if (asteroid_color0_string.equals(Integer.toHexString(imag.getRGB((int) rocket.pos.x + 27 , (int) rocket.pos.y +rocket_lenght)).substring(2)))
                    return true;

            for(rocket_lenght=20; rocket_lenght<30; rocket_lenght++)
                if (asteroid_color0_string.equals(Integer.toHexString(imag.getRGB((int) rocket.pos.x + 29, (int) rocket.pos.y +rocket_lenght)).substring(2)))
                    return true;
        }
        return false;// 0,2 sub string
    }


    /**
     *Losowe dodawanie kolejnych obiektów asteroid
     */
    void add_asteroid() {
        if (1 == random.nextInt(szansa_na_asteroide)) {
            if (nr_asteroidy == asteroid_how_fast) {
                asteroid0 = new Asteroid();
                // asteroid0.set_Image_asteroid(imag);
                asteroid_0 = true;
            } else if (nr_asteroidy ==asteroid_how_fast+1 ) {
                asteroid1 = new Asteroid();
                //  asteroid1.set_Image_asteroid(imag);
                asteroid_1 = true;
            } else if (nr_asteroidy == asteroid_how_fast+2) {
                asteroid2 = new Asteroid();
                // asteroid2.set_Image_asteroid(imag);
                asteroid_2 = true;
            } else if (nr_asteroidy == asteroid_how_fast+3) {
                asteroid3 = new Asteroid();
                //  asteroid3.set_Image_asteroid(imag);
                asteroid_3 = true;
            } else if (nr_asteroidy == asteroid_how_fast+4) {
                asteroid4 = new Asteroid();
                //   asteroid4.set_Image_asteroid(imag);
                asteroid_4 = true;
            } else if (nr_asteroidy == asteroid_how_fast+5) {
                asteroid5 = new Asteroid();
                // asteroid5.set_Image_asteroid(imag);
                asteroid_5 = true;
            } else if (nr_asteroidy == asteroid_how_fast+6) {
                asteroid6 = new Asteroid();
                //  asteroid6.set_Image_asteroid(imag);
                asteroid_6 = true;
            } else if (nr_asteroidy == asteroid_how_fast+7) {
                asteroid7 = new Asteroid();
                //  asteroid7.set_Image_asteroid(imag);
                asteroid_7 = true;
            } else if (nr_asteroidy == asteroid_how_fast+8) {
                asteroid8 = new Asteroid();
                //  asteroid1.set_Image_asteroid(imag);
                asteroid_8 = true;
            } else if (nr_asteroidy == asteroid_how_fast+9) {
                asteroid9 = new Asteroid();
                // asteroid2.set_Image_asteroid(imag);
                asteroid_9 = true;
            } else if (nr_asteroidy == asteroid_how_fast+10) {
                asteroid10 = new Asteroid();
                //  asteroid3.set_Image_asteroid(imag);
                asteroid_10 = true;
            } else if (nr_asteroidy == asteroid_how_fast+11) {
                asteroid11 = new Asteroid();
                //   asteroid4.set_Image_asteroid(imag);
                asteroid_11 = true;
            } else if (nr_asteroidy == asteroid_how_fast+12) {
                asteroid12 = new Asteroid();
                // asteroid5.set_Image_asteroid(imag);
                asteroid_12 = true;
            } else if (nr_asteroidy == asteroid_how_fast+13) {
                asteroid13 = new Asteroid();
                //  asteroid6.set_Image_asteroid(imag);
                asteroid_13 = true;
            } else if (nr_asteroidy == asteroid_how_fast+14) {
                asteroid14 = new Asteroid();
                //  asteroid7.set_Image_asteroid(imag);
                asteroid_14 = true;
            }  else if (nr_asteroidy == asteroid_how_fast+15) {
                asteroid15 = new Asteroid();
                 //  asteroid7.set_Image_asteroid(imag);
                asteroid_15 = true;
            }  else if (nr_asteroidy ==asteroid_how_fast+16 ) {
                nr_asteroidy = asteroid_how_fast-1;
            }
            nr_asteroidy++;
        }
    }

    /**
     * Aktualizacja położenia asteroid
     */
    void update_asteroid(){
        if(asteroid_0) asteroid0.update(mapa);
        if(asteroid_1) asteroid1.update(mapa);
        if(asteroid_2) asteroid2.update(mapa);
        if(asteroid_3) asteroid3.update(mapa);
        if(asteroid_4) asteroid4.update(mapa);
        if(asteroid_5) asteroid5.update(mapa);
        if(asteroid_6) asteroid6.update(mapa);
        if(asteroid_7) asteroid7.update(mapa);
        if(asteroid_8) asteroid8.update(mapa);
        if(asteroid_9) asteroid9.update(mapa);
        if(asteroid_10) asteroid10.update(mapa);
        if(asteroid_11) asteroid11.update(mapa);
        if(asteroid_12) asteroid12.update(mapa);
        if(asteroid_13) asteroid13.update(mapa);
        if(asteroid_14) asteroid14.update(mapa);
        if(asteroid_15) asteroid15.update(mapa);
    }

    /**
     * Wyswietlanie asteroid
     * @param s pozwala wyswietlic asteroidy na ekranie
     */
    void asteroid_render(Screen s){
        if(asteroid_0) asteroid0.render(s);
        if(asteroid_1) asteroid1.render(s);
        if(asteroid_2) asteroid2.render(s);
        if(asteroid_3) asteroid3.render(s);
        if(asteroid_4) asteroid4.render(s);
        if(asteroid_5) asteroid5.render(s);
        if(asteroid_6) asteroid6.render(s);
        if(asteroid_7) asteroid7.render(s);
        if(asteroid_8) asteroid8.render(s);
        if(asteroid_9) asteroid9.render(s);
        if(asteroid_10) asteroid10.render(s);
        if(asteroid_11) asteroid11.render(s);
        if(asteroid_12) asteroid12.render(s);
        if(asteroid_13) asteroid13.render(s);
        if(asteroid_14) asteroid14.render(s);
        if(asteroid_15) asteroid15.render(s);
    }

    /**
     * Funkcja sprawdza czy jakiś klawisz jest wciśnięty i odpowiednio wplywa na ruch rakiety
     */
    void sterowanie () {
        if (live) {
            if(rocket.pos.x<4) {
                rocket.vel.x = 0;
            }
            if (Keyboard.getKey(KeyEvent.VK_W)) {
                paliwo = paliwo - 4;
                //rocket.vel.y=-1;
                if (rocket.pos.y > 7)//nie wylatuje po za ekran
                    //if(rocket.vel.y>-2 &&)
                    rocket.vel.y += (-0.8 + rocket.vel.y) / 40;

                else
                    rocket.vel.y = 0;

                rocket.anim = s_rocket_up;
            } else {
                rocket.vel.y += (Y_vel - rocket.vel.y) / 50;
                rocket.anim = s_rocket;
            }


            if (Keyboard.getKey(KeyEvent.VK_A)) {
                paliwo = paliwo - 2;
                if (rocket.pos.x > 6) {
                    //  System.out.println("A naped");
                    rocket.vel.x += (-0.8 - rocket.vel.x) / 10;
                } else
                    rocket.vel.x = 0;

                rocket.anim = s_rocket_right;
            } else {
                rocket.vel.x += (X_vel - rocket.vel.x) / 240;
            }

            if (Keyboard.getKey(KeyEvent.VK_D)) {
                paliwo = paliwo - 2;
                if (rocket.pos.x < Screen.WIDTH - 35) {
                    rocket.vel.x += (0.8 - rocket.vel.x) / 10;
                } else
                    rocket.vel.x = 0;

                rocket.anim = s_rocket_left;
            } else {
//            if (rocket.pos.x < 7 || rocket.pos.x > Screen.WIDTH - 15)
//                rocket.vel.x = 0;
//            else if(!(Keyboard.getKey(KeyEvent.VK_A)))
                rocket.vel.x += (X_vel - rocket.vel.x) / 240;
            }
        }
    }

    /**
     * Dobor odpowiednich zmiennych w zaleznosci od pozio,mu trudnosci
     * @param dif wybrany poziom trudnosci
     */
    void choosen_difficulty(int dif){
        if(dif==1){
            iloczyn_paliwa= GameConfig.getIloczyn_paliwa(difficulty);
            asteroid_how_fast= GameConfig.getAsteroid_how_fast(difficulty);//większa liczba pozniej sie pojawiaja asterioidy
            szansa_na_asteroide= GameConfig.getSzansa_na_asteroide(difficulty);
            paliwo= GameConfig.getPaliwo(difficulty);
            paliwo=paliwo*iloczyn_paliwa;
            points_optimum= GameConfig.getPoints_optimum(difficulty);
            rocket.HP= GameConfig.getRocketHP(difficulty);
        }

        if(dif==2){
            iloczyn_paliwa= GameConfig.getIloczyn_paliwa(difficulty);
            asteroid_how_fast= GameConfig.getAsteroid_how_fast(difficulty);//większa liczba pozniej sie pojawiaja asterioidy
            szansa_na_asteroide= GameConfig.getSzansa_na_asteroide(difficulty);
            paliwo= GameConfig.getPaliwo(difficulty);
            paliwo=paliwo*iloczyn_paliwa;
            points_optimum= GameConfig.getPoints_optimum(difficulty);
            rocket.HP= GameConfig.getRocketHP(difficulty);
        }

        if(dif==3){
            iloczyn_paliwa= GameConfig.getIloczyn_paliwa(difficulty);
            asteroid_how_fast= GameConfig.getAsteroid_how_fast(difficulty);//w// iększa liczba pozniej sie pojawiaja asterioidy
            szansa_na_asteroide= GameConfig.getSzansa_na_asteroide(difficulty);
            paliwo= GameConfig.getPaliwo(difficulty);
            paliwo=paliwo*iloczyn_paliwa;
            points_optimum= GameConfig.getPoints_optimum(difficulty);
            rocket.HP= GameConfig.getRocketHP(difficulty);
        }
    }
}