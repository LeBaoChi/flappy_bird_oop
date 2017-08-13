package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;


public class Draw extends JPanel {
    Background bgr = new Background();
    Pipe pipe1 = new Pipe();
    Pipe pipe2 = new Pipe();
    Bird bird = new Bird();
    private int x1 = 600; // toa do ong 1
    private int x2 = 600 + (600 + 70) / 2 +1; // toa do ong 2
    private SoundPlayer pointSound = new SoundPlayer();
    private SoundPlayer hitSound = new SoundPlayer();

    private String help="";

    public void init() {

        setBackground(Color.yellow);
        setLayout(null);

        bgr.initBG();
        pipe1.initPipe(x1);
        pipe2.initPipe(x2);
        bird.initB();

        setFocusable(true);
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if(key == e.VK_M){
                    if(hitSound.isSound()==true){
                        pointSound.offSound();
                        System.out.println("Tat am thanh");
                    }else{
                        pointSound.onSound();
                        System.out.println("Bat am thanh");
                    }

                }else if(key == e.VK_H){
                    System.out.println("Help");
                    help="Ấn phím 'M' để tắt âm thanh \n" +
                            "Ấn phím 'V' để bật âm thanh \n" +
                            "Ấn phím 'D' để chọn chế độ khó \n" +
                            "Ấn phím 'E' để chọn chế độ dễ";
                    repaint();
                }else if(key == e.VK_D){
                    System.out.println("Che Do Kho");
                    pipe1.levelDiffcult();
                    pipe2.levelDiffcult();
                }else if(key == e.VK_E){
                    System.out.println("Che do De");
                    pipe1.levelEasy();
                    pipe2.levelEasy();
                }else if(thread.getState()==Thread.State.NEW){
                    thread.start();
                }else {
                    bird.jump();
                    help="";
                }
            }

        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(thread.getState()==Thread.State.NEW){
                    thread.start();
                    help="";
                }else {
                    bird.jump();
                    help="";
                }
            }
        });

    }

    // ham viet ra help
    public void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        bgr.draw(g2d);
        g.drawString("Level : " + pipe1.getLevel(), 20, 60);
        drawString(g,help,200,200);
        pipe1.draw(g2d);
        pipe2.draw(g2d);
        bird.draw(g2d);
        if (bird.getxBird() + bird.getSizeX() == pipe1.getX() || bird.getxBird() + bird.getSizeX() == pipe2.getX()) {

            if(pointSound.isSound()){
                pointSound.play(new File("sfx_point.wav"));
            }
            if(bgr.score % 50 == 0 && bgr.score>1){
                pipe1.increSpped(1);
                pipe2.increSpped(1);
            }
            bgr.score++;
        }

    }

    public boolean checkCollision() {
        Rectangle birdRect = new Rectangle(bird.getxBird(), bird.getyBird(), bird.getSizeX(), bird.getSizeY());
        Rectangle pipe1RectUp = new Rectangle(pipe1.getX(), pipe1.getyDown(), pipe1.getW(),
                pipe1.gethUp());

        Rectangle pipe1RectDown = new Rectangle(pipe1.getX(), pipe1.getyDown(),
                pipe1.getW(), pipe1.gethDown());

        Rectangle pipe2RectDown = new Rectangle(pipe2.getX(), pipe2.getyUp(), pipe2.getW(),
                pipe2.gethUp());

        Rectangle pipe2RectUp = new Rectangle(pipe2.getX(), pipe2.getyDown(),
                pipe2.getW(), pipe2.gethDown());

        Rectangle landRect = new Rectangle(0, 600, 600, 100);

        if (birdRect.intersects(pipe1RectUp)
                || birdRect.intersects(pipe1RectDown)
                || birdRect.intersects(pipe2RectUp)
                || birdRect.intersects(pipe2RectDown)
                || birdRect.intersects(landRect)) {
//                bgr.score++;

                if(hitSound.isSound()){
                    hitSound.play(new File("sfx_hit.wav"));
                }

                return true;
        }
        return false;
    }

    Thread thread = new Thread() {
        public void run() {
            while (true) {
                bgr.update();
                pipe1.update();
                pipe2.update();
                bird.move();
                if (checkCollision() == true  ) {
                    addKeyListener(new KeyAdapter(){
                        @Override
                        public void keyPressed(KeyEvent e) {
                            int key = e.getKeyCode();
                            if(key == e.VK_ENTER){
                                GUI view = new GUI();
                                view.initView();
                                view.setVisible(true);
                            }
                        }

                    });
                    break;
                }
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }

    };

}
