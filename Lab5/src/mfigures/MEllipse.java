package mfigures;

import java.awt.*;

public class MEllipse extends MCircle {

    private int r2;

    public MEllipse() {
        super();
        this.r2 = rand.nextInt(100) + 1;
    }

    public MEllipse(int x, int y, int r1, int r2) {
        super(x, y, r1);
        this.r2 = r2;
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.fillOval(getX(), getY(), 2 * getR(), 2 * r2);
    }

    public void swapR() {
        if (getR() > getR2()) {
            setX(getX() + getR() / 2);
            setY(getY() - getR2() / 2);
        } else if (getR() < getR2()) {
            setX(getX() - getR() / 2 - ((getR2() - getR()) / 2));
            setY(getY() + getR2() / 2 - ((getR2() - getR()) / 2));
        }
        int tempR = getR();
        setR(r2);
        setR2(tempR);
    }

    public int getR2() {
        return r2;
    }

    public void setR2(int r2) {
        this.r2 = r2;
    }

}
