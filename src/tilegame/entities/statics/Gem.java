package tilegame.entities.statics;

import tilegame.entities.Entity;
import tilegame.gfx.AssetsManager;
import tilegame.gfx.GameCamera;
import tilegame.mediators.Mediator;
import tilegame.tiles.Tile;
import tilegame.wrappers.Coords;

import java.util.List;

import java.awt.*;

public class Gem extends StaticEntity {

    private boolean active;

    public Gem(Coords coords, Mediator mediator) {
        super(coords, Entity.DEFAULT_SIZE, mediator, Entity.GEM_SYMBOL);
    }

    @Override
    public Rectangle prepareBounds() {   // small point in the center
        Rectangle bounds = new Rectangle();
        bounds.x = Tile.SIZE.getWidth()/2 -2;
        bounds.y =  Tile.SIZE.getWidth()/2 -2;
        bounds.width = 5;
        bounds.height = 5;
        return bounds;
    }

    @Override
    public void tick() {
        active = isUnderBox();
    }


    @Override
    public void render(Graphics graphics, GameCamera camera) {
        graphics.drawImage(
                AssetsManager.gem,
                coords.getXMinOffsetInt(camera),
                coords.getYMinOffsetInt(camera),
                size.getWidth(),
                size.getHeight(),
                null);

        //DRAW BOUNDS
       graphics.setColor(Color.RED);
        graphics.fillRect(
                coords.getXMinOffsetInt(camera) + bounds.x,
                coords.getYMinOffsetInt(camera) + bounds.y,
                bounds.width,
                bounds.height
        );
    }


    public boolean isUnderBox() {
        List<Entity> intersectedEntities = mediator.isColision(this, new Coords(0, 0));
        for (Entity intersectedEntity : intersectedEntities) {
            if (intersectedEntity.getSymbol() == Entity.BOX_SYMBOL) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isFlat() {
        return true;
    }


    public boolean isActive() {
        return active;
    }
}
