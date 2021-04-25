package input;

import core.gamestates.Ranking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Klasa wczytujaca oraz sortujaca wyniki graczy
 */
public class RankingReader {

        private static File file;
        private static Scanner scanner;
        public static String[] nick= new String[100];
        public static int[] score=new int[100];
        private static String[] score_string= new String[100];

    /**
     * Konstruktor wczytuje a nastepnie sortuje wyniki oraz graczy
     * @param fileName
     */
    public RankingReader(String fileName){
            load(fileName);
            take_nick_and_score();
            sort(score, nick);
        }

    /**
     * Wczyhtywanie odpowiedniego pliku
     * @param fileName nawaz pliku z ktorego pobieramy dane
     */
    public static void load(String fileName) {
            file = new File(fileName);
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    /**
     * Pobieranie nazw oraz wynikow
     */
    public void take_nick_and_score() {
        String tempScannerValue;
        int temp=0;
        int k=0;
        int i=0;
        while (this.scanner.hasNextLine()) {
            temp++;
            tempScannerValue = this.scanner.nextLine();
            if(temp % 2 == 1){
                nick[k]=tempScannerValue;
                k++;
            }
            if(temp % 2 == 0) {
                score[i] = Integer.parseInt(tempScannerValue);
                i++;
            }
        }
    }

    /**
     * Sortowanie wynikow graczy
     * @param score wynik
     * @param nick nazawa gracza
     */
        private void sort(int score[], String nick[]){
            int temp;
            int zmiana = 1;
            String temp_nick;
            while(zmiana > 0){
                zmiana = 0;
                for(int i = 0; i< score.length-1; i++){
                    if(score[i]< score[i+1]){
                        temp = score[i+1];
                       temp_nick=nick[i+1];
                        score[i+1] = score[i];
                        nick[i+1]=nick[i];
                        score[i] = temp;
                        nick[i]=temp_nick;
                        zmiana++;
                    }
                }
            }
//            for(int i=0; i<10; i++){
//               // System.out.println();
//                System.out.println(score[i]+"   "+nick[i]);
//            }
        }

    /**
     * Wyswietlanie 10 najelpeszych wynikow
     */
    public void show(){
            for(int i=0; i<10; i++){
                // System.out.println();
                System.out.println(score[i]+"   "+nick[i]);
            }

            Ranking rank = new Ranking(score,nick);
            rank.setLocationRelativeTo(null);//wyświetlanie okienka na środku ekaranu
            rank.setResizable(true);// rozszerzanie okienkaaaa
            rank.setVisible(true);

        }

    }

