package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Pipe {
    private int space = 300;
    private int yUp,  yDown;
    private int hUp, hDown;
    private int x, w;
    private int speed = 3;
    private Image pipeUp, pipeDown;

    private String level = "Easy";

    int random() {
        Random rd = new Random();
        int h = rd.nextInt(400);
        if (h < 40) h = 40;
        else if (h > 600-space-20) h = 600-space-20;
        return h;
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

    public void draw(Graphics2D g) {
        g.drawImage(pipeUp, x, yUp, w, hUp, null);
        g.drawImage(pipeDown, x, yDown, w, hDown, null);
    }

    public void levelDiffcult(){
        level="Diffcult";
        speed=6;
    }
    public void levelEasy(){
        level="Easy";
        speed=3;
    }

    public void increSpped(int increment){
        speed+=increment;
    }

    public String getLevel() {
        return level;
    }
}
