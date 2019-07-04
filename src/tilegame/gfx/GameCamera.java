package tilegame.gfx;

import tilegame.entities.Entity;
import tilegame.factories.Settings;
import tilegame.tiles.Tile;
import tilegame.utils.Utils;
import tilegame.wrappers.Coords;
import tilegame.wrappers.Size;

import java.util.Random;

public class GameCamera {

    private Coords offset;
    private Size windowSize;
    private Settings settings;



    public GameCamera(Coords offset, Settings settings) {
        this.offset = offset;
        this.settings = settings;
        this.windowSize = settings.getWindowSize();
    }


    public void centerOnEntity(Entity entity) {
        Coords entityCoords = entity.getCoords();
        offset.setX( entityCoords.getX() - (double) windowSize.getWidth() / 2);
        offset.setY( entityCoords.getY() - (double) windowSize.getHeight() / 2);
        checkBlankSpace();
    }

    public void move(Coords amount) {
        offset.addCoords(amount);
    }

    public void move(int xPixels, int yPixels) {
        move(new Coords(xPixels, yPixels));
        checkBlankSpace();
    }

    public void checkBlankSpace() {
        int worldWidth = settings.getWorldSize().getWidth();
        int worldHight = settings.getWorldSize().getHeight();

        double cameraPositionOnMapX = Utils.widthToTilePixels(worldWidth) - windowSize.getWidth();   // world Width (in tiles) * tile width in pixels - window width
        double cameraPositionOnMapY = Utils.widthToTilePixels(worldHight) - windowSize.getHeight();

        if (offset.getX() < 0) {
            offset.setX(0);

        } else if (offset.getX() > cameraPositionOnMapX ) {
            offset.setX(cameraPositionOnMapX);
        }

        if (offset.getY() < 0) {
            offset.setY(0);
        } else if (offset.getY() > cameraPositionOnMapY) {
            offset.setY(cameraPositionOnMapY);
        }
    }

    public void earthQuake() {
        offset.setY(new Random().nextInt(50));
        offset.setX(new Random().nextInt(25));
    }


    public Coords getOffset() {
        return offset;
    }

    public Size getWindowSize() {
        return windowSize;
    }

}
