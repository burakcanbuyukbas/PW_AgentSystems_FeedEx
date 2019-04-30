package Models;

public class FeedexCenter extends FeedexObjectWithLocation {
    private FoodType content;
    private int capacity;

    public FoodType getContent() {
        return content;
    }

    public void setContent(FoodType content) {
        this.content = content;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    protected FeedexCenter(FoodType content, int capacity) {
        this.content = content;
        this.capacity = capacity;
    }
}
