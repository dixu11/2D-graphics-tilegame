package tilegame.tiles;

import java.util.ArrayList;
import java.util.List;

public class TileManager {

    private List<Tile> tiles;

    public TileManager() {
        tiles = buildTiles();
    }



    public List<Tile> buildTiles() {
        List<Tile> tiles = new ArrayList<>();
        tiles.add(new GrassTile());
        tiles.add(new RockTile());
        tiles.add(new DirtTile());
        tiles.add(new WaterTile());
        return tiles;
    }

    public Tile getTileBySymbol(char symbol) {
        for (Tile tile : tiles) {
            if (tile.getSymbol() == symbol) {
                return tile;
            }
        }
        return getEmptyTile();
    }



    public Tile getEmptyTile() {
        return getTileBySymbol(Tile.EMPTY_SYMBOL);
    }

    public Tile getDefaultTile() {
        return getTileBySymbol(Tile.DEFAULT_TILE_SYMBOL);
    }

}
// maybe will need mediator
