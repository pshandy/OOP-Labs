package mfigures;

import java.awt.*;
import java.util.Random;

public class MRing {
    private MCircle innerCircle;
    private MCircle outerCircle;

    private static final int border = 5;
    private static final Random rand = new Random();

    public MRing(int x, int y, int r) {
        if (r <= border)
            r = border + 1;
        outerCircle = new MCircle(x, y, r, Color.BLACK);
        innerCircle = new MCircle(x + border, y + border, r - border);
    }

    public MRing(MPoint point, int r) {
        if (r <= border)
            r = border + 1;
        outerCircle = new MCircle(point.getX(), point.getY(), r, Color.BLACK);
        innerCircle = new MCircle(point.getX() + border, point.getY() + border, r - border);
    }

    public MRing() {
        int x = rand.nextInt(600) + 1;
        int y = rand.nextInt(300) + 1;
        int r = rand.nextInt(100) + 1 + border;
        outerCircle = new MCircle(x, y, r, Color.BLACK);
        innerCircle = new MCircle(x + border, y + border, r - border);
    }

    public void draw(Graphics2D g2D) {
        outerCircle.draw(g2D);
        innerCircle.draw(g2D);
    }

    public void move(int x, int y) {
        outerCircle.move(x, y);
        innerCircle.move(x, y);
    }

    public void setX(int x) {
        outerCircle.setX(x);
        innerCircle.setX(x + border);
    }

    public int getX() {
        return (outerCircle.getX());
    }

    public void setY(int y) {
        outerCircle.setY(y);
        innerCircle.setY(y + border);
    }

    public int getY() {
        return (outerCircle.getY());
    }

    public void setR(int r) {
        outerCircle.setR(r + border);
        innerCircle.setR(r);
    }

    public int getR() {
        return outerCircle.getR();
    }
}
