package mfigures;

import java.awt.*;
import java.util.Random;

public class MSquareRing {
    private MSquare innerSquare;
    private MSquare outerSquare;

    private static final int border = 2;
    private static final Random rand = new Random();

    public MSquareRing(int x, int y, int width) {
        if (width <= 2 * border)
            width = 2 * border + 1;
        outerSquare = new MSquare(x, y, width, Color.BLACK);
        innerSquare = new MSquare(x + border, y + border, width - 2 * border);
    }

    public MSquareRing(MPoint point, int width) {
        if (width <= 2 * border)
            width = 2 * border + 1;
        outerSquare = new MSquare(point.getX(), point.getY(), width, Color.BLACK);
        innerSquare = new MSquare(point.getX() + border, point.getY() + border, width - 2 * border);
    }

    public MSquareRing() {
        int x = rand.nextInt(600) + 1;
        int y = rand.nextInt(300) + 1;
        int width = rand.nextInt(100) + 1 + border;
        outerSquare = new MSquare(x, y, width, Color.BLACK);
        innerSquare = new MSquare(x + border, y + border, width - 2 * border);
    }

    public void draw(Graphics2D g2D) {
        outerSquare.draw(g2D);
        g2D.setColor(Color.RED);
        innerSquare.draw(g2D);
    }

    public void move(int x, int y) {
        outerSquare.move(x, y);
        innerSquare.move(x, y);
    }

    public void setX(int x) {
        outerSquare.setX(x);
        innerSquare.setX(x + border);
    }

    public int getX() {
        return (outerSquare.getX());
    }

    public void setY(int y) {
        outerSquare.setY(y);
        innerSquare.setY(y + border);
    }

    public int getY() {
        return (outerSquare.getY());
    }

    public void setWidth(int width) {
        outerSquare.setWidth(width);
        innerSquare.setWidth(width - 2 * border);
    }

    public int getWidth() {
        return outerSquare.getWidth();
    }
}
