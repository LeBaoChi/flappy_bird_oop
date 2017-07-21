package com.company;

import javax.swing.*;


public class GUI extends JFrame{
    int width = 600;
    int height = 700;
    Pipe pipe1,pipe2;

    public void initView(){
        setResizable(false);
        setTitle("Flappy Bird");
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Draw draw = new Draw();
        draw.init();
        add(draw);

    }
}
