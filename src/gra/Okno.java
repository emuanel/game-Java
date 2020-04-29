package gra;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Okno glowne gry demonstracyjnej-frame.
 * @author jfili
 */
public class Okno extends JFrame {

    /**
     * konstruktor ramki gry
     * @param width szerokoĹ›Ä‡ okna
     * @param height wysokoĹ›Ä‡ okna
     * @param x pozycja x lewego gĂłrnego naroĹĽnika okna
     * @param y pozycja y lewego gĂłrnego naroĹĽnika okna
     */
    public Okno(int width, int height, int x, int y) {
        super();
        setSize(width, height);
        setLocation(x, y);
        setResizable(false);
        setUndecorated(true);
        //dodanie nowego obiektu - panelu zawierajacego menu glowne gry
        getContentPane().add(new MenuPanel(new ImageIcon("images/tlo1.jpg").getImage()));
        pack();
    }
}
