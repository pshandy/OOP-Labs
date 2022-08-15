package mfigures;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class MRectangle {
    private MPoint  point;
    private int     width;
    private int     height;

    private static final Random rand = new Random();
    private final Color color = Color.BLUE;

    public MRectangle(int x, int y, int width, int height) {
        this.point = new MPoint(x, y);
        this.width = width;
        this.height = height;
    }

    public MRectangle(MPoint point, int width, int height) {
        this.point = point;
        this.width = width;
        this.height = height;
    }

    public MRectangle() {
        this.point = new MPoint(rand.nextInt(600) + 1,
                rand.nextInt(300) + 1);
        this.width = rand.nextInt(100) + 1;
        this.height = rand.nextInt(100) + 1;
    }

    public void draw(Graphics2D g2D) {
    	g2D.setColor(color);
        g2D.fillRect(point.getX(), point.getY(), width, height);
    }

    public void move(int x, int y) {
        point.setX(point.getX() + x);
        point.setY(point.getY() + y);
    }

    public void setX(int x) {
        point.setX(x);
    }

    public int getX() {
        return (point.getX());
    }

    public void setY(int y) {
        point.setY(y);
    }

    public int getY() {
        return (point.getY());
    }

    public int getWidth() {
        return (width);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return (height);
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
