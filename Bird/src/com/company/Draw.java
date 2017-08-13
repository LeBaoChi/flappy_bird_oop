package com.company;

//import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class Draw extends JPanel {
    Background bgr = new Background();
    Pipe pipe1 = new Pipe();
    Pipe pipe2 = new Pipe();
    Bird bird = new Bird();
    int x1 = 600;
    int x2 = 600 + (600 + 70) / 2 +1;
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
                }else
                if(key == e.VK_D){
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
    void drawString(Graphics g, String text, int x, int y) {
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
        System.out.print("bird ");
        System.out.println(bird.xBird + bird.sizeX);
        System.out.print("pipe1 ");
        System.out.println(pipe1.x);
        System.out.print("pipe2 ");
        System.out.println(pipe2.x);
        System.out.println();
        if (bird.xBird + bird.sizeX == pipe1.x || bird.xBird + bird.sizeX == pipe2.x) {

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

    boolean checkCollision() {
        Rectangle birdRect = new Rectangle(bird.xBird, bird.yBird, bird.sizeX, bird.sizeY);
        Rectangle pipe1RectUp = new Rectangle(pipe1.x, pipe1.yUp, pipe1.w,
                pipe1.hUp);

        Rectangle pipe1RectDown = new Rectangle(pipe1.x, pipe1.yDown,
                pipe1.w, pipe1.hDown);

        Rectangle pipe2RectDown = new Rectangle(pipe2.x, pipe2.yUp, pipe2.w,
                pipe2.hUp);

        Rectangle pipe2RectUp = new Rectangle(pipe2.x, pipe2.yDown,
                pipe2.w, pipe2.hDown);

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
