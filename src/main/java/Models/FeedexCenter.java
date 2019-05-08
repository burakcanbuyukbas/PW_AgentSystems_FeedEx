package Models;

public class FeedexCenter extends FeedexObjectWithLocation {
    private int capacity;



    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    protected FeedexCenter(String name, int capacity) {
        super(name);
        this.capacity = capacity;
    }
}
