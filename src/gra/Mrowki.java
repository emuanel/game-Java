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
 * klasa odpowiadajaca za modelowanie mrowki
 * @author jfili
 */
public class Mrowki {
    /** polozenie x mrowki*/
    int x;
    /** polozenie y mrowki*/
    int y;
    /** polozenie x docelowe mrowki*/
    int celx;
    /** polozenie y docelowe mrowki*/
    int cely;
    /** obraz mrowki*/
    Image mrowki;
    /** zmienna przechowujaca lokalizacje oraz obrot mrowki*/
    AffineTransform tx = null;
    /** zmienna odpowiadajaca za to czy mrowka byla juz trafiona*/
    boolean hit;
    /** zmienna odpowiadajaca za pozyce mrowki-jak jest obrocona*/
    int pozycja;
    /**
     * konstruktor mrowki
     */
    public Mrowki() {
        hit = false;
        mrowki = new ImageIcon("images/ant1.png").getImage();
        Random r = new Random();
        int los = r.nextInt(4);
        if (los == 0) {
            y = r.nextInt(Main.gameHeight);
            x = 0 - mrowki.getWidth(null);
            celx = r.nextInt(Main.gameWidth);
            cely = r.nextInt(Main.gameHeight);
        }
        if (los == 1) {
            y = r.nextInt(Main.gameHeight);
            x = Main.gameWidth;
            celx = r.nextInt(Main.gameWidth);
            cely = r.nextInt(Main.gameHeight);
        }
        if (los == 2) {
            y = Main.gameHeight ;
            x = r.nextInt(Main.gameHeight);
            celx = r.nextInt(Main.gameWidth);
            cely = r.nextInt(Main.gameHeight);
        } else {
            x = r.nextInt(Main.gameWidth);
            y = 0 - mrowki.getHeight(null);
            cely = r.nextInt(Main.gameWidth);
            celx = r.nextInt(Main.gameHeight);
        }
    }
    /**
     * sprawdzenie czy uzytkownik trafil w mrowke
     * @param x x w ktorym kliknal 
     * @param y y w ktorym kliknal 
     */
    boolean containsPoint(int x, int y) {
        tx.rotate(Math.toRadians(0));
        //pozycja=1 czyli bez obrotu mrowki(w naturalnym polozeniu)
        if (pozycja == 1) {
            if (x >= this.x && x < this.x + mrowki.getWidth(null)) {
                if (y >= this.y && y < this.y + mrowki.getHeight(null)) {
                    return true;
                }
            }
        }
        //pozycja=2 obrot o 45stopni
        if (pozycja == 2) {
            if (x >= this.x - mrowki.getHeight(null) * Math.sqrt(2) / 2 && x < this.x + mrowki.getWidth(null) * Math.sqrt(2) / 2) {
                if (y >= this.y && y < this.y + (2 * mrowki.getWidth(null) * Math.sqrt(2) / 2)) {
                    return true;
                }
            }
        }
        //pozycja=3 obrot o 90stopni
        if (pozycja == 3) {
            if (x >= this.x - mrowki.getHeight(null) && x < this.x) {
                if (y >= this.y && y < this.y + mrowki.getWidth(null)) {
                    return true;
                }
            }
        }
        //pozycja=4 obrot o 135stopni
        if (pozycja == 4) {
            if (x <= this.x && x > this.x - (2 * mrowki.getWidth(null) * Math.sqrt(2) / 2)) {
                if (y >= this.y - mrowki.getHeight(null) * Math.sqrt(2) / 2 && y < this.y + mrowki.getWidth(null) * Math.sqrt(2) / 2) {
                    return true;
                }
            }
        }
        //pozycja=5 obrot o 180stopni
        if (pozycja == 5) {
            if (x <= this.x && x > this.x - mrowki.getWidth(null)) {
                if (y <= this.y && y > this.y - mrowki.getHeight(null)) {
                    return true;
                }
            }
        }
        //pozycja=6 obrot o 225stopni
        if (pozycja == 6) {
            if (x >= this.x - mrowki.getHeight(null) * Math.sqrt(2) / 2 && x < this.x + mrowki.getWidth(null) * Math.sqrt(2) / 2) {
                if (y <= this.y && y > this.y - (2 * mrowki.getWidth(null) * Math.sqrt(2) / 2)) {
                    return true;
                }
            }
        }
        //pozycja=7 obrot o 270stopni
        if (pozycja == 7) {
            if (x >= this.x && x < this.x + mrowki.getWidth(null)) {
                if (y <= this.y && y > this.y - mrowki.getHeight(null)) {
                    return true;
                }
            }
        }
        //pozycja=8 obrot o 315stopni
        if (pozycja == 8) {
            if (x >= this.x && x < this.x + (2 * mrowki.getWidth(null) * Math.sqrt(2) / 2)) {
                if (y <= this.y + mrowki.getHeight(null) * Math.sqrt(2) / 2 && y >= this.y - mrowki.getWidth(null) * Math.sqrt(2) / 2) {
                    return true;
                }
            }
        }
        return false;

    }
}
