package mfigures;

import java.awt.*;

public class MSquare extends MFigure {
    private int width;

    public MSquare() {
        super();
        width = rand.nextInt(50) + 1;
    }

    public MSquare(int x, int y, int width) {
        super(x, y);
        this.width = width;
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.fillRect(getX(), getY(), width, width);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int r) {
        this.width = r;
    }
}
