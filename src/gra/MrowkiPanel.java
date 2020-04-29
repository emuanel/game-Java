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
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * panel gry formikarium 
 * @author jfili
 */
public class MrowkiPanel extends JPanel {
    /** Tlo panelu*/
    private Image img;
    /** Zegar*/
    Timer timer;
    /** Czas rowny na poczatku 60s*/
    int i = 60;
    /** Zmienna przechwytujaca status gry*/
    Status nowagra;
    /** Tablica mrowek*/
    Mrowki[] mrowy;
    /** Tablica rozgniecionych mrowek*/
    Mrowki[] mrowy_rozgniecione;
    /** ilosc rozgniecionych mrowek*/
    int iloscrozgniecionych = -1;
    /** obsluga myszki*/
    MouseAdapter myszka;
    /**
     * konstruktor panelu gry formikarium
     * @param img tlo panelu
     * @param gra status z wybranym wczesniej poziomem
     */
    public MrowkiPanel(Image img, Status gra) {
        nowagra = gra;
        //zaleznie od poziomu rozna liczba mrowek
        if (gra.poziom == 1) {
            mrowy = new Mrowki[10];
        }

        if (gra.poziom == 2) {
            mrowy = new Mrowki[15];
        }

        if (gra.poziom == 3) {
            mrowy = new Mrowki[20];
        }

        for (int i = 0; i < mrowy.length; i++) {
            mrowy[i] = new Mrowki();
        }
        mrowy_rozgniecione = new Mrowki[20];
        this.img = img;
        Dimension size = new Dimension(Main.gameWidth, Main.gameHeight);
        setPreferredSize(size);
        setLayout(null);
        setVisible(true);

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
        
        menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Main.okno.getContentPane().add(new MenuPanel(new ImageIcon("images/tlo1.jpg").getImage()));
            }
        });
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                czas.setText("Czas: " + i--);
                if (i < 0 || iloscrozgniecionych + 1 == mrowy.length) {
                    JPanel okienkoWynikowe = new JPanel();
                    JLabel poziom = null;
                    JLabel czas = new JLabel("Czas: " + Integer.toString(60 - i));
                    
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
                    okienkoWynikowe.add(czas);
                    okienkoWynikowe.add(wynik);
                    okienkoWynikowe.add(menu);

                    okienkoWynikowe.setBorder(BorderFactory.createLineBorder(Color.black));

                    Dimension rozmiar = new Dimension(400, 60);
                    Dimension rozmiar2 = new Dimension(300, 60);
                    poziom.setPreferredSize(rozmiar);
                    wynik.setPreferredSize(rozmiar);
                    czas.setPreferredSize(rozmiar);
                    menu.setSize(150, 60);

                    koniec.setSize(rozmiar);

                    poziom.setOpaque(false);
                    wynik.setOpaque(false);
                    menu.setOpaque(false);
                    czas.setOpaque(false);
                    koniec.setOpaque(false);

                    poziom.setBackground(new Color(139, 69, 19));
                    wynik.setBackground(new Color(139, 69, 19));
                    czas.setBackground(new Color(139, 69, 19));
                    koniec.setBackground(new Color(139, 69, 19));
                    menu.setContentAreaFilled(false);

                    poziom.setBorder(null);
                    wynik.setBorder(null);
                    czas.setBorder(null);
                    koniec.setBorder(null);
                    menu.setBorderPainted(false);

                    Font bArial = new Font("Forte", Font.BOLD, 50);
                    poziom.setFont(bArial);
                    wynik.setFont(bArial);
                    koniec.setFont(bArial);
                    czas.setFont(bArial);
                    menu.setFont(bArial);

                    okienkoWynikowe.setBounds(Main.gameWidth / 2 - 250, Main.gameHeight / 2 - 200, 500, 400);
                    okienkoWynikowe.setBackground(new Color(139, 69, 19));
                    add(okienkoWynikowe);
                    menu.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            setVisible(false);
                            Main.okno.getContentPane().add(new MenuPanel(new ImageIcon("images/tlo1.jpg").getImage()));
                        }

                    });
                    czas.setText("Czas: " + i--);
                    removeMouseListener (myszka);
                    timer.cancel();
                    
                }
            }
        }, 0, 1000);

        myszka = new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {

                for (int i = 0; i < mrowy.length; i++) {
                    if (me.getY() > 70) {
                        //sprawdzenie czy trafiono
                        if (mrowy[i].containsPoint(me.getX(), me.getY())) {
                            if (!mrowy[i].hit) {
                                iloscrozgniecionych++;
                                mrowy[i].mrowki = new ImageIcon("images/blot.png").getImage();
                                mrowy[i].hit = true;
                                gra.punkty++;
                                wynik.setText("Wynik: " + Integer.toString(gra.punkty));
                                mrowy_rozgniecione[iloscrozgniecionych] = mrowy[i];

                                break;
                            }
                        }
                    }
                }
            }
        };
        addMouseListener(myszka);
    }
    /**
     * metoda odpowiadajaca za przemiszczanie i rysowanie mrowek oraz tla
     * @param gs graphics
     */
    public void paintComponent(Graphics gs) {

        Graphics2D g = (Graphics2D) gs;
        g.drawImage(img, 0, 0, null);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //rysowanie rozgniecionych mrowek-plam
        for (int i = 0; i < iloscrozgniecionych + 1; i++) {
            g.drawImage(mrowy_rozgniecione[i].mrowki, mrowy_rozgniecione[i].tx, null);
        }
        //ruch mrowek na
        //okreslenie obecnego polozenia mrowki wzgledem punktu docelowego
        for (int i = mrowy.length - 1; i >= 0; i--) {
            {
                if (mrowy[i].celx > mrowy[i].x) {
                    if (!mrowy[i].hit) {
                        mrowy[i].tx = AffineTransform.getTranslateInstance(mrowy[i].x++, mrowy[i].y);
                    }
                }
                if (mrowy[i].cely > mrowy[i].y) {
                    if (!mrowy[i].hit) {
                        mrowy[i].tx = AffineTransform.getTranslateInstance(mrowy[i].x, mrowy[i].y++);
                    }
                }
                if (mrowy[i].celx < mrowy[i].x) {
                    if (!mrowy[i].hit) {
                        mrowy[i].tx = AffineTransform.getTranslateInstance(mrowy[i].x--, mrowy[i].y);
                    }
                }
                if (mrowy[i].cely < mrowy[i].y) {
                    if (!mrowy[i].hit) {
                        mrowy[i].tx = AffineTransform.getTranslateInstance(mrowy[i].x, mrowy[i].y--);
                    }
                }
                if (mrowy[i].celx == mrowy[i].x && mrowy[i].cely == mrowy[i].y) {
                    if (!mrowy[i].hit) {
                        Random r = new Random();
                        mrowy[i].celx = r.nextInt(Main.gameWidth);
                        mrowy[i].cely = r.nextInt(Main.gameHeight - 70) + 70;
                    }
                }
                if (mrowy[i].celx > mrowy[i].x && mrowy[i].cely > mrowy[i].y) {
                    if (!mrowy[i].hit) {
                        mrowy[i].tx.rotate(Math.toRadians(90));
                        mrowy[i].pozycja = 3;
                    }
                }
                if (mrowy[i].celx > mrowy[i].x && mrowy[i].cely < mrowy[i].y) {
                    if (!mrowy[i].hit) {
                        mrowy[i].tx.rotate(Math.toRadians(0));
                        mrowy[i].pozycja = 1;
                    }
                }
                if (mrowy[i].celx > mrowy[i].x && mrowy[i].cely == mrowy[i].y) {
                    if (!mrowy[i].hit) {
                        mrowy[i].tx.rotate(Math.toRadians(45));
                        mrowy[i].pozycja = 2;
                    }
                }
                if (mrowy[i].celx < mrowy[i].x && mrowy[i].cely < mrowy[i].y) {
                    if (!mrowy[i].hit) {
                        mrowy[i].tx.rotate(Math.toRadians(270));
                        mrowy[i].pozycja = 7;
                    }
                }
                if (mrowy[i].celx == mrowy[i].x && mrowy[i].cely > mrowy[i].y) {
                    if (!mrowy[i].hit) {
                        mrowy[i].tx.rotate(Math.toRadians(135));
                        mrowy[i].pozycja = 4;
                    }
                }
                if (mrowy[i].celx < mrowy[i].x && mrowy[i].cely > mrowy[i].y) {
                    if (!mrowy[i].hit) {
                        mrowy[i].tx.rotate(Math.toRadians(180));
                        mrowy[i].pozycja = 5;
                    }
                }
                if (mrowy[i].celx < mrowy[i].x && mrowy[i].cely == mrowy[i].y) {
                    if (!mrowy[i].hit) {
                        mrowy[i].tx.rotate(Math.toRadians(225));
                        mrowy[i].pozycja = 6;
                    }
                }
                if (mrowy[i].celx == mrowy[i].x && mrowy[i].cely < mrowy[i].y) {
                    if (!mrowy[i].hit) {
                        mrowy[i].tx.rotate(Math.toRadians(315));
                        mrowy[i].pozycja = 8;
                    }
                }
            }
            //rysowanie nierozgniecionych mrowek
            if (!mrowy[i].hit) {
                g.drawImage(mrowy[i].mrowki, mrowy[i].tx, null);
            }

        }
        //jesli czas sie nie skonczyl
        if (i != 0) {
            repaint();
        }

    }
}
