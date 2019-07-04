package tilegame.states;

import tilegame.Game;
import tilegame.gfx.GameCamera;

import java.awt.*;

public abstract class State {

    public State() {
    }

    public abstract void tick();

    public abstract void render(Graphics graphics, GameCamera gameCamera);




}
