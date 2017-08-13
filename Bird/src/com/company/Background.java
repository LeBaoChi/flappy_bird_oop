package com.company;

import javax.swing.*;
import java.awt.*;


public class Background {
    Image imgUnderground;
    Image imgBG;
    Image imgBird;
    public int xImgBG = 0;
    public int yImgBG = 600;
    int speedImgBG = 3;
    public int score = 0;
    public int x = 40;
    public int y = 200;
    int len = 0;

    public void initBG() {
        imgUnderground = new ImageIcon(getClass().getResource("/image/unnderground.png")).getImage();
        imgBG = new ImageIcon(getClass().getResource("/image/bg.png")).getImage();
        imgBird = new ImageIcon(getClass().getResource("/image/bird.png")).getImage();
    }

    public void update() {
        xImgBG = xImgBG - speedImgBG;
        if (xImgBG <= -600) {
            xImgBG = 0;
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(imgBG, 0, 0, 600, 600, null);
        g.drawImage(imgUnderground, xImgBG, yImgBG, 600, 100, null);
        g.drawImage(imgUnderground, xImgBG + 600, yImgBG, 600, 100, null);
        g.drawString("Score: " + score, 20, 20);

    }
}
