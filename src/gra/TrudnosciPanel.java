/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gra;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel z wyborem poziomu
 * @author jfili
 */
class TrudnosciPanel extends JPanel {
    /** zmianna odpowiadajaca za zdjecie tla*/
    private Image img; 
    
     /**
     * konstruktor panelu odpowiadajacego wybor poziomu trudnosci
     * @param tlo tlo panelu
     * @param nrGry wybrana gra 
     */
    public TrudnosciPanel(Image tlo, int nrGry) {
        //wczytanie tla
        this.img = tlo;
        //ustawienie rozmiaru panelu
        Dimension size = new Dimension(Main.gameWidth, Main.gameHeight);
        setPreferredSize(size);
        //ustawienia panelu
        setLocation(0, 0);
        setLayout(null);
        //dodanie przyciskow do panelu
        JButton latwy = new JButton("Łałtwy");
        JButton sredni = new JButton("Sredni");
        JButton trudny = new JButton("Trudny");
        JButton powrot = new JButton("Powrot");
        
        //stworzenie obiektu przechowujacego parametry gry
        Status dane = new Status();
        
        //dodanie obslugi przyciskow
        latwy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //wybranie poziomu latwego
                dane.poziom = 1;
                //zaleznie od wyboru gry w menu utworzenie odpowiedniego panelu
                switch (nrGry) {
                    case 1:
                        //ukrycie obecnego panelu
                        setVisible(false);
                        //utworzenie oraz dodanie panelu odpowiadajacego za gre nr1-ArkaNoego
                        LatweDzwiekiPanel dzwieki = null;
                        try {
                            dzwieki = new LatweDzwiekiPanel(new ImageIcon("images/tlo4.png").getImage(), dane);
                        } catch (Exception ex) {
                        }
                        Main.okno.getContentPane().add(dzwieki);
                        break;
                    case 2:
                        //ukrycie obecnego panelu
                        setVisible(false);
                        //utworzenie oraz dodanie panelu odpowiadajacego za gre nr2-Formikarium
                        MrowkiPanel mrowki = null;
                        try {
                            mrowki = new MrowkiPanel(new ImageIcon("images/tlo5.jpg").getImage(), dane);
                        } catch (Exception ex) {
                        }
                        Main.okno.getContentPane().add(mrowki);
                        break;
                    case 3:
                         //utworzenie oraz dodanie panelu odpowiadajacego za gre nr3-OwocowySad
                        OwocePanel owoce = null;
                        try {
                            owoce = new OwocePanel(new ImageIcon("images/tlo6.jpg").getImage(), dane);
                        } catch (Exception ex) {
                        }
                        Main.okno.getContentPane().add(owoce);
                        //ukrycie obecnego panelu
                        setVisible(false);
                        break;
                }
            }
        });
        sredni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //wybranie poziomu sredniego
                dane.poziom = 2;
                //zaleznie od wyboru gry w menu utworzenie odpowiedniego panelu
                switch (nrGry) {
                    case 1:
                        //ukrycie obecnego panelu
                        setVisible(false);
                        //utworzenie oraz dodanie panelu odpowiadajacego za gre nr1-ArkaNoego
                        SrednieDzwiekiPanel dzwieki = null;
                        try {
                            dzwieki = new SrednieDzwiekiPanel(new ImageIcon("images/arka.jpg").getImage(), dane);
                        } catch (Exception ex) {
                        } 
                        Main.okno.getContentPane().add(dzwieki);
                        break;
                    case 2:
                        //ukrycie obecnego panelu
                        setVisible(false);
                        //utworzenie oraz dodanie panelu odpowiadajacego za gre nr2-Formikarium
                        MrowkiPanel mrowki = null;
                        try {
                            mrowki = new MrowkiPanel(new ImageIcon("images/tlo5.jpg").getImage(), dane);
                        } catch (Exception ex) {
                        }
                        Main.okno.getContentPane().add(mrowki);
                        break;
                    case 3:
                        //utworzenie oraz dodanie panelu odpowiadajacego za gre nr3-OwocowySad
                        OwocePanel owoce = null;
                        try {
                            owoce = new OwocePanel(new ImageIcon("images/tlo6.jpg").getImage(), dane);
                        } catch (Exception ex) {
                        }
                        Main.okno.getContentPane().add(owoce);
                        //ukrycie obecnego panelu
                        setVisible(false);
                        break;

                }//koniec switch

            }
        });
        trudny.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //wybranie poziomu sredniego
                dane.poziom = 3;
                switch (nrGry) {
                    case 1:
                        //ukrycie obecnego panelu
                        setVisible(false);
                         //utworzenie oraz dodanie panelu odpowiadajacego za gre nr1-ArkaNoego
                        TrudneDzwiekiPanel dzwieki = null;
                        try {
                            dzwieki = new TrudneDzwiekiPanel(new ImageIcon("images/tlo9.jpg").getImage(), dane);
                        } catch (Exception ex) {  
                        } 
                        Main.okno.getContentPane().add(dzwieki);
                        break;
                    case 2:
                        //ukrycie obecnego panelu
                        setVisible(false);
                         //utworzenie oraz dodanie panelu odpowiadajacego za gre nr3-Formikarium
                        MrowkiPanel mrowki = null;
                        try {
                            mrowki = new MrowkiPanel(new ImageIcon("images/tlo5.jpg").getImage(), dane);
                        } catch (Exception ex) {
                        }
                        Main.okno.getContentPane().add(mrowki);
                        break;
                    case 3:
                        //utworzenie oraz dodanie panelu odpowiadajacego za gre nr3-OwocowySad
                        OwocePanel owoce = null;
                        try {
                            owoce = new OwocePanel(new ImageIcon("images/tlo6.jpg").getImage(), dane);
                        } catch (Exception ex) {
                        }
                        Main.okno.getContentPane().add(owoce);
                         //ukrycie obecnego panelu
                        setVisible(false);
                        break;

                }//koniec switch
            }
        });
        //obsluga przycisku powrotu
        powrot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //ukrycie obecnego panelu
                setVisible(false);
                //dodanie panelu menuglowne
                Main.okno.getContentPane().add(new MenuPanel(new ImageIcon("images/tlo1.jpg").getImage()));
            }
        });
        //ustawienia tla przyciskow
        latwy.setOpaque(false);
        sredni.setOpaque(false);
        trudny.setOpaque(false);
        powrot.setOpaque(false);
        //ustawienia tla przyciskow
        latwy.setContentAreaFilled(false);
        sredni.setContentAreaFilled(false);
        trudny.setContentAreaFilled(false);
        powrot.setContentAreaFilled(false);
        //ustawienia tla przyciskow
        latwy.setBorderPainted(false);
        sredni.setBorderPainted(false);
        trudny.setBorderPainted(false);
        powrot.setBorderPainted(false);
        //ustawienia czcionki tekstu
        Font bArial = new Font("Forte", Font.BOLD, 30);
        latwy.setFont(bArial);
        sredni.setFont(bArial);
        trudny.setFont(bArial);
        powrot.setFont(bArial);
        //dodanie przyciskow
        add(latwy);
        add(sredni);
        add(trudny);
        add(powrot);
        //ustawienie polozenia oraz rozmiaru przyciskow
        latwy.setBounds(620, 215, 400, 100);
        sredni.setBounds(620, 310, 400, 100);
        trudny.setBounds(620, 410, 400, 100);
        powrot.setBounds(620, 510, 400, 100);
        //stworzenie oraz dodanie labela zawierajacego nazwe gry "1vs4"
        JLabel naglowek = new JLabel("1 vs 4");
        naglowek.setBounds(710, 55, 250, 150);
        naglowek.setFont(new Font("Algerian", Font.ITALIC, 80));
        add(naglowek);
    }
 
     /**
     * rysowanie tla panelu
     * @param tlo tlo panelu
     */
    protected void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
