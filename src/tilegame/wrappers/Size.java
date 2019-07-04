package tilegame.wrappers;

import tilegame.gfx.GameCamera;

public class Size {

    private int width;
    private int height;


    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public double getWidthMinOffset(GameCamera gameCamera) {
        return width - gameCamera.getOffset().getX();
    }

    public double getHeightMinOffset(GameCamera gameCamera) {
        return height - gameCamera.getOffset().getY();
    }


    public int getWidthMinOffsetInt(GameCamera gameCamera) {
        return (int) (width - gameCamera.getOffset().getX());
    }

    public int getHeightMinOffsetInt(GameCamera gameCamera) {
        return (int) (height - gameCamera.getOffset().getY());
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void minusSize(int pixels) {
        width -= pixels;
        height -= pixels;
        if (width < 0 || height < 0) {
            width = 0;
            height = 0;
        }
    }
}
