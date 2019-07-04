package tilegame.worlds;

import tilegame.entities.Entity;
import tilegame.entities.EntityManager;
import tilegame.factories.Settings;
import tilegame.gfx.GameCamera;
import tilegame.mediators.Mediator;
import tilegame.tiles.Tile;
import tilegame.tiles.TileManager;
import tilegame.utils.Utils;
import tilegame.wrappers.Coords;
import tilegame.wrappers.Size;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// handles game components rendering and ticks
public class World {


    private Size worldSize;
    private char[][] tiles;

    private TileManager tileManager;
    private Settings settings;
    private EntityManager entityManager;
    private Mediator mediator;


    public World(EntityManager entityManager, TileManager tileManager, Settings settings) {
        this.entityManager = entityManager;
        this.settings = settings;
        this.tileManager = tileManager;
        init();
    }


    public void init() {
        tiles = buildTiles();
    }

    public Map<Character, List<Coords>> readEntityStartingCoords() {
        String[] tokens = getMapAsStrings();
        Map<Character, List<Coords>> entityLocations = new HashMap<>();

        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                char entity = tokens[(x + y * worldSize.getWidth()) + 2].charAt(1);
                if (entity == Entity.NO_ENTITY_SYMBOL) {
                    continue;
                }

                if (entityLocations.get(entity) == null) {
                    entityLocations.put(entity, new ArrayList<>());
                }
                Coords entityCoords = new Coords(Utils.widthToTilePixels(x), Utils.heightToTilePixels(y));
                entityLocations.get(entity).add(entityCoords);
            }
        }
        return entityLocations;
    }

    public String[] getMapAsStrings() {
        String mapString = Utils.loadFileAsString(getWorldPath());
        return mapString.split("\\s+");
    }

    private char[][] buildTiles() {
        //LOADING FROM FILE
        String[] tokens = getMapAsStrings();

        //SETTING SIZE
        worldSize = new Size(Utils.parseInt(tokens[0]), Utils.parseInt(tokens[1]));
        settings.setWorldSize(worldSize);

        //NEW BOARD
        tiles = new char[worldSize.getHeight()][worldSize.getWidth()];

        //FILLING BOARD
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                tiles[y][x] = tokens[(x + y * worldSize.getWidth()) + 2].charAt(0);
            }
        }

        return tiles;
    }

    public void tick() {

    }

    public Tile getTileByPixelCoords(Coords pixelCoords) {
        pixelCoords.scaleBySize(Tile.SIZE);
        return getTile(pixelCoords);
    }

    public Tile getTile(Coords tileCoords) {
        //ARE YOU ON THE MAP?
        if (tileCoords.getX() < 0 || tileCoords.getY() < 0 || tileCoords.getX() >= worldSize.getWidth() || tileCoords.getY() >= worldSize.getHeight()) {
            return tileManager.getDefaultTile();
        }

        char symbol = tiles[tileCoords.getYInt()][tileCoords.getXInt()];
        return tileManager.getTileBySymbol(symbol);
    }

    public void render(Graphics graphics, GameCamera camera) {
        //OBJECTS SHORTCUTS
        Coords offset = camera.getOffset();
        Size windowSize = camera.getWindowSize();

        //DETERMINE ACTUAL SCREEN POSITION - to render only tiles that user see
        int xStart = Math.max(0, offset.getXInt() / Tile.SIZE.getWidth());     // dividing to get result in nr of tiles, not pixels
        int xEnd = Math.min(worldSize.getWidth(), (offset.getXInt() + windowSize.getWidth()) / Tile.SIZE.getWidth() + 1);
        int yStart = Math.max(0, offset.getYInt() / Tile.SIZE.getHeight());
        int yEnd = Math.min(worldSize.getHeight(), (offset.getYInt() + windowSize.getHeight()) / Tile.SIZE.getHeight() + 1);

        //DRAW TILES
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                Coords coords = new Coords(x, y);

                Coords pixelCoords = new Coords(
                        x * Tile.SIZE.getWidth(),
                        y * Tile.SIZE.getHeight());

                pixelCoords.minOffset(camera);

                getTile(coords).render(graphics, pixelCoords);
            }
        }
    }

    public String getWorldPath() {
        return settings.getLvlPath(settings.getWorldNumber());
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public Size getWorldSize() {
        return worldSize;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}
