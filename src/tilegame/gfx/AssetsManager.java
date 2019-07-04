package tilegame.gfx;

import tilegame.wrappers.Coords;
import tilegame.wrappers.Size;

import java.awt.image.BufferedImage;

public class AssetsManager {

    private static final int WIDTH = 32, HEIGHT = 32;

    public  static BufferedImage dirt, grass, rock, water;  //TILES
    public static BufferedImage player,gem, box;    //ENTITIES
    public static BufferedImage empty;      //OTHERS

    public static void init() {
        final Size DEFAULT_SIZE = new Size(WIDTH, HEIGHT);

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/tanksSheet.png"));

        player = sheet.crop(
                new Coords(WIDTH*14,0),
                DEFAULT_SIZE);

        dirt = sheet.crop(
                new Coords(WIDTH*4,HEIGHT*3),
                DEFAULT_SIZE);

        grass = sheet.crop(
                new Coords(WIDTH*5,HEIGHT*3),
                DEFAULT_SIZE);

        rock = sheet.crop(
                new Coords(WIDTH*10,HEIGHT),
                DEFAULT_SIZE);

        water = sheet.crop(
                new Coords(WIDTH*6,HEIGHT*3),
                DEFAULT_SIZE);


        gem = sheet.crop(
                new Coords(0, HEIGHT*2),
                DEFAULT_SIZE);

        box = sheet.crop(
                new Coords(WIDTH*17, HEIGHT*4),
                DEFAULT_SIZE);

        empty = sheet.crop(
                new Coords(0,0),
                new Size(1,1));


    }
}
