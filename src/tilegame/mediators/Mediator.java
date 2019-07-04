package tilegame.mediators;

import tilegame.entities.Entity;
import tilegame.entities.EntityManager;
import tilegame.entities.creatures.Player;
import tilegame.entities.statics.Gem;
import tilegame.factories.Settings;
import tilegame.tiles.Tile;
import tilegame.worlds.World;
import tilegame.wrappers.Coords;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// handles game components communications (Entities and tiles)
public class Mediator {

    private EntityManager entityManager;
    private World world;
    private Settings settings;

    public Mediator(EntityManager entityManager,  World world, Settings settings) {
        this.entityManager = entityManager;
        this.world = world;
        this.settings = settings;
    }

    public List<Entity> isColision(Entity askingEntity, Coords offset) {
        List<Entity> entitiesPushed = new ArrayList<>();
        for (Entity entity : entityManager.getEntities()) {
            if (entity.equals(askingEntity) || !entity.isSolid()) {
                continue;
            }
            if (entity.getColisionBounds(new Coords(0,0)).intersects(askingEntity.getColisionBounds(offset))) {
//                System.out.println("Colision with entity: " + entity.getSymbol());
                entitiesPushed.add(entity);
            }
        }
        return entitiesPushed;
    }

    public boolean areAllGemsActive() {
        for (Gem gem : entityManager.getGems()) {
            if (!gem.isActive()) {
                return false;
            }
        }
        return true;
    }

    public Map<Character, List<Coords>> readEntityStartingCoords() {
       return world.readEntityStartingCoords();
    }

    public boolean isTileSolid(Coords pixelCoords) {
      return   world.getTileByPixelCoords(pixelCoords).isSolid();
    }


    public int getStepCount() {
        return entityManager.getPlayer().getStepCount();
    }

    public void reducePlayerBy(int pixels) {
        entityManager.getPlayer().getSize().minusSize(pixels);
    }


    public List<Entity> getPlayerColisions() {
        return isColision(getPlayer(), new Coords(0,0));

    }

    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return entityManager.getPlayer();
    }

    public Tile getTileOnCoords(Coords coords) {
        return world.getTileByPixelCoords(coords);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Coords getStartingCoords() {
        return settings.getStartingCoords();
    }
}
