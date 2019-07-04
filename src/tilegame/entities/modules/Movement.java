package tilegame.entities.modules;

import tilegame.entities.creatures.MovableEntity;
import tilegame.mediators.Mediator;
import tilegame.wrappers.Coords;

import java.awt.*;

public class Movement {     // Class created to avoid calculating movement in thisEntity class

    private double speed;
    private int streanght;
    private double xMove, yMove;
    private MovableEntity thisEntity;
    private Rectangle bounds;
    private Mediator mediator;


    public Movement(double speed, MovableEntity movableEntity, int streanght) {
        this.speed = speed;
        this.streanght = streanght;
        setOwner(movableEntity);
    }

    public void setOwner(MovableEntity owner) {
        thisEntity = owner;
        bounds = thisEntity.getBounds();
        this.mediator = thisEntity.getMediator();
        // maybe i could use strattegy pattern or something so i can executeMove this to the constructor
    }

    public boolean move(Side side) {
        calculateInput(side);
        return executeMove();
    }

    public void calculateInput(Side side) {
        yMove = 0;
        xMove = 0;

        switch (side) {
            case UP:
                thisEntity.setFacingSide(Side.UP);
                yMove = -speed;
                break;
            case DOWN:
                thisEntity.setFacingSide(Side.DOWN);
                yMove = speed;
                break;
            case LEFT:
                thisEntity.setFacingSide(Side.LEFT);
                xMove = -speed;
                break;
            case RIGHT:
                thisEntity.setFacingSide(Side.RIGHT);
                xMove = speed;
                break;
        }
    }

    public boolean executeMove() {
        boolean moved = true;
        if (thisEntity.comunicateColisions(new Coords(xMove, 0),streanght).isEmpty()) {
            moved = moveX();    //should be executed if there is colision
        }
        if (thisEntity.comunicateColisions(new Coords(0, yMove),streanght).isEmpty()) {
            moved = moveY();
        }
        return moved;
    }
//  20 + 2 + 16 + 32 / 64  = Tile 1

    public boolean moveX() {
        boolean moved = false;
        Coords coords = thisEntity.getCoords();

        if (xMove > 0) {    // moving right
            double tempX = coords.getX() + xMove + bounds.x + bounds.width;
            if (!isCollision(new Coords(tempX, coords.getY() + bounds.y)) &&
                    !isCollision(new Coords(tempX, coords.getY() + bounds.y + bounds.height))) {
                coords.addToX(xMove);
                moved = true;
            }

        } else if (xMove < 0) {     //moving left
            double tempX = coords.getX() + xMove + bounds.x;
            if (!isCollision(new Coords(tempX, coords.getY() + bounds.y)) &&
                    !isCollision(new Coords(tempX, coords.getY() + bounds.y + bounds.height))) {
                coords.addToX(xMove);
                moved = true;
            }
        }
        return moved;
    }

    public boolean moveY() {
        boolean moved = false;
        Coords coords = thisEntity.getCoords();

        if (yMove < 0) {     // moving up
            double tempY = coords.getYInt() + yMove + bounds.y;
            if (!isCollision(new Coords(coords.getX() + bounds.x, tempY)) &&
                    !isCollision(new Coords(coords.getX() + bounds.x + bounds.width, tempY))) {

                coords.addToY(yMove);
                moved = true;
            }
        } else if (yMove > 0) {     // moving down
            double tempY = coords.getYInt() + yMove + bounds.y + bounds.height;
            if (!isCollision(new Coords(coords.getX() + bounds.x, tempY)) &&
                    !isCollision(new Coords(coords.getX() + bounds.x + bounds.width, tempY))) {

                coords.addToY(yMove);
                moved = true;
            }
        }
        return moved;
    }

    public boolean isCollision(Coords pixelCoords) {
        return mediator.isTileSolid(pixelCoords);
    }


    public enum Side {
        UP(0), LEFT(1), DOWN(2), RIGHT(3);

        int id;

        Side(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static Side getSideById(int id) {
            for (Side side : Side.values()) {
                if (side.getId() == id) {
                    return side;
                }
            }
            return LEFT;
        }

        public static Side getNext(Side side) {
            int id = side.getId();
            if (id + 1 < Side.values().length) {
                return getSideById(id + 1);
            } else {
               return getSideById(0);
            }
        }
    }


    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getxMove() {
        return xMove;
    }

    public void setXMove(double xMove) {
        this.xMove = xMove;
    }

    public double getyMove() {
        return yMove;
    }

    public void setYMove(double yMove) {
        this.yMove = yMove;
    }

}
