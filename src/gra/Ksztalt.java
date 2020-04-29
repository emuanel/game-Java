package gra;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
/**
 * klasa modelujaca ksztalty
 * @author jfili
 */
public class Ksztalt {
    /** zdjecie ksztaltu*/
    Image cien;
    /** nazwa ksztaltu*/
    String nazwa;
    /** tablica z dostepnymi zdjeciami ksztaltow*/
    static Image cienie[] = {new ImageIcon("images/ninja.png").getImage(), new ImageIcon("images/wolf.png").getImage(), new ImageIcon("images/dog.png").getImage(),new ImageIcon("images/samolot.png").getImage(),
        new ImageIcon("images/jaszczurka.png").getImage(),new ImageIcon("images/swieczka.png").getImage(),new ImageIcon("images/nurek.png").getImage(),new ImageIcon("images/czarownica.png").getImage(),
    new ImageIcon("images/ryba.png").getImage(),new ImageIcon("images/papuga.png").getImage(),new ImageIcon("images/krolik.png").getImage(),new ImageIcon("images/kot.png").getImage(),new ImageIcon("images/gitara.png").getImage()};
    static String nazwy[] = {"Ninja","Wilk","Pies","Samolot","Jaszczurka","Swieczka","Nurek","Czarownica","Ryba","Papuga","Krolik","Kot","Gitara"};
    /** numer ksztaltu*/
    static int numer=0;
    /**
    * konstruktor ksztaltow
    */
    public Ksztalt() {
        cien=cienie[numer];
        nazwa=nazwy[numer];
        numer++;
        if(numer>=nazwy.length)
            numer=0;
    }
}
