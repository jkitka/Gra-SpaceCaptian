package core.gamestates;

import input.SaveData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Klasa odpowiedzialna za okno wyswietlajace ranking zwiazany z najlepszymi wynikami
 */

public class Ranking extends JFrame implements ActionListener {

    JLabel nazwa, nr1, nr2, nr3, nr4, nr5, nr6, nr7, nr8, nr9, nr10;
    JButton bConfirm;
    private int fontSize = 24;
    public Font font = new Font("Calibri", Font.BOLD, fontSize);

    /**
     * Konstruktor klasy Ranking wczytujacy poszczegolne wiersze pliku z wyniami oraz dopasowujacy rozmiary poszczegolnych elementow okna
     * @param stab zmienna tablicowa zawierajaca wyniki graczy zapisane w odpowiedniej kolejnsci
     * @param ntab zmienna tablicowa zawierajaca nicki graczy zapisane w odpowiedniej kolejnsci
     */

    public Ranking(int stab[], String ntab[]) {
        setSize(260, 450);

        setTitle("RANKING");
        setLayout(null);

        nazwa = new JLabel("   WYNIK NICK");
        nazwa.setBounds(40, 10, 250, 30);
        nazwa.setFont(font);
        add(nazwa);

        nr1 = new JLabel("1. " + stab[0] + " " + ntab[0]);
        nr1.setBounds(40, 40, 250, 30);
        nr1.setFont(font);
        add(nr1);

        nr2 = new JLabel("2. " + stab[1] + " " + ntab[1]);
        nr2.setBounds(40, 70, 250, 30);
        nr2.setFont(font);
        add(nr2);

        nr3 = new JLabel("3. " + stab[2] + " " + ntab[2]);
        nr3.setBounds(40, 100, 250, 30);
        nr3.setFont(font);
        add(nr3);

        nr4 = new JLabel("4. " + stab[3] + " " + ntab[3]);
        nr4.setBounds(40, 130, 250, 30);
        nr4.setFont(font);
        add(nr4);

        nr5 = new JLabel("5. " + stab[4] + " " + ntab[4]);
        nr5.setBounds(40, 160, 250, 30);
        nr5.setFont(font);
        add(nr5);

        nr6 = new JLabel("6. " + stab[5] + " " + ntab[5]);
        nr6.setBounds(40, 190, 250, 30);
        nr6.setFont(font);
        add(nr6);

        nr7 = new JLabel("7. " + stab[6] + " " + ntab[6]);
        nr7.setBounds(40, 220, 250, 30);
        nr7.setFont(font);
        add(nr7);

        nr8 = new JLabel("8. " + stab[7] + " " + ntab[7]);
        nr8.setBounds(40, 250, 250, 30);
        nr8.setFont(font);
        add(nr8);

        nr9 = new JLabel("9. " + stab[8] + " " + ntab[8]);
        nr9.setBounds(40, 280, 250, 30);
        nr9.setFont(font);
        add(nr9);

        nr10 = new JLabel("10. " + stab[9] + " " + ntab[9]);
        nr10.setBounds(40, 310, 250, 30);
        nr10.setFont(font);
        add(nr10);


        bConfirm = new JButton("Okej");
        bConfirm.setBounds(70, 360, 100, 20);
        add(bConfirm);
        bConfirm.addActionListener(this);


    }

    /**
     * Klasa pozwalajaca na automatyczne zamkniecie okna po klinieciu mysza w przycisk
     * @param e parametr pozwalajacy na zamkniecie okna po klinieciu w przycisk myszka
     */
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}