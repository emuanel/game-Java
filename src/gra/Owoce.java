/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gra;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * klasa modelujaca owoce
 * @author jfili
 */
public class Owoce {
    /** numer owocu*/
    static int numer=0;
    /** lokalizacja x owocu*/
    int x;
    /** lokalizacja y owocu*/
    int y;
    /** zdjecie owocu*/
    Image owoc;
    /** tablica zawierajaca dostepne owoce*/
    Image owoce[] = {new ImageIcon("images/jablko.png").getImage(), new ImageIcon("images/czeresnie.png").getImage(), new ImageIcon("images/czeresnie2.png").getImage(),new ImageIcon("images/truskawka.png").getImage(),
        new ImageIcon("images/gruszka.png").getImage(), new ImageIcon("images/gruszka2.png").getImage(), new ImageIcon("images/jablko2.png").getImage(), new ImageIcon("images/sliwki.png").getImage(),
        new ImageIcon("images/winogrona.png").getImage(), new ImageIcon("images/winogrono2.png").getImage(), new ImageIcon("images/wisnie.png").getImage()};
    /** obrot owocu oraz jego lokalizacja*/
    AffineTransform tx = null;
    /** kat obrotu*/
    int kat = 0;
    /**
     * konstruktor owocow
     */
    Owoce() {
        owoc=owoce[numer];
        y = 0;
        Random r = new Random();
        //losowanie polozenia x owocu
        x = r.nextInt(Main.gameWidth - owoce[numer].getWidth(null));
        numer++;
        if(numer==11)
            numer=0;
    }
}
