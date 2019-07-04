package tilegame.entities.statics;

import tilegame.entities.Entity;
import tilegame.mediators.Mediator;
import tilegame.wrappers.Coords;
import tilegame.wrappers.Size;

public abstract class StaticEntity extends Entity {

    public StaticEntity(Coords coords, Size size, Mediator mediator, char symbol) {
        super(coords, size, mediator, symbol);
    }



}
