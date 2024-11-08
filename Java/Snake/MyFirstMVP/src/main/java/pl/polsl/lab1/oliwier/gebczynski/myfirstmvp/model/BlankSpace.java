package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

public class BlankSpace {
    private int x;
    private int y;

    public BlankSpace(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
