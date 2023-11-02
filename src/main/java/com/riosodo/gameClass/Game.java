package com.riosodo.gameClass;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {

    public static JFrame jFrame;
    private BufferedImage image;
    private Thread thread;
    private boolean isRunning;
    public final static int WIDTH = 240;
    public final static int HEIGHT = 180;
    public static int scale = 3;
    public static double expectedFPS = 60;


    public Game(){
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH*scale,HEIGHT*scale));
        initFrame();
        requestFocus();

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    }
    public void initFrame(){
        jFrame = new JFrame("game");
        jFrame.add(this);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setVisible(true);
        jFrame.pack();
    }

    public synchronized void start(){
        thread = new Thread(this);
        isRunning = true;
        thread.start();

    }
    public synchronized void stop(){
        isRunning = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void run() {
        long lastTime = System.nanoTime();
        double ns = 1000000000 / expectedFPS;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();
        while (isRunning){
            long now = System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                render();
                frames++;
                delta--;
            }
            if(System.currentTimeMillis() - timer >= 1000){
                System.out.println("FPS: "+frames);
                frames = 0;
                timer+=1000;
            }
        }
        stop();
    }
    public void tick(){

    }
    public void render(){

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}