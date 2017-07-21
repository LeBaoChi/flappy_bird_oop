package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Pipe {
    public int space = 300;
    public int yUp,  yDown;
    public int hUp, hDown;
    public int x, w;
    public int speed = 3;
    public Image pipeUp, pipeDown;

    int random() {
        Random rd = new Random();
        int a = rd.nextInt(400);
        if (a < 40) a = 40;
        else if (a > 600-space-20) a = 600-space-20;
        return a;
    }

    void initPipe(int a) {
        pipeDown = new ImageIcon(getClass().getResource("/image/pipe_up.png")).getImage();
        pipeUp = new ImageIcon(getClass().getResource("/image/pipe_down.png")).getImage();
        x = a;
        w = 70;
        yUp = 0;
        hUp = random();
        yDown = hUp + space;
        hDown = 600 - (hUp + space);
    }

    public void update() {
        x = x - speed;
        if (x < -w) {
            x = 600;
            hUp = random();
            yDown = hUp + space;
            hDown = 600 - (hUp + space);
        }
    }

    void draw(Graphics2D g) {
        g.drawImage(pipeUp, x, yUp, w, hUp, null);
        g.drawImage(pipeDown, x, yDown, w, hDown, null);
    }

}
