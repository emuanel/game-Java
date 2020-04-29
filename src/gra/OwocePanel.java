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
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * panel gry Owocowy sad
 * @author jfili
 */
class OwocePanel extends JPanel implements KeyListener {
    /** zmienna ktora odpowiada za okres spadania owocow*/
    int co_ile_spada = 999;
    /** tablica owocow*/
    Owoce owocki[];
    /** ilosc owcow spadajacych+owocow ktore spadly*/
    int owocki_poruszajace;
    /** zdjecie kosza*/
    Image kosz = new ImageIcon("images/kosz.png").getImage();
    /** polozenie kosza*/
    AffineTransform polozenie = null;
    /** x polozenia kosza*/
    int locx = Main.gameWidth / 2;
    /** pole tekstowe z wynikiem*/
    JTextField wynik;
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
     * @param gra status z wybranym wczesniej poziomem
     */
    OwocePanel(Image img, Status gra) {
        nowagra = gra;
        nowagra.punkty = 0;
        owocki = new Owoce[60];
        for (int i = 0; i < owocki.length; i++) {
            owocki[i] = new Owoce();
        }
        this.img = img;
        Dimension size = new Dimension(Main.gameWidth, Main.gameHeight);
        setPreferredSize(size);
        setLayout(null);
        setVisible(true);
        
        addKeyListener(this);
        //dodanie paska gornego z informacjami
        JPanel informacje = new JPanel();
        JTextField czas = new JTextField();
        wynik = new JTextField("Wynik: 0");
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
                co_ile_spada--;
                //koniec czasu=dodaj panel
                if (i < 0) {
                    JPanel okienkoWynikowe = new JPanel();
                    JLabel poziom = null;
                    if (nowagra.poziom == 1) {
                        poziom = new JLabel("Poziom: łatwy");
                    }
                    if (nowagra.poziom == 2) {
                        poziom = new JLabel("Poziom: sredni");
                    }
                    if (nowagra.poziom == 3) {
                        poziom = new JLabel("Poziom: trudny");
                    }
                    JTextField wynik = new JTextField("Wynik: " + nowagra.punkty);
                    JButton menu = new JButton("Menu");

                    JLabel koniec = new JLabel(" Koniec gry!");
                    okienkoWynikowe.add(koniec);
                    okienkoWynikowe.add(poziom);
                    okienkoWynikowe.add(wynik);
                    okienkoWynikowe.add(menu);

                    okienkoWynikowe.setBorder(BorderFactory.createLineBorder(Color.black));

                    Dimension rozmiar = new Dimension(400, 60);
                    Dimension rozmiar2 = new Dimension(300, 60);
                    poziom.setPreferredSize(rozmiar);
                    wynik.setPreferredSize(rozmiar);
                    menu.setSize(150, 60);

                    koniec.setSize(rozmiar);

                    poziom.setOpaque(false);
                    wynik.setOpaque(false);
                    menu.setOpaque(false);

                    koniec.setOpaque(false);

                    poziom.setBackground(new Color(139, 69, 19));
                    wynik.setBackground(new Color(139, 69, 19));
                    koniec.setBackground(new Color(139, 69, 19));
                    menu.setContentAreaFilled(false);

                    poziom.setBorder(null);
                    wynik.setBorder(null);
                    koniec.setBorder(null);
                    menu.setBorderPainted(false);

                    Font bArial = new Font("Forte", Font.BOLD, 50);
                    poziom.setFont(bArial);
                    wynik.setFont(bArial);
                    koniec.setFont(bArial);
                    menu.setFont(bArial);

                    okienkoWynikowe.setBounds(Main.gameWidth / 2 - 250, Main.gameHeight / 2 - 150, 500, 300);
                    okienkoWynikowe.setBackground(new Color(139, 69, 19));
                    add(okienkoWynikowe);
                    menu.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            setVisible(false);
                            Main.okno.getContentPane().add(new MenuPanel(new ImageIcon("images/tlo1.jpg").getImage()));
                        }
                    });

                    timer.cancel();
                }

            }
        }, 0, 1000);

    }
    /**
     * metoda odpowiadajaca za przemieszczanie i rysowanie owocow oraz tla
     * sprawdza rowniez czy owoc wpadl do kosza
     * @param gs
     */
    public void paintComponent(Graphics gs) {
        Graphics2D g = (Graphics2D) gs;
        g.drawImage(img, 0, 0, null);
        //zaleznie od poziomu rozny okres spadania owocow
        if (nowagra.poziom == 1) {
            if (co_ile_spada % 5 == 0) {

                owocki_poruszajace++;

                co_ile_spada--;
            }
        }
        if (nowagra.poziom == 2 || nowagra.poziom == 3) {
            if (co_ile_spada % 3 == 0) {

                owocki_poruszajace++;

                co_ile_spada--;
            }
        }
        //sprawdzanie czy owoc wpadl do kosza
        for (int i = 0; i <= owocki_poruszajace; i++) {
            owocki[i].tx = AffineTransform.getTranslateInstance(owocki[i].x, owocki[i].y++);
            if (nowagra.poziom == 3) {
                owocki[i].tx.rotate(Math.toRadians(owocki[i].kat++));
            }
            g.drawImage(owocki[i].owoc, owocki[i].tx, null);
            if (owocki[i].y == (Main.gameHeight - kosz.getHeight(null) / 2)) {

                if (owocki[i].x > locx && owocki[i].x + owocki[i].owoc.getWidth(null) < locx + kosz.getWidth(null)) {
                    wynik.setText("Wynik: " + Integer.toString(++nowagra.punkty));
                }
            }
        }
        //kosz wychodzacy za ekran pojawia sie po drugiej stronie
        if (locx < 0 - kosz.getWidth(null)) {
            locx = Main.gameWidth - 1;
        }
        if (locx > Main.gameWidth) {
            locx = 0 - kosz.getWidth(null) + 1;
        }
        //rysowanie kosza
        polozenie = AffineTransform.getTranslateInstance(locx, Main.gameHeight - kosz.getHeight(this));
        g.drawImage(kosz, polozenie, null);
        if (i != 0) {
            repaint();
        }
    }
    /**
     * metoda odpowiadajaca za obsluge klawiatury (przemieszczanie kosza)
     * @param evt wcisniety klawisz klawiatury
     */
    public void keyPressed(KeyEvent evt) {
        //obsluga strzalek
        if (evt.getKeyCode() == 37) {
            locx = locx - 15;
        }
        if (evt.getKeyCode() == 39) {
            locx = locx + 15;

        }
    }

    public void keyReleased(KeyEvent evt) {
    }

    public void keyTyped(KeyEvent evt) {

    }

    public boolean isFocusTraversable() {
        return true;
    }

}
