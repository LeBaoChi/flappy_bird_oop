package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Bird {
    private Image imgBird;
    private int xBird;
    private int yBird;
    private int sizeX = 35; // kich thuoc chim theo chieu X (chieu doc)
    private int sizeY = 35; // kich thuoc chim theo chieu Y (chieu ngang)
    private double speed = 0.75;

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getxBird() {

        return xBird;
    }

    public int getyBird() {
        return yBird;
    }

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
            speed = speed + 0.3 ;
            yBird = (int) (yBird + speed);
    }

    void jump() {
            speed = -5;
            if(wingSound.isSound()){
                wingSound.play(new File("sfx_wing.wav"));
            }

    }


}
