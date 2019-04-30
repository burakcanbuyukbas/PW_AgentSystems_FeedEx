package Models;

import java.util.Random;

public class FeedexObjectWithLocation {


    protected FeedexObjectWithLocation() {
        Random rnd = new Random();
        this.x = rnd.nextInt(100);
        this.y = rnd.nextInt(100);
    }

    private int x;

    private int y;

    private String Name;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
