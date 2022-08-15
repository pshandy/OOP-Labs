package mfigures;

import java.awt.*;
import java.util.Random;

public abstract class MFigure {

    private int x;
    private int y;
    protected static final Random rand = new Random();

    public MFigure() {
        this.x = rand.nextInt(100) + 1;
        this.y = rand.nextInt(100) + 1;
    }

    public MFigure(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public final void move(int x, int y, Frame frame) {
        this.x += x;
        this.y += y;
        draw((Graphics2D)frame.getGraphics());
    }

    public abstract void draw(Graphics2D g2D);
}
