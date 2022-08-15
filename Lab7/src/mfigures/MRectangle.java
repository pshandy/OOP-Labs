package mfigures;

import java.awt.*;

public class MRectangle extends MSquare {
    private int height;

    public MRectangle() {
        super();
        this.height = rand.nextInt(50) + 1;
    }

    public MRectangle(int x, int y, int width, int height) {
        super(x, y, width);
        this.height = height;
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.fillRect(getX(), getY(), getWidth(), height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
