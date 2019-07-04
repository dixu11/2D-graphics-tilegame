package tilegame.factories;

import tilegame.Game;
import tilegame.display.Display;
import tilegame.gfx.GameCamera;
import tilegame.input.KeyManager;
import tilegame.states.StateManager;
import tilegame.wrappers.Coords;

// simple class that creates essential game objects
public class GameFactory {

    private Settings settings;
    private Game game;


    public GameFactory(Settings startingSettings) {
        this.settings = startingSettings;
        init();
    }


    private void init() {
        Display display = new Display(
                settings.getGameTitle(),
                settings.getWindowSize()
        );
        GameCamera gameCamera = new GameCamera(
                new Coords(0,0), settings);

        KeyManager keyManager = new KeyManager();
        StateManager stateManager = new StateManager(keyManager, gameCamera, settings);


        game = new Game(display, keyManager, stateManager);

    }


    public Game getGame() {
        return game;
    }

}
