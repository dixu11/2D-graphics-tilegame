package tilegame.states;

import tilegame.Game;
import tilegame.entities.EntityManager;
import tilegame.factories.Settings;
import tilegame.gfx.GameCamera;
import tilegame.input.KeyManager;
import tilegame.mediators.Mediator;
import tilegame.tiles.Tile;
import tilegame.tiles.TileManager;
import tilegame.utils.Utils;
import tilegame.worlds.World;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public class GameState extends State {

    private TileManager tileManager;
    private EntityManager entityManager;

    private Mediator mediator;  //handles Entity - Tile interaction
    private World world;    //handles Entity - Tile rendering

    private Settings settings;
    KeyManager keyManager;

    private boolean gameIsOver;
    private LocalTime endGameTime;


    public GameState(Settings settings, KeyManager keyManager) {
        this.settings = settings;
        this.keyManager = keyManager;
        determineStartingLvl();
        buildGameComponents();
    }

    public void buildGameComponents() {
        keyManager.releaseAllKeys();
        entityManager = new EntityManager(keyManager);
        tileManager = new TileManager();
        world = new World(entityManager, tileManager, settings);


        mediator = new Mediator(entityManager, world, settings);

        entityManager.init(mediator); //    sending Mediator // change to setmediator TODO
        world.setMediator(mediator);    // sending Mediator
    }

    public void determineStartingLvl() {
        int choosenNumber = chooseLvl();
        settings.setWorldNumber(choosenNumber);
    }


    public int chooseLvl() { // let you set world at the star of the game
        int numberOfLvls = settings.getNumberOfLvls();
        int chosenNumber;
        do {
            chosenNumber = -1;
            String input = JOptionPane.showInputDialog(
                    String.format("Choose world number " +
                            "Available worlds: 1 - %d", numberOfLvls));

            if (input == null) {
                JOptionPane.showMessageDialog(null, "Have a nice day!");
                System.exit(0);
            }

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You have to write number!");
                continue;
            }
            if (Utils.onlyNumbers(input)) {
                chosenNumber = Utils.parseInt(input);
            } else {
                JOptionPane.showMessageDialog(null, "Only numbers are accepted!");
            }
        } while (chosenNumber < 1 || chosenNumber > numberOfLvls);
        return chosenNumber;
    }

    @Override
    public void tick() {
        if (keyManager.isSpace()) {
            keyManager.releaseSpaceFor(0.5); // otherwise it spams this method over and over
            keyManager.releaseAllKeys();
            restartWorld();
        }

        if (mediator.areAllGemsActive() && !gameIsOver) {
            changeWorld();
        }
        world.tick();
        entityManager.tick();


        if (gameIsOver) {
            mediator.reducePlayerBy(1);
            terminateGame();
        }
    }

    @Override
    public void render(Graphics graphics, GameCamera gameCamera) {
        world.render(graphics, gameCamera);
        entityManager.render(graphics, gameCamera);
        if (gameIsOver) {
            endGameAnimation(gameCamera);
        }
    }

    public void changeWorld() {
        nextWorld();
    }

    private void restartWorld() {
        int answer = JOptionPane.showConfirmDialog(null, "Do you really want to restart actual level?");
        if (answer == 0) {
            buildGameComponents();
        }
    }

    private void nextWorld() {
        if (settings.setNextWolrdNumber()) {
            JOptionPane.showMessageDialog(null,
                    String.format("You won in %d steps!",
                            mediator.getStepCount()));

            JOptionPane.showMessageDialog(null,
                    String.format("Loading world %d ...",
                            settings.getWorldNumber()));
            buildGameComponents();
        } else {
            setEndGame();
        }
    }

    private void setEndGame() {
        if (!gameIsOver) {
            endGameTime = LocalTime.now();
            gameIsOver = true;
        }
    }

    private void terminateGame() {
        if (endGameTime.until(LocalTime.now(), ChronoUnit.SECONDS) > 0.8) {
            JOptionPane.showMessageDialog(null, "CONGRATULATIONS!!!\n You cleared the game!");
            System.exit(0);
        }

    }

    private void endGameAnimation(GameCamera gameCamera) {
        gameCamera.earthQuake();

    }


    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
