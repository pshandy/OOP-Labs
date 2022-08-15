package mfigures;

import java.awt.*;

public class MRhombus extends MSquare {
    private int height;

    public MRhombus() {
        super();
        this.height = rand.nextInt(100) + 1;
    }

    public MRhombus(int x, int y, int width, int height) {
        super(x, y, width);
        this.height = height;
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawLine(getX(), getY() + height / 2, getX() + getWidth() / 2, getY());
        g2D.drawLine(getX() + getWidth() / 2, getY(), getX() + getWidth(), getY() + height / 2);
        g2D.drawLine(getX(), getY() + height / 2, getX() + getWidth() / 2, getY() + height);
        g2D.drawLine(getX() + getWidth() / 2, getY() + height, getX() + getWidth(), getY() + height / 2);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
