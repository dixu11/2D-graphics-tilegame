package tilegame.tiles;

import tilegame.gfx.AssetsManager;

public class RockTile extends Tile {

    public RockTile() {
        super(AssetsManager.rock,Tile.ROCK_SYMBOL);
    }


    @Override
    public boolean isSolid() {
        return true;
    }
}
