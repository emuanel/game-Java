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
import java.io.IOException;
import java.util.TimerTask;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Timer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Panel gry ArkaNoego poziom latwy
 * @author jfili
 */
public class LatweDzwiekiPanel extends JPanel {
    /** Tlo panelu*/
    private Image img;
    /** Wybrane zwierze*/
    int wcisnieteZwierze;
    /** Zegar*/
    Timer timer;
    /** Czas rowny na poczatku 60s*/
    int i = 60;
    /** Zmienna przechwytujaca status gry*/
    Status nowagra;
    /** Tablica zwierzac jako przyciski*/
    JButton zwierzeta[];
    /**
     * konstruktor panelu gry ArkaNoego poziom latwy
     * @param img tlo
     * @param status status z wybranym wczesniej poziomem
     * @throws IOException problem z plikiem
    * @throws UnsupportedAudioFileException problem z plikiem
    * @throws LineUnavailableException problem z plikiem
     */
    public LatweDzwiekiPanel(Image img, Status status) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        nowagra = status;
        Dzwiek nowaGra = new Dzwiek();

        this.img = img;
        Dimension size = new Dimension(Main.gameWidth, Main.gameHeight);
        setPreferredSize(size);
        setLayout(null);
        setVisible(true);

        zwierzeta = new JButton[8];
        for (int j = 0; j < zwierzeta.length; j++) {
            zwierzeta[j] = new JButton();
            zwierzeta[j].setOpaque(false);
            zwierzeta[j].setContentAreaFilled(false);
            zwierzeta[j].setBorderPainted(false);
        }

        zwierzeta[0].setBounds(27, 250, 300, 280);
        zwierzeta[1].setBounds(357, 220, 300, 330);
        zwierzeta[2].setBounds(687, 220, 300, 330);
        zwierzeta[3].setBounds(1037, 220, 300, 330);
        zwierzeta[4].setBounds(27, 580, 300, 330);
        zwierzeta[5].setBounds(357, 580, 300, 330);
        zwierzeta[6].setBounds(687, 580, 300, 330);
        zwierzeta[7].setBounds(1037, 580, 300, 330);

        JPanel informacje = new JPanel();
        JTextField czas = new JTextField();
        JTextField wynik = new JTextField("Wynik: " + status.punkty);
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
                if (i < 0) {
                    JPanel okienkoWynikowe = new JPanel();
                    JLabel poziom = null;
                    if (nowagra.poziom == 1) {
                        poziom = new JLabel("Poziom: łatwy");
                    }
                    if (nowagra.poziom == 2) {
                        poziom = new JLabel("Poziom: średni");
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

                    for (int j = 0; j < zwierzeta.length; j++) {
                        remove(zwierzeta[j]);
                    }
                    timer.cancel();
                }
            }
        }, 0, 1000);

        nowaGra.playDzwiek(1);

        zwierzeta[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wcisnieteZwierze = 0;
                try {
                    if (nowaGra.sprawdzPoprawnosc(wcisnieteZwierze)) {
                        nowagra.punkty++;
                        wynik.setText("Wynik: " + Integer.toString(nowagra.punkty));
                    }
                    Thread.sleep(500);
                    nowaGra.playDzwiek(1);
                } catch (Exception ex) {

                }

            }
        }
        );
        zwierzeta[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wcisnieteZwierze = 1;
                try {
                    if (nowaGra.sprawdzPoprawnosc(wcisnieteZwierze)) {
                        nowagra.punkty++;
                        wynik.setText("Wynik: " + Integer.toString(nowagra.punkty));
                    }
                    Thread.sleep(500);
                    nowaGra.playDzwiek(1);
                } catch (Exception ex) {

                }
            }
        }
        );
        zwierzeta[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wcisnieteZwierze = 2;
                try {
                    if (nowaGra.sprawdzPoprawnosc(wcisnieteZwierze)) {
                        nowagra.punkty++;
                        wynik.setText("Wynik: " + Integer.toString(nowagra.punkty));
                    }
                    Thread.sleep(500);
                    nowaGra.playDzwiek(1);
                } catch (Exception ex) {

                }
            }
        }
        );
        zwierzeta[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wcisnieteZwierze = 3;
                try {
                    if (nowaGra.sprawdzPoprawnosc(wcisnieteZwierze)) {
                        nowagra.punkty++;
                        wynik.setText("Wynik: " + Integer.toString(nowagra.punkty));
                    }
                    Thread.sleep(500);
                    nowaGra.playDzwiek(1);
                } catch (Exception ex) {

                }
            }
        }
        );
        zwierzeta[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wcisnieteZwierze = 4;
                try {
                    if (nowaGra.sprawdzPoprawnosc(wcisnieteZwierze)) {
                        nowagra.punkty++;
                        wynik.setText("Wynik: " + Integer.toString(nowagra.punkty));
                    }
                    Thread.sleep(500);
                    nowaGra.playDzwiek(1);
                } catch (Exception ex) {

                }
            }
        }
        );
        zwierzeta[5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wcisnieteZwierze = 5;
                try {
                    if (nowaGra.sprawdzPoprawnosc(wcisnieteZwierze)) {
                        nowagra.punkty++;
                        wynik.setText("Wynik: " + Integer.toString(nowagra.punkty));
                    }
                    Thread.sleep(500);
                    nowaGra.playDzwiek(1);
                } catch (Exception ex) {

                }
            }
        }
        );
        zwierzeta[6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wcisnieteZwierze = 6;
                try {
                    if (nowaGra.sprawdzPoprawnosc(wcisnieteZwierze)) {
                        nowagra.punkty++;
                        wynik.setText("Wynik: " + Integer.toString(nowagra.punkty));
                    }
                    Thread.sleep(500);
                    nowaGra.playDzwiek(1);
                } catch (Exception ex) {

                }
            }
        }
        );
        zwierzeta[7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wcisnieteZwierze = 7;
                try {
                    if (nowaGra.sprawdzPoprawnosc(wcisnieteZwierze)) {
                        nowagra.punkty++;
                        wynik.setText("Wynik: " + Integer.toString(nowagra.punkty));
                    }
                    Thread.sleep(500);
                    nowaGra.playDzwiek(1);
                } catch (Exception ex) {

                }
            }
        }
        );
        for (int j = 0; j < zwierzeta.length; j++) {
            add(zwierzeta[j]);
        }
    }
    /**
     * metoda odpowiadajaca za ryosowanie tla
     * @param g graphics
     */
    public void paintComponent(Graphics g) {
        //rysowanie tla
        g.drawImage(img, 0, 0, this);
    }

}
