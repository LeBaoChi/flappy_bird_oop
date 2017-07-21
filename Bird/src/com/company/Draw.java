package com.company;

import javax.sound.sampled.*;
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
    Item item = new Item();
    int speed = 3;
    int x1 = 600;
    int x2 = 600 + (600 + 70) / 2;
    int count = 0;
    int eatItem = 1;
    public int life = 0;

    public void init() {
        setFocusable(true);
        setBackground(Color.yellow);
        setLayout(null);

        bgr.initBG();
        pipe1.initPipe(x1);
        pipe2.initPipe(x2);
        bird.initB();
        item.init();

        thread.start();

        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                bird.jump();
            }

        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bird.jump();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        Clip point_clip=null;
        File point_sound = new File("sfx_point.wav"); //you could also get the sound file with an URL
        try {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            bgr.draw(g2d);
            g.drawString("life : " + life, 20,60);
            pipe1.draw(g2d);
            pipe2.draw(g2d);
            bird.draw(g2d);
            if (bird.xBird + bird.sizeX == pipe1.x || bird.xBird + bird.sizeX == pipe2.x) {
                AudioInputStream point_audio = AudioSystem.getAudioInputStream(point_sound);
                DataLine.Info info_point = new DataLine.Info(Clip.class, point_audio.getFormat());
                point_clip = (Clip)AudioSystem.getLine(info_point);
                point_clip.open(point_audio);
                bgr.score++;
                point_clip.start();
//                clip.close();
            }
            //ve o item
//            if (bgr.score == 1 && eatItem == 1) {
//                item.draw(g2d);
//            }
            //ve chim
//            if (eatItem == 0) {
//                bgr.drawB(g2d);
//            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        // Get a sound clip resource.


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
            Clip hit_clip=null;
            File hit_sound = new File("sfx_hit.wav"); //you could also get the sound file with an URL
            AudioInputStream hit_audio = null;
            try {
                hit_audio = AudioSystem.getAudioInputStream(hit_sound);
                DataLine.Info info_hit = new DataLine.Info(Clip.class, hit_audio.getFormat());
                hit_clip = (Clip)AudioSystem.getLine(info_hit);
                hit_clip.open(hit_audio);
                bgr.score++;
                hit_clip.start();
                return true;
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

//    boolean CheckItem() {
//        Rectangle birdRect = new Rectangle(bird.xBird, bird.yBird, bird.sizeX, bird.sizeY);
//        Rectangle itemRec = new Rectangle(item.x, item.y, item.size, item.size);
//        if (birdRect.intersects(itemRec)) return true;
//        return false;
//    }

    Thread thread = new Thread() {
        public void run() {
            while (true) {
                bgr.update();
                pipe1.update();
                pipe2.update();
                bird.move();
//                bird.autoFit();

                if (checkCollision() == true  ) {
                    break;
                }
//                if (CheckItem() == true) {
//                    eatItem = 0;
//                }
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
