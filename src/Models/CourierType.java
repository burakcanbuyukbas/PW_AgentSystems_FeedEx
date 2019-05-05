package Models;

public class CourierType extends FeedexObjectWithLocation {
    private int payloadAmount;

    public CourierType(String name) {
        super(name);
    }

    public int getPayload() {
        return payloadAmount;
    }

    public void setPayload(int payloadAmount) {
        this.payloadAmount = payloadAmount;
    }
}
