package tilegame.entities.creatures;

import tilegame.entities.Entity;
import tilegame.entities.modules.Movement;
import tilegame.gfx.AssetsManager;
import tilegame.gfx.GameCamera;
import tilegame.input.KeyManager;
import tilegame.mediators.Mediator;
import tilegame.tiles.Tile;
import tilegame.wrappers.Coords;
import tilegame.wrappers.Size;

import java.awt.*;

public class Player extends MovableEntity {

    private KeyManager keyManager;
    private Coords playerTileCoords;    // in tiles not pixels
    private int stepCount;

    public Player(Coords startingCoords,KeyManager keyManager, Mediator mediator) {
        super(startingCoords,
                new Size(Entity.DEFAULT_SIZE.getWidth(), Entity.DEFAULT_SIZE.getHeight()), //TODO method in utility
                mediator,
                Entity.PLAYER_SYMBOL);

        this.keyManager = keyManager;
    }

    @Override
    public Rectangle prepareBounds() {
        Rectangle bounds = new Rectangle();
        bounds.x = 10;
        bounds.y = 8;
        bounds.width = 35;
        bounds.height = 37;
        return bounds;
    }


    @Override
    public void tick() {
        super.tick();
        calculateInput();   // checks for what kays were pressed and execute it
        stepsCounterTick();

//        System.out.printf("Coords: X - %.1f Y - %.1f\n", coords.getX(), coords.getY());
    }

    @Override
    public void render(Graphics graphics, GameCamera camera) {
        camera.centerOnEntity(this);

        graphics.drawImage(
                AssetsManager.player,
                coords.getXMinOffsetInt(camera),
                coords.getYMinOffsetInt(camera),
                size.getWidth(),
                size.getHeight(),
                null);


        //DRAW BOUNDS
      /*  graphics.setColor(Color.RED);
        graphics.fillRect(
                coords.getXMinOffsetInt(camera) + bounds.x,
                coords.getYMinOffsetInt(camera) + bounds.y,
                bounds.width,
                bounds.height
        );*/
    }

    private void stepsCounterTick() {
        Coords actualCoordsInTiles = new Coords(
                coords.getX() + Tile.SIZE.getWidth()/2,
                coords.getY()+ Tile.SIZE.getHeight()/2);
        actualCoordsInTiles.scaleBySize(Tile.SIZE);
        if (playerTileCoords == null) {
            playerTileCoords = actualCoordsInTiles;
            return;
        } else if (!playerTileCoords.toString().equals(actualCoordsInTiles.toString())) {
            playerTileCoords = actualCoordsInTiles;
            stepCount++;
        }
    }

    private void calculateInput() {
        movement.setXMove(0);
        movement.setYMove(0);

        if (keyManager.isUp()) {
            movement.move(Movement.Side.UP);
        }
        if (keyManager.isDown()) {
            movement.move(Movement.Side.DOWN);
        }
        if (keyManager.isLeft()) {
            movement.move(Movement.Side.LEFT);
        }
        if (keyManager.isRight()) {
            movement.move(Movement.Side.RIGHT);

        }

    }

    public int getStepCount() {
        return stepCount;
    }
}
