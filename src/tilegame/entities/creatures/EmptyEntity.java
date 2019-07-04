package tilegame.entities.creatures;

import tilegame.entities.Entity;
import tilegame.gfx.GameCamera;
import tilegame.mediators.Mediator;
import tilegame.wrappers.Coords;
import tilegame.wrappers.Size;

import java.awt.*;

public class EmptyEntity extends Entity {

    public EmptyEntity(Mediator mediator) {
        super(new Coords(0,0), new Size(0,0), mediator, Entity.NO_ENTITY_SYMBOL);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics, GameCamera camera) {

    }
}
