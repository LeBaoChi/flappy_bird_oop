package com.company;

//import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class Draw extends JPanel {
    public boolean restart=false;
    Background bgr = new Background();
    Pipe pipe1 = new Pipe();
    Pipe pipe2 = new Pipe();
    Bird bird = new Bird();
    Item item = new Item();
    int speed = 3;
    int x1 = 600;
    int x2 = 600 + (600 + 70) / 2;
    int count = 0;
    int eatItem = 1;
    SoundPlayer pointSound = new SoundPlayer();
    SoundPlayer hitSound = new SoundPlayer();


    public int life = 0;

    public void init() {

        setBackground(Color.yellow);
        setLayout(null);

        bgr.initBG();
        pipe1.initPipe(x1);
        pipe2.initPipe(x2);
        bird.initB();
//        item.init();

        setFocusable(true);
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if(thread.getState()==Thread.State.NEW){
                    thread.start();
                }else bird.jump();
            }

        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(thread.getState()==Thread.State.NEW){
                    thread.start();
                }else bird.jump();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        bgr.draw(g2d);
        g.drawString("life : " + life, 20, 60);
        pipe1.draw(g2d);
        pipe2.draw(g2d);
        bird.draw(g2d);
        if (bird.xBird + bird.sizeX == pipe1.x || bird.xBird + bird.sizeX == pipe2.x) {
            pointSound.play(new File("sfx_point.wav"));
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

                hitSound.play(new File("sfx_hit.wav"));
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
