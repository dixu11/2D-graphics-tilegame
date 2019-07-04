package tilegame.entities.creatures;

import tilegame.entities.Entity;
import tilegame.entities.modules.Movement;
import tilegame.mediators.Mediator;
import tilegame.wrappers.Coords;
import tilegame.wrappers.Size;

// ENTITY THAT MOVES HIMSELF
public abstract class MovableEntity extends Entity {

    private static int DEFAULT_HEALTH = 15;
    protected final static double DEFAULT_SPEED = 2;

    protected int health;
    protected Movement movement;



    public MovableEntity(Coords coords, Size size, Mediator mediator, char symbol) {
        super(coords, size, mediator,symbol);
        this.movement = buildMovement();
        health = DEFAULT_HEALTH;
    }

    protected Movement buildMovement() {
        return new Movement(DEFAULT_SPEED, this,1);
    }


    @Override
    public void tick() {
        movement.executeMove();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Movement getMovement() {
        return movement;
    }
}
