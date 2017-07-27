package com.company;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bird {
    Image imgBird;
    int xBird;
    int yBird;
    public int sizeX = 35;
    public int sizeY = 35;
    double speed = 0.75;
    int v = 0;
    SoundPlayer wingSound=new SoundPlayer();

    public void initB() {
        xBird = 100;
        yBird = 30;
        imgBird = new ImageIcon(getClass().getResource("/image/bird.png")).getImage();

    }

    public void draw(Graphics2D g) {
        g.drawImage(imgBird, xBird, yBird, sizeX, sizeY, null);
    }

    void move() {
            speed = speed + .3 ;
            yBird = (int) (yBird + speed);
    }

    void jump() {
            speed = -5;
            if(wingSound.isSound()){
                wingSound.play(new File("sfx_wing.wav"));
            }

    }

    public void offSoundB(){
        wingSound.offSound();
    }
    public void onSoundB(){
        wingSound.onSound();
    }
}
