package mfigures;

import java.awt.*;

public class MLine extends MFigure {

    private int x2;
    private int y2;

    public MLine() {
        super();
        x2 = rand.nextInt(300) + 1;
        y2 = rand.nextInt(300) + 1;
    }

    public MLine(int x1, int y1, int x2, int y2) {
        super(x1, y1);
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void move(int x2, int y2) {
        setX(getX() + x2);
        setY(getY() + y2);
        this.x2 += x2;
        this.y2 += y2;
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawLine(getX(), getY(), x2, y2);
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}
