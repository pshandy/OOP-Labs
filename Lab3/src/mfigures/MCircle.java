package mfigures;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class MCircle {
    private MPoint  point;
    private int     r;

    private static final Random rand = new Random();
    private Color color = Color.BLUE;
    
    public MCircle(int x, int y, int r, Color color) {
        this.point = new MPoint(x, y);
        this.r = r;
        this.color = color;
    }

    public MCircle(int x, int y, int r) {
        this.point = new MPoint(x, y);
        this.r = r;
    }

    public MCircle(MPoint point, int r) {
        this.point = point;
        this.r = r;
    }

    public MCircle() {
        this.point = new MPoint(rand.nextInt(600) + 1,
                rand.nextInt(300) + 1);
        this.r = rand.nextInt(100) + 1;
    }

    public void draw(Graphics2D g2D) {
    	g2D.setColor(color);
        g2D.fillOval(point.getX(), point.getY(), 2 * r, 2 * r);
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

    public void setR(int r) {
        this.r = r;
    }

    public int getR() {
        return r;
    }
}
