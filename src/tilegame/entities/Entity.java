package tilegame.entities;

import tilegame.entities.modules.Movement;
import tilegame.gfx.GameCamera;
import tilegame.mediators.Mediator;
import tilegame.wrappers.Coords;
import tilegame.wrappers.Size;

import java.awt.*;
import java.util.List;


public abstract class Entity {      // Everything in a game that is not a Tile

    public static final char PLAYER_SYMBOL = 'p';
    public static final char BOX_SYMBOL = 'x';
    public static final char GEM_SYMBOL = 'o';
    public static final char NO_ENTITY_SYMBOL = '_';

    protected static final Size DEFAULT_SIZE = new Size(55, 55);
    protected static final Movement.Side DEFAULT_FACING_SIDE = Movement.Side.UP;


    protected char symbol;
    protected Coords coords;    // IN PIXELS
    protected Size size;
    protected Rectangle bounds;
    protected Movement.Side facingSide;

    protected Mediator mediator;


    public Entity(Coords coords, Size size, Mediator mediator, char symbol) {
        this.coords = coords;
        this.size = size;
        this.mediator = mediator;
        this.symbol = symbol;
        this.facingSide = DEFAULT_FACING_SIDE;

        bounds = prepareBounds();
    }

    public Rectangle prepareBounds(){
        return new Rectangle();
    }


    public abstract void tick();

    public abstract void render(Graphics graphics, GameCamera camera);


    public List<Entity> comunicateColisions(Coords offset, int streanght) {       //OFFSET LETS YOU CHECK POTENTIAL COLLISION BOX AFTER MOVEMENT
        List<Entity> pushedEntities =  mediator.isColision(this, offset);
        if (pushedEntities.size()>streanght) {
            return pushedEntities;
        }
        for (Entity pushedEntity : pushedEntities) {
            pushedEntity.reactToColision(this);
        }
        return pushedEntities;
    }

    public  void reactToColision(Entity entity) {

    }



    //RETURNS AREA THAT IS SOLID
    public Rectangle getColisionBounds(Coords offset) {

        return new Rectangle((coords.getXInt() + bounds.x + offset.getXInt()),
                (coords.getYInt() + bounds.y + offset.getYInt()),
                bounds.width,
                bounds.height);

    }

    public boolean isEmpty() {
        return size.getHeight() > 0;
    }

    public boolean isFlat(){
        return false;
    }

    public Coords getCoords() {
        return coords;
    }

    public Size getSize() {
        return size;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Movement.Side getFacingSide() {
        return facingSide;
    }

    public void setFacingSide(Movement.Side facingSide) {
        this.facingSide = facingSide;
    }

    public boolean isSolid() {
        return true;
    }


}
