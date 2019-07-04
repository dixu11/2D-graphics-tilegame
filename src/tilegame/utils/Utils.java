package tilegame.utils;

import tilegame.tiles.Tile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utils {

    public static String loadFileAsString(String path) {
        StringBuilder content = new StringBuilder("");
        File file = new File(path);
        try {
            Scanner scanner = new Scanner(file);
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                content.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static boolean onlyNumbers(String numbers) {
        for (int i = 0; i < numbers.length(); i++) {
            if (!Character.isDigit(numbers.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static int widthToTiles(double number) {
        return (int) number / Tile.SIZE.getWidth();
    }

    public static int heightToTiles(double number) {
        return (int) number / Tile.SIZE.getHeight();
    }

    public static int widthToTilePixels(double number) {
        return (int) number * Tile.SIZE.getWidth();
    }

    public static int heightToTilePixels(double number) {
        return (int) number * Tile.SIZE.getHeight();
    }
}
