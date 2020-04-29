package gra;

import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 * Prosta gra 1 vs4
 * @author jfili
 */
public class Main extends JPanel {
    /** Szerokosc pola graficznego gry*/
    static int gameWidth = 1280;
    /** Wysokosc pola graficznego gry*/
    static int gameHeight = 1024;
    /** Ramka gry*/
    static Okno okno;

    /**
     * Metoda uruchamiania gry. Najpierw pobierane sa parametry ekranu i po
     * ustaleniu rozmiaru gry (1280/1024) obliczany jest punkt (x,y) gornego,
     * lewego naroznika panelu gry tak, aby pole gry bylo wysrodkowane na
     * ekranie.
     * @param args da
     */
    public static void main(String[] args) {
        //pobieranie rozmiaru ekranu
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

        //oblicz wspolrzednee naroznika tak, aby pole gry bylo wysrodkowane
        int xCenter = (screenWidth - gameWidth) / 2;
        int yCenter = 0;

        //utworzenie obiektu klasy Okno- konstruktor wywoluje dalsza akcje
        okno = new Okno(gameWidth, gameHeight, xCenter, yCenter);

        okno.setVisible(true);
    }//koniec main()
}//koniec class Main
