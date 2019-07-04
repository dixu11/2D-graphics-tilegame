package tilegame.gfx;

import tilegame.wrappers.Coords;
import tilegame.wrappers.Size;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sheet;


    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }


    public BufferedImage crop(Coords coords, Size size) {
        return sheet.getSubimage(coords.getXInt(), coords.getYInt(), size.getWidth(), size.getHeight());
    }
}
