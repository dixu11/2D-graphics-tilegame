package tilegame.factories;

import tilegame.wrappers.Coords;
import tilegame.wrappers.Size;

// class made to pass actual game state easily everywhere
public class Settings {

    //GAME DEFAULT SETTINGS
    public static final String DEFAULT_GAME_TITLE = "Sokoban";
    public static final Size DEFAULT_WINDOW_SIZE = new Size(640, 360);

    //WORLDS DEFAULT SETTINGS
       public static final int DEFAULT_STARTING_LVL_NUM = 6;
    public static final Size DEFAULT_WORLD_SIZE = new Size(12, 12);
    public static final String DEFAULT_LVL_PATH = "res/worlds/world%d.lvl";
    public static final Coords DEFAULT_COORDS = new Coords(0, 0);
    public static final int DEFAULT_NUMBER_OF_LVLS = 6;

    //GAME SETTINGS
    private String gameTitle;
    private Size windowSize;

    //WORLD SETTINGS
    private int worldNumber;
    private Size worldSize;
    private String lvlPath;
    private Coords startingCoords;
    private int numberOfLvls;


    private Settings() {
        gameTitle = DEFAULT_GAME_TITLE;
        windowSize = DEFAULT_WINDOW_SIZE;
        worldNumber = DEFAULT_STARTING_LVL_NUM;
        worldSize = DEFAULT_WORLD_SIZE;
        lvlPath = DEFAULT_LVL_PATH;
        startingCoords = DEFAULT_COORDS;
        numberOfLvls = DEFAULT_NUMBER_OF_LVLS;
    }


    public static SettingsBuilder createSettings() {
        return new SettingsBuilder();
    }

    public static Settings createDefaultSettings() {
        return new SettingsBuilder().build();
    }

    public boolean setNextWolrdNumber() {
        if (worldNumber+1 <=numberOfLvls) {
            worldNumber++;
            return true;
        }
        return false;
    }


    private static class SettingsBuilder {
        private Settings startingSettings = new Settings();

        public SettingsBuilder() {
            setTitle(DEFAULT_GAME_TITLE);
            setWindowSize(DEFAULT_WINDOW_SIZE);
            setLvlNumber(DEFAULT_STARTING_LVL_NUM);

        }

        private SettingsBuilder setLvlNumber(int lvlNumber) {
            startingSettings.setWorldNumber(lvlNumber);
            return this;
        }

        public SettingsBuilder setTitle(String title) {
            startingSettings.setGameTitle(title);
            return this;
        }

        public SettingsBuilder setWindowSize(Size windowSize) {
            startingSettings.setWindowSize(windowSize);
            return this;
        }

        public Settings build() {
            return startingSettings;
        }

    }


    public String getLvlPath(int lvl) {
        return String.format(lvlPath, lvl);
    }




    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public Size getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(Size windowSize) {
        this.windowSize = windowSize;
    }

    public void setWorldNumber(int worldNumber) {
        this.worldNumber = worldNumber;
    }

    public int getWorldNumber() {
        return worldNumber;
    }

    public Size getWorldSize() {
        return worldSize;
    }

    public void setWorldSize(Size worldSize) {
        this.worldSize = worldSize;
    }

    public void setLvlPath(String lvlPath) {
        this.lvlPath = lvlPath;
    }

    public String getLvlPath() {
        return lvlPath;
    }

    public Coords getStartingCoords() {
        return startingCoords;
    }

    public void setStartingCoords(Coords startingCoords) {
        this.startingCoords = startingCoords;
    }

    public int getNumberOfLvls() {
        return numberOfLvls;
    }
}
