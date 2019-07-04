package tilegame;

import tilegame.factories.GameFactory;
import tilegame.factories.Settings;
import tilegame.gfx.AssetsManager;

public class Launcher {

    public static void main(String[] args) {
        AssetsManager.init();   // loading assets in static library

        Settings startingSettings = Settings    // creating default setting object
                .createDefaultSettings();

        GameFactory gameFactory = new GameFactory(startingSettings);    // game factory creates game objects and sends them settings

        Game game = gameFactory.getGame();
        game.start();
    }
}

//TO TEST ENDING CHOOSE WORLD 7 (WORLD FOR TESTS)

// wykonano w około: 32h


// BUGS NOT FIXED:
// moving speeds up for a sec after finishing lvl


