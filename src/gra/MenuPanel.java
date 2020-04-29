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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Menu glowne gry
 *
 * @author jfili
 */
public class MenuPanel extends JPanel {

    /**
     * zmiena odpowiadajaca za numer wybranej gry
     */
    int numerGry;
    /**
     * zmianna odpowiadajaca za zdjecie tla
     */
    private Image img;

    /**
     * konstruktor panelu odpowiadajacego za menu
     *
     * @param img tlo
     */
    public MenuPanel(Image img) {
        //ustawienia panelu 
        setLocation(0, 0);
        setLayout(null);

        //wczytanie tla
        this.img = img;
        //ustawienie rozmiaru panelu
        Dimension size = new Dimension(Main.gameWidth, Main.gameHeight);
        setPreferredSize(size);

        setLayout(null);
        //dodanie przyciskow ktore odpowiadaja za jedna z czterech gier
        JButton sluchanieDzwiekow = new JButton("Arka Noego");
        JButton rozgniatanieMrowek = new JButton("Formikarium");
        JButton lapanieOwocow = new JButton("Owocowy sad");
        JButton rozpoznawanieKsztaltow = new JButton("Rozpoznawanie Kształtów");

        JButton zakoncz = new JButton("Zakoncz");
        //obsluga przycisko
        sluchanieDzwiekow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //wybrano gre nr1 
                numerGry = 1;
                //ukrywanie obecnego panelu
                setVisible(false);
                //dodanie do ramki obiektu odpowiadajacego za wybor poziomu trudnosci-TrudnosciPanel
                Main.okno.getContentPane().add(new TrudnosciPanel(new ImageIcon("images/tlo2.jpg").getImage(), numerGry));
            }
        }
        );
        rozgniatanieMrowek.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //wybrano gre nr2
                numerGry = 2;
                //ukrywanie obecnego panelu
                setVisible(false);
                //dodanie do ramki obiektu odpowiadajacego za wybor poziomu trudnosci-TrudnosciPanel
                Main.okno.getContentPane().add(new TrudnosciPanel(new ImageIcon("images/tlo2.jpg").getImage(), numerGry));
            }
        });
        lapanieOwocow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //wybrano gre nr3
                numerGry = 3;
                //dodanie do ramki obiektu odpowiadajacego za wybor poziomu trudnosci-TrudnosciPanel
                TrudnosciPanel panel = new TrudnosciPanel(new ImageIcon("images/tlo2.jpg").getImage(), numerGry);
                Main.okno.getContentPane().add(panel);
                //ukrywanie obecnego panelu
                setVisible(false);
            }
        });
        rozpoznawanieKsztaltow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //wybrano gre nr4
                numerGry = 4;
                //ukrywanie obecnego panelu
                setVisible(false);
                //dodanie do ramki obiektu odpowiadajacego za wybor poziomu trudnosci-TrudnosciPanel
                KsztaltyPanel ksztalty = new KsztaltyPanel(new ImageIcon("images/tlo7.jpg").getImage());
                Main.okno.add(ksztalty);

            }
        });
        zakoncz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //zamkniecie programu
                System.exit(1);
            }
        });
        //ustawienie lokolizacji oraz rozmiaru przyciskow
        sluchanieDzwiekow.setBounds(620, 215, 400, 100);
        rozgniatanieMrowek.setBounds(620, 310, 400, 100);
        lapanieOwocow.setBounds(620, 410, 400, 100);
        rozpoznawanieKsztaltow.setBounds(620, 510, 400, 100);

        zakoncz.setBounds(620, 615, 400, 100);

        //ustawienie czcionek
        Font bArial = new Font("Forte", Font.BOLD, 26);
        sluchanieDzwiekow.setFont(bArial);
        rozgniatanieMrowek.setFont(bArial);
        lapanieOwocow.setFont(bArial);
        rozpoznawanieKsztaltow.setFont(bArial);

        zakoncz.setFont(bArial);

        //ustawienie tla przyciskow
        sluchanieDzwiekow.setOpaque(false);
        rozgniatanieMrowek.setOpaque(false);
        lapanieOwocow.setOpaque(false);
        rozpoznawanieKsztaltow.setOpaque(false);

        zakoncz.setOpaque(false);

        sluchanieDzwiekow.setContentAreaFilled(false);
        rozgniatanieMrowek.setContentAreaFilled(false);
        lapanieOwocow.setContentAreaFilled(false);
        rozpoznawanieKsztaltow.setContentAreaFilled(false);

        zakoncz.setContentAreaFilled(false);

        sluchanieDzwiekow.setBorderPainted(false);
        rozgniatanieMrowek.setBorderPainted(false);
        lapanieOwocow.setBorderPainted(false);
        rozpoznawanieKsztaltow.setBorderPainted(false);

        zakoncz.setBorderPainted(false);

        //dodanie przyciskow do panelu
        add(sluchanieDzwiekow);
        add(rozgniatanieMrowek);
        add(lapanieOwocow);
        add(rozpoznawanieKsztaltow);
        add(zakoncz);

        //stworzenie oraz dodanie labela zawierajacego nazwe gry "1vs4"
        JLabel naglowek = new JLabel("1 vs 4");
        naglowek.setBounds(710, 55, 250, 150);
        naglowek.setFont(new Font("Algerian", Font.ITALIC, 80));
        add(naglowek);
    }

    /**
     * metoda odpowiadajaca za ryosowanie tla
     *
     * @param g graphics
     */
    protected void paintComponent(Graphics g) {
        //rysowanie tla
        g.drawImage(img, 0, 0, null);
    }
}
