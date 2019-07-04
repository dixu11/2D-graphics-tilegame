package tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.SECONDS;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    private boolean up, down, left, right;
    private boolean space;

    private LocalTime spacePressedTime;
    private double spaceCooldownSec;


    public KeyManager() {
        keys = new boolean[256];
        spacePressedTime = LocalTime.now();
    }


    public void tick() {
        up = (keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]);
        down = (keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]);
        left = (keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]);
        right = (keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]);

        if (isAfterCooldown() && keys[KeyEvent.VK_SPACE]) {
            spacePressedTime = LocalTime.now();
            space = keys[KeyEvent.VK_SPACE];
            spaceCooldownSec = 0;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }


    public boolean isAfterCooldown() {
        return spacePressedTime.until(LocalTime.now(), SECONDS) > spaceCooldownSec;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void releaseAllKeys() {
        keys = new boolean[256];
    }

    public void releaseSpaceFor(double seconds) {
        space = false;
        spaceCooldownSec = seconds;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isSpace() {
        return space;
    }

    public void setSpace(boolean space) {
        this.space = space;
    }
}
