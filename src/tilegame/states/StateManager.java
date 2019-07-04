package tilegame.states;

import tilegame.factories.Settings;
import tilegame.gfx.GameCamera;
import tilegame.input.KeyManager;


import java.awt.*;

public class StateManager {

    private State currentState;

    private static State gameState;
    private static State menuState;

    private KeyManager keyManager;
    private GameCamera gameCamera;
    private Settings settings;


    public StateManager(KeyManager keyManager, GameCamera gameCamera, Settings settings) {
        this.keyManager = keyManager;
        this.gameCamera = gameCamera;
        this.settings = settings;

        gameState = buildGameState();
        menuState = buildMenuState();
        currentState = gameState;
    }

    private State buildGameState() {

        //BUILD COMPONENTS MEDIATORS

        //SET STATES
        gameState = new GameState(settings,keyManager);
        return gameState;
    }

    private State buildMenuState() {
        return  new MenuState();
    }


    public void tick() {
        currentState.tick();
    }

    public void render(Graphics graphics) {
        currentState.render(graphics,gameCamera);
    }



    public void setState(State state) {
        currentState = state;
    }

    public State getCurrentState() {
        return currentState;
    }


}
