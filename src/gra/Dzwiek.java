/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gra;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

    /**
     * klasa odpowiadajaca za otwarzanie dzwiekow i sprawdzanie czy wybrano poprawne zwierze
     * @author jfili
     */
public class Dzwiek {
    /** tablca dzwiekow dla poziomu latwego*/
    String[] tab1 = {"sounds/frog.wav", "sounds/horse.wav", "sounds/lion.wav", "sounds/sheep.wav", "sounds/cat.wav",
        "sounds/cow.wav", "sounds/dog.wav", "sounds/elephant.wav"};
    /** tablca dzwiekow dla poziomu sredniego*/
    String[] tab2 = {"sounds/dog.wav", "sounds/lion.wav", "sounds/kogut.wav", "sounds/elephant.wav", "sounds/malpa.wav",
        "sounds/bird.wav"};
    /** tablca dzwiekow dla poziomu trudnego*/
    String[] tab3 = {"sounds/dog.wav", "sounds/cow.wav", "sounds/malpa.wav", "sounds/lion.wav", "sounds/bird.wav",
        "sounds/kogut.wav", "sounds/pszczoly.wav", "sounds/pig.wav", "sounds/mouse.wav", "sounds/penguin.wav",
        "sounds/zebra.wav", "sounds/indyk.wav", "sounds/kaczka.wav", "sounds/sowa.wav"};
    int dzwiek;
    int dzwiek_poprzedni = 0;
    /**
    * metoda odpowiadajaca za wybranie oraz odtworzenie dzwieku z tablicy
    * @param numerTablicy zaleznie od tego parametru wybiera tablice z ktorej maja byc odtwarzane dzwieki 
    * @throws IOException problem z plikiem
    * @throws UnsupportedAudioFileException problem z plikiem
    * @throws LineUnavailableException problem z plikiem
    */
    public void playDzwiek(int numerTablicy) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Random r;
        String filePath;
        AudioInputStream audioInputStream;
        Clip clip;
        //zaleznie od poziomu trudnosci inna tablica
        switch (numerTablicy) {
            case 1:
                //losowanie dzwieku do odtworzenia 
                r = new Random();
                do {
                    dzwiek = r.nextInt(tab1.length);
                } while (dzwiek == dzwiek_poprzedni);
                dzwiek_poprzedni = dzwiek;
                //pobranie dzwieku z tablicy i odtworzenie go
                filePath = tab1[dzwiek];
                audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                break;
            case 2:
                //losowanie dzwieku do odtworzenia 
                r = new Random();
                do {
                    dzwiek = r.nextInt(tab2.length);
                } while (dzwiek == dzwiek_poprzedni);
                dzwiek_poprzedni = dzwiek;
                //pobranie dzwieku z tablicy i odtworzenie go
                filePath = tab2[dzwiek];
                audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                break;
            case 3:
                //losowanie dzwieku do odtworzenia 
                r = new Random();
                do {
                    dzwiek = r.nextInt(tab3.length);
                } while (dzwiek == dzwiek_poprzedni);
                dzwiek_poprzedni = dzwiek;
                //pobranie dzwieku z tablicy i odtworzenie go
                filePath = tab3[dzwiek];
                audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                break;
        }
    }
    /**
    * metoda odpowiadajaca za sprawdzanie poprawnosci wyboru zwierzecia oraz odtworzenie dzwieku dzwieku goog albo bad
    * @param wybor wybrane zwierze przez uzytkownika
    * @return true gdy poprawna odpowiedz
    * @return false gdy zla odpowiedz 
    * @throws IOException problem z plikiem
    * @throws UnsupportedAudioFileException problem z plikiem
    * @throws LineUnavailableException problem z plikiem
    */
    public boolean sprawdzPoprawnosc(int wybor) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //jesli wybor odpowiedni odtworzenie dzwieku good
        if (dzwiek == wybor) {
            String filePath = "sounds/good.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            return true;
        } else { //jesli wybor nieodpowiedni odtworzenie dzwieku bad
            String filePath = "sounds/bad.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            return false;
        }
    }
}
