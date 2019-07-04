package tilegame.tiles;

//One block of a map

import tilegame.wrappers.Coords;
import tilegame.wrappers.Size;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static final char
            DEFAULT_TILE_SYMBOL = 'G',
            GRASS_SYMBOL = 'G',
            ROCK_SYMBOL = 'R',
            DIRT_SYMBOL = 'D',
            WATER_SYMBOL = 'W',
            EMPTY_SYMBOL = '_';

    public static final Size SIZE =
            new Size(55, 55);


    private static int nextId = 0;

    protected final int id;
    protected final char symbol;

    protected BufferedImage texture;


    public Tile(BufferedImage texture, char symbol) {
        this.texture = texture;
        this.symbol = symbol;
        id = nextId;
        nextId++;
    }


    public void tick() {

    }

    public void render(Graphics graphics, Coords coords) {

        graphics.drawImage(
                texture,
                coords.getXInt(),
                coords.getYInt(),
                SIZE.getWidth(),
                SIZE.getHeight(),
                null);
    }

    public boolean isSolid() {
        return false;
    }


    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getId() {
        return id;
    }

}
