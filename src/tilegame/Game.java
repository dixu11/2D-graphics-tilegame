package tilegame;

import tilegame.display.Display;
import tilegame.input.KeyManager;
import tilegame.states.StateManager;
import tilegame.wrappers.Size;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {     //main class

    //FRAME
    private  Display display;
    private Size windowSize;

    //THREAD
    private Thread thread;
    private boolean running;

    //GRAPHICS
    private BufferStrategy bufferStrategy;
    private Graphics graphics;


    //MANAGERS
    private StateManager stateManager;
    private KeyManager keyManager;


    public Game(Display display, KeyManager keyManager, StateManager stateManager) {

        windowSize = display.getSize();

        this.display = display;
        this.keyManager = keyManager;
        this.stateManager =stateManager;

    }


    private void init() {
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().setVisible(true);
    }

    @Override
    public void run() {     //GAME LOOP
        init();
        int fps = 60;
        double timePerTick = 1_000_000_000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {

            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta>= 1) {

                tick();
                render();

                ticks++;
                delta--;
            }

            if (timer>=1_000_000_000) {
//                System.out.println("Ticks and frames: " + ticks);
                ticks = 0;
                timer = 0;
            }

        }

        stop();
    }

    private void tick() {
        keyManager.tick();

        if (stateManager.getCurrentState() != null) {
            stateManager.tick();
        }
    }

    private void render() {
        bufferStrategy = display.getCanvas().getBufferStrategy();         //manages buffers - virtual screens - prepared before display

        if (bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        graphics = bufferStrategy.getDrawGraphics();        // graphics - our main painting tool

        graphics.clearRect(0,0,windowSize.getWidth(),windowSize.getHeight());         //clear screen


        // DRAW HERE
        if (stateManager.getCurrentState() != null) {
            stateManager.render(graphics);
        }


        // FINALISING
        bufferStrategy.show();
        graphics.dispose();

    }

    public synchronized void start() {
        if (running) {
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();     // invoke run() method
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public KeyManager getKeyManager() {
        return keyManager;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void setKeyManager(KeyManager keyManager) {
        this.keyManager = keyManager;
    }
}
