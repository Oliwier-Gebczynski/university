package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import java.awt.*;

public class Candy {
    private int x;
    private int y;

    public Candy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point getPosition() {
        return new Point(x, y);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
