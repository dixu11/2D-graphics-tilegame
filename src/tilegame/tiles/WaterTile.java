package tilegame.tiles;

import tilegame.gfx.AssetsManager;

public class WaterTile extends Tile {

    public WaterTile() {
        super(AssetsManager.water,Tile.WATER_SYMBOL);
    }


    public boolean isSolid() {
        return true;
    }
}
