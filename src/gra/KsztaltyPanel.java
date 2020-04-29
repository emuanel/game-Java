/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * panel gry Rozpoznawanie ksztaltow
 * @author jfili
 */
class KsztaltyPanel extends JPanel {
    /** Tablica ksztaltow*/
    Ksztalt[] ksztalty;
    /** pole tekstowe z nazwa ksztaltu*/
    JTextField haslo = new JTextField();
    /** zmienna odpowiadajaca za to czy uzytkownik udzielil juz odpowiedzi*/
    boolean odpowiedz = true;
    /** wybrany ksztalt przez uzytkownika */
    int wybor;
    /** odpowiedz a*/
    int los1 = 0;
    /** odpowiedz b*/
    int los2 = 0;
    /** odpowiedz c*/
    int los3 = 0;
    /** odpowiedz d*/
    int los4 = 0;
    /** ksztalt do odgadniecia*/
    int hasl;
    /** obsluga myszy*/
    MouseListener mysz;
    /** Tlo panelu*/
    private Image img;
    /** Zegar*/
    Timer timer;
    /** Czas rowny na poczatku 60s*/
    int i = 60;
    /** Zmienna przechwytujaca status gry*/
    Status nowagra;
    
    /**
     * konstruktor panelu gry owocowy sad
     * @param img tlo panelu
     */
    public KsztaltyPanel(Image img) {
        nowagra = new Status();
        //tworzenie tablicy ksztaltow
        ksztalty = new Ksztalt[13];
        for (int i = 0; i < ksztalty.length; i++) {
            ksztalty[i] = new Ksztalt();
        }
        //wczytanie tla
        this.img = img;
        //ustawienia panelu
        Dimension size = new Dimension(500, 500);
        setPreferredSize(size);
        setLayout(null);
        setVisible(true);
        //dodanie i ustawienia componentow do paska gornego
        JPanel informacje = new JPanel();
        JTextField czas = new JTextField();

        JTextField wynik = new JTextField("Wynik: " + nowagra.punkty);
        JButton menu = new JButton("Menu");
        informacje.add(czas);
        informacje.add(wynik);
        informacje.add(menu);
        informacje.setBorder(BorderFactory.createLineBorder(Color.black));

        Dimension rozmiar = new Dimension(300, 60);
        czas.setPreferredSize(rozmiar);
        wynik.setPreferredSize(rozmiar);
        menu.setPreferredSize(rozmiar);

        czas.setOpaque(false);
        wynik.setOpaque(false);
        menu.setOpaque(false);

        czas.setBackground(new Color(139, 69, 19));
        wynik.setBackground(new Color(139, 69, 19));
        menu.setContentAreaFilled(false);

        czas.setBorder(null);
        wynik.setBorder(null);
        menu.setBorderPainted(false);

        Font bArial = new Font("Forte", Font.BOLD, 50);
        czas.setFont(bArial);
        wynik.setFont(bArial);
        menu.setFont(bArial);

        informacje.setBounds(0, 0, 1380, 70);
        informacje.setBackground(new Color(139, 69, 19));
        add(informacje);
        //obsluga powrotu do menu
        menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Main.okno.getContentPane().add(new MenuPanel(new ImageIcon("images/tlo1.jpg").getImage()));
            }
        });
        //obsluga zegara
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                czas.setText("Czas: " + i--);
                //dodanie panelu po zakonieczeniu czasu
                if (i < 0) {
                    
                    JPanel okienkoWynikowe = new JPanel();

                    JTextField wynik = new JTextField("Wynik: " + nowagra.punkty);
                    JButton menu = new JButton("Menu");

                    JLabel koniec = new JLabel(" Koniec gry!");
                    okienkoWynikowe.add(koniec);

                    okienkoWynikowe.add(wynik);
                    okienkoWynikowe.add(menu);

                    okienkoWynikowe.setBorder(BorderFactory.createLineBorder(Color.black));

                    Dimension rozmiar = new Dimension(400, 60);
                    Dimension rozmiar2 = new Dimension(300, 60);

                    wynik.setPreferredSize(rozmiar);
                    menu.setSize(150, 60);

                    koniec.setSize(rozmiar);

                    wynik.setOpaque(false);
                    menu.setOpaque(false);

                    koniec.setOpaque(false);

                    wynik.setBackground(new Color(139, 69, 19));
                    koniec.setBackground(new Color(139, 69, 19));
                    menu.setContentAreaFilled(false);

                    wynik.setBorder(null);
                    koniec.setBorder(null);
                    menu.setBorderPainted(false);

                    Font bArial = new Font("Forte", Font.BOLD, 50);

                    wynik.setFont(bArial);
                    koniec.setFont(bArial);
                    menu.setFont(bArial);

                    okienkoWynikowe.setBounds(Main.gameWidth / 2 - 250, Main.gameHeight / 2 - 100, 500, 220);
                    okienkoWynikowe.setBackground(new Color(139, 69, 19));
                    add(okienkoWynikowe);
                    menu.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            setVisible(false);
                            Main.okno.getContentPane().add(new MenuPanel(new ImageIcon("images/tlo1.jpg").getImage()));
                        }

                    });
                    //wylaczenie oblugi myszki
                    removeMouseListener(mysz);
                    timer.cancel();
                }
            }
        }, 0, 1000);
        
        haslo.setOpaque(false);
        haslo.setBorder(null);
        haslo.setBounds(380, 130, 400, 50);
        haslo.setFont(bArial);
        add(haslo);
        //obsluga myszki
        mysz = new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getX() > 80 && me.getX() < 470) {
                    if (me.getY() > 120 && me.getY() < 500) {
                        //sprawdzenie czy dobra odpowiedz
                        if (hasl == los1) {
                            wynik.setText("Wynik: " + Integer.toString(++nowagra.punkty));
                            playgood();
                        } else {
                            playbad();
                        }
                        odpowiedz = true;
                    }
                }
                if (me.getX() > 480 && me.getX() < 870) {
                    if (me.getY() > 120 && me.getY() < 500) {
                        //sprawdzenie czy dobra odpowiedz
                        if (hasl == los2) {
                            wynik.setText("Wynik: " + Integer.toString(++nowagra.punkty));
                            playgood();
                        } else {
                            playbad();
                        }
                        odpowiedz = true;
                    }
                }
                if (me.getX() > 80 && me.getX() < 470) {
                    if (me.getY() > 500 && me.getY() < 870) {
                        //sprawdzenie czy dobra odpowiedz
                        if (hasl == los3) {
                            wynik.setText("Wynik: " + Integer.toString(++nowagra.punkty));
                            playgood();
                        } else {
                            playbad();
                        }
                        odpowiedz = true;
                    }
                }
                if (me.getX() > 480 && me.getX() < 870) {
                    if (me.getY() > 500 && me.getY() < 870) {
                        //sprawdzenie czy dobra odpowiedz
                        if (hasl == los4) {
                            wynik.setText("Wynik: " + Integer.toString(++nowagra.punkty));
                            playgood();
                        } else {
                            playbad();
                        }
                        odpowiedz = true;
                    }
                }
            }
        };
        //dodanie obslugi myszki do panelu
        addMouseListener(mysz);
    }
    /**
     * rysowanie tla oraz ksztaltow 
     * @param g
     */
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
        Random r = new Random();
        //losowanie odpowiedzi oraz hasla jesli odpowiedzniano do poprzednie haslo
        if (odpowiedz) {
            los1 = 0;
            los2 = 0;
            los3 = 0;
            los4 = 0;
            los1 = r.nextInt(ksztalty.length);
            do {
                los2 = r.nextInt(ksztalty.length);
            } while (los2 == los1);
            do {
                los3 = r.nextInt(ksztalty.length);
            } while (los3 == los1 || los3 == los2);
            do {
                los4 = r.nextInt(ksztalty.length);
            } while (los4 == los1 || los4 == los2 || los4 == los3);
            do {
                hasl = r.nextInt(ksztalty.length);
            } while (hasl != los1 && hasl != los2 && hasl != los3 && hasl != los4);
        }
        haslo.setText(ksztalty[hasl].nazwa);
        g.drawImage(ksztalty[los1].cien, 140, 200, null);
        g.drawImage(ksztalty[los2].cien, 500, 200, null);
        g.drawImage(ksztalty[los3].cien, 140, 550, null);
        g.drawImage(ksztalty[los4].cien, 500, 550, null);
        odpowiedz = false;
        if (i != 0) {
            repaint();
        }
    }
    /**
     * odtworzenie dzwieku oznaczajacego poprawna odpowiedz
     */
    static void playgood() {
        try {
            String filePath = "sounds/good.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {

        }
    }
    /**
     * odtworzenie dzwieku oznaczajacegozla odpowiedz
     */
    static void playbad() {
        try {

            String filePath = "sounds/bad.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception e) {

        }
    }
}
