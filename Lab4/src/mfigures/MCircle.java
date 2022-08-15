package mfigures;

import java.awt.*;

public class MCircle extends MFigure {

    private int r;

    public MCircle() {
        super();
        r = rand.nextInt(100) + 1;
    }

    public MCircle(int x, int y, int r) {
        super(x, y);
        this.r = r;
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.fillOval(getX(), getY(), 2 * r, 2 * r);
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
