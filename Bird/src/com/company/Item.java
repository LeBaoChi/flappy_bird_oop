package com.company;

import javax.swing.*;
import java.awt.*;

public class  Item {
    Image imgItem;

    public int x = 700;
    public int y = 400;
    public int size = 35;
    public int speed = 3;

    public void init() {
        imgItem = new ImageIcon(getClass().getResource("/image/item.png")).getImage();
    }

    public void draw(Graphics2D g) {
        g.drawImage(imgItem, x, y, size, size, null);
        x = x - speed;
    }
}
