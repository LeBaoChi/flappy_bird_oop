package com.company;

import javax.swing.*;


public class GUI extends JFrame{
    private int width = 600;
    private int height = 700;
    Draw draw = new Draw();

    public void initView(){
        setResizable(false);
        setTitle("Flappy Bird");
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        draw.init();
        add(draw);

    }
}
