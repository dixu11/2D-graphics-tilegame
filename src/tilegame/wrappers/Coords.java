package tilegame.wrappers;

import tilegame.gfx.GameCamera;

import java.util.Objects;

public class Coords {
    private double x, y;


    public void addCoords(Coords coords) {
        addToX(coords.getXInt());
        addToY(coords.getYInt());
    }

    public void addToY(double toAdd) {
        y += toAdd;
    }

    public void addToX(double toAdd) {
        x += toAdd;
    }

    public void submitFromY(double toSubmit) {
        y -= toSubmit;
    }

    public void submitFromX(double toSubmit) {
        x -= toSubmit;
    }

    public Coords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void scaleBySize(Size size) {
        x = getXSacaledBySize(size);
        y = getYSacaledBySize(size);
    }

    public int getXSacaledBySize(Size size) {
        return (int) (x / size.getWidth());
    }

    public int getYSacaledBySize(Size size) {
        return (int) (y / size.getHeight());
    }

    public int getYInt() {
        return (int) y;
    }

    public int getXInt() {
        return (int)x;
    }


    public void minOffset(GameCamera camera) {
        x = getXMinOffset(camera);
        y = getYMinOffset(camera);
    }

    public double getYMinOffset(GameCamera gameCamera) {
        return y - gameCamera.getOffset().getY();
    }

    public double getXMinOffset(GameCamera gameCamera) {
        return x - gameCamera.getOffset().getX();
    }

    public int getYMinOffsetInt(GameCamera gameCamera) {
        return (int) (y - gameCamera.getOffset().getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coords coords = (Coords) o;
        return Double.compare(coords.x, x) == 0 &&
                Double.compare(coords.y, y) == 0;
    }

    public int getXMinOffsetInt(GameCamera gameCamera) {
        return (int)(x - gameCamera.getOffset().getX());
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
