package com.company;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bird {
    Clip wing_clip=null,swooshing_clip=null;
    File wing_sound = new File("sfx_wing.wav"); //you could also get the sound file with an URL
    File swooshings_sound = new File("sfx_swooshing.wav"); //you could also get the sound file with an URL
    Image imgBird;
    int xBird;
    int yBird;
    public int sizeX = 35;
    public int sizeY = 35;
    double speed = 0.75;
    int v = 0;
    private SoundPlayer flapSound, bupSound, getMoneySound;

    public void initB() {
        xBird = 100;
        yBird = 30;
        imgBird = new ImageIcon(getClass().getResource("/image/bird.png")).getImage();

    }

    public void draw(Graphics2D g) {
        g.drawImage(imgBird, xBird, yBird, sizeX, sizeY, null);
    }

    public void drawBirdItem(Graphics2D g){
        g.drawImage(imgBird, xBird, yBird, sizeX, sizeY, null);
    }

    public void autoFit() {
        if (yBird < 0) yBird = 0;
        else if (yBird > 600) yBird = 600;
    }

    void move() {
//        AudioInputStream swooshing_audio = null;
//        try {
//            swooshing_audio = AudioSystem.getAudioInputStream(swooshings_sound);
//            DataLine.Info info_swooshing = new DataLine.Info(Clip.class, swooshing_audio.getFormat());
//            swooshing_clip = (Clip)AudioSystem.getLine(info_swooshing);
//            swooshing_clip.open(swooshing_audio);
//            swooshing_clip.start();
            speed = speed + .3 ;
            yBird = (int) (yBird + speed);
//        } catch (UnsupportedAudioFileException e) {
//        e.printStackTrace();
//    } catch (IOException e) {
//        e.printStackTrace();
//    } catch (LineUnavailableException e)     {
//        e.printStackTrace();
//        }


    }

    void jump() {
        AudioInputStream wing_audio = null;
        try {
            wing_audio = AudioSystem.getAudioInputStream(wing_sound);
            DataLine.Info info_wing = new DataLine.Info(Clip.class, wing_audio.getFormat());
            wing_clip = (Clip)AudioSystem.getLine(info_wing);
            wing_clip.open(wing_audio);
            wing_clip.start();
            speed = -5;
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e)     {
            e.printStackTrace();
        }


    }

}
