package com.company;

import javax.swing.*;
import java.awt.*;


public class Background {
    Image imgunnderground;
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
        imgunnderground = new ImageIcon(getClass().getResource("/image/unnderground.png")).getImage();
        imgBG = new ImageIcon(getClass().getResource("/image/bg.png")).getImage();
        //
        imgBird = new ImageIcon(getClass().getResource("/image/bird.png")).getImage();
    }

    public void update() {
        xImgBG = xImgBG - speedImgBG;
        if (xImgBG <= -600) {
            xImgBG = 0;
        }
//        updateB();
    }

    public void updateB() {
        len = len % 2;
        switch (len % 2) {
            case 1:
                y = y - 5;
                if (y == 150) len++;
                break;
            case 0:
                y = y + 5;
                if (y == 450) len++;
                break;
        }
    }

    public void drawB(Graphics2D g) {
        g.drawImage(imgBird, x, y, 35, 35, null);
    }

    public void draw(Graphics2D g) {
        g.drawImage(imgBG, 0, 0, 600, 600, null);
        g.drawImage(imgunnderground, xImgBG, yImgBG, 600, 100, null);
        g.drawImage(imgunnderground, xImgBG + 600, yImgBG, 600, 100, null);
        g.drawString("Score: " + score, 20, 20);

    }
}
