package tilegame.tiles;

import tilegame.gfx.AssetsManager;
import tilegame.gfx.ImageLoader;

import java.awt.image.BufferedImage;

public class EmptyTile extends Tile {
    public EmptyTile() {
        super(AssetsManager.empty, EMPTY_SYMBOL);
    }
}
