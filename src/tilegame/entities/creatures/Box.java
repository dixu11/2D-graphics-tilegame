package tilegame.entities.creatures;

import tilegame.entities.Entity;
import tilegame.entities.modules.Movement;
import tilegame.gfx.AssetsManager;
import tilegame.gfx.GameCamera;
import tilegame.mediators.Mediator;
import tilegame.wrappers.Coords;

import java.awt.*;

public class Box extends MovableEntity {


    public Box(Coords coords, Mediator mediator) {
        super(coords,
                Entity.DEFAULT_SIZE,
                mediator,
                Entity.BOX_SYMBOL);

        movement.setSpeed(1);
    }


    @Override
    protected Movement buildMovement() {
        return super.buildMovement();
    }

    @Override
    public Rectangle prepareBounds() {
        Rectangle bounds = new Rectangle();
        bounds.x = 10;
        bounds.y = 8;
        bounds.width = 37;
        bounds.height = 37;
        return bounds;
    }
//todo
    @Override
    public void reactToColision(Entity otherEntity) {
        if (otherEntity.getSymbol() == Entity.PLAYER_SYMBOL) {
            int boxesPushed = 0;
            for (Entity entity : mediator.getPlayerColisions()) {
                if (entity.getSymbol() == Entity.BOX_SYMBOL) {
                    boxesPushed++;
                }
                if (boxesPushed > 1) {
                    return;
                }
            }
            movement.move(otherEntity.getFacingSide());
        }
    }

    @Override
    public void tick() {

    }



    @Override
    public void render(Graphics graphics, GameCamera camera) {
        graphics.drawImage(
                AssetsManager.box,
                coords.getXMinOffsetInt(camera),
                coords.getYMinOffsetInt(camera),
                size.getWidth(),
                size.getHeight(),
                null);

        //DRAW BOUNDS
       /* graphics.setColor(Color.RED);
        graphics.fillRect(
                coords.getXMinOffsetInt(camera) + bounds.x,
                coords.getYMinOffsetInt(camera) + bounds.y,
                bounds.width,
                bounds.height
        );*/
    }


}
