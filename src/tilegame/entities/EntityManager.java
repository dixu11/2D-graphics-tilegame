package tilegame.entities;

import tilegame.Game;
import tilegame.entities.creatures.Box;
import tilegame.entities.creatures.Player;
import tilegame.entities.statics.Gem;
import tilegame.gfx.GameCamera;
import tilegame.input.KeyManager;
import tilegame.mediators.Mediator;
import tilegame.wrappers.Coords;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityManager {

    private Player player;
    private List<Entity> entities;
    private List<Gem> gems;
    private Mediator mediator;

    private KeyManager keyManager;

    public EntityManager(KeyManager keyManager) {
        this.keyManager = keyManager;
    }

    public void init(Mediator mediator) {
        this.mediator = mediator;
        this.gems = new ArrayList<>();
        entities = buildEntities();

    }


    public List<Entity> buildEntities() {
        List<Entity> entities = new ArrayList<>();
        Map<Character, List<Coords>> startingLocations = mediator.readEntityStartingCoords();

        for (Character character : startingLocations.keySet()) {
            List<Coords> entitiesCoords = startingLocations.get(character);
            for (Coords entitiesCoord : entitiesCoords) {

                switch (character) {
                    case Entity.PLAYER_SYMBOL:
                        player = new Player(entitiesCoord, keyManager, mediator);
                        entities.add(player);
                        break;
                    case Entity.BOX_SYMBOL:
                        entities.add(new Box(entitiesCoord, mediator));
                        break;
                    case Entity.GEM_SYMBOL:
                        Gem gem = new Gem(entitiesCoord, mediator);
                        entities.add(gem);
                        gems.add(gem);
                        break;
                }
            }


        }


      /*  entities.add(new Gem(new Coords(55, 275), mediator));
        entities.add(new Gem(new Coords(550, 64), mediator));
        entities.add(new Gem(new Coords(55, 550), mediator));
        entities.add(new Gem(new Coords(550, 495), mediator));

        entities.add(new Box(new Coords(275, 110), mediator));
        entities.add(new Box(new Coords(275, 330), mediator));
        entities.add(new Box(new Coords(495, 330), mediator));
        entities.add(new Box(new Coords(115, 165), mediator));*/
//        entities.add(new Box(new Coords(200, 300), mediator));
        return entities;
    }

    public void tick() {

        for (Entity entity : entities) {
            if (entity.equals(player)) {
                continue;
            }
            entity.tick();
        }
        player.tick();
    }

    public void render(Graphics graphics, GameCamera camera) {
        renderWithPriority(graphics, camera, true);
        renderWithPriority(graphics, camera, false);
        player.render(graphics, camera);
    }

    public void renderWithPriority(Graphics graphics, GameCamera camera, boolean renderFlatEntities) {
        for (Entity entity : entities) {
            if (entity.equals(player)) {
                continue;
            }
            if (renderFlatEntities && entity.isFlat()) {
                entity.render(graphics, camera);
            } else if (!renderFlatEntities && !entity.isFlat()) {
                entity.render(graphics, camera);
            }

        }
    }

    public List<Gem> getGems() {
        return gems;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void setPlayerCoords(Coords coords) {
        player.setCoords(coords);
    }

    public Player getPlayer() {
        return player;
    }

    public List<Entity> getEntities() {
        return entities;
    }
}


//Possible to add: comparator that define rendering order