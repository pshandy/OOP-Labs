package mfigures;

import java.awt.*;

public class MLine extends MFigure {

    private int x2;
    private int y2;

    public MLine() {
        super();
        x2 = rand.nextInt(600) + 1 - getX();
        y2 = rand.nextInt(600) + 1 - getY();
    }

    public MLine(int x1, int y1, int x2, int y2) {
        super(x1, y1);
        this.x2 = x2 - getX();
        this.y2 = y2 - getY();
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawLine(getX(), getY(), getX() + x2, getY() + y2);
    }

    public int getX2() {
        return (getX() + x2);
    }

    public void setX2(int x2) {
        this.x2 = x2 - getX();
    }

    public int getY2() {
        return (getY() + y2);
    }

    public void setY2(int y2) {
        this.y2 = y2 - getY();
    }
}
