package core;

import input.SaveData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Klasa odpowiedzialna za wszelkie dzialnia zwiazane z oknem dotyczacym wproawdzania Nickname'u przez gracza
 */
public class Nickname extends JFrame implements ActionListener {

    private static String fileName="wynik.txt";
    JLabel lnick;
    JLabel lpunkty;
    JTextField tnick;
    JButton bConfirm;
    private static String tempNick;
    private int punkty=10;
    private static String nick;
    private static Integer score;
    public SaveData saveData;

    /**
     * Konstruktor klasy odpowiedzialny za dopasowywanie rozmiarow okna, jego poszczegolnych elemntow oraz wyswietlanie finalnego wyniku gracza
     * @param punkty parametr przekazujacy wynik osiagniety przez danego gracza
     */

    public Nickname(int punkty){

        setSize(400,200);// 400 150
        setLayout(null);

        lnick = new JLabel("Wprowadź swój nick:");
        lnick.setBounds(40,40,150,20);//40 40 150 20
        add(lnick);


        lpunkty = new JLabel("Liczba punktów: "+String.valueOf(punkty));
        lpunkty.setBounds(40,60,150,20);//40 40 150 20
        add(lpunkty);

        tnick = new JTextField("");
        tnick.setBounds(190,40,150,20);// 190 40 150 20
        add(tnick);


        bConfirm = new JButton("Zatwierdź");
        bConfirm.setBounds(250,85,100,20);// 250 70 100 20
        add(bConfirm);
        bConfirm.addActionListener(this);

        this.punkty=punkty;
    }

    /**
     * Funkcja odpowiedzialna za zapisywanie danych gracza po kliknieciu w przycisk "Zatwierdz"
     * @param e parametr zczytujacy moment Klikniecia w przycisk
     */
    public void actionPerformed(ActionEvent e) {
        tempNick = tnick.getText();
        System.out.println(tempNick);

        score=punkty;
        nick=tempNick;
        if(nick==null){
            nick="nick";
        }
            try {
                saveData=new SaveData(score, nick, fileName);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        dispose();
    }

}
