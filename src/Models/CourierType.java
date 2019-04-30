package Models;

public class CourierType extends FeedexObjectWithLocation {
    private int payloadAmount;

    public int getPayload() {
        return payloadAmount;
    }

    public void setPayload(int payloadAmount) {
        this.payloadAmount = payloadAmount;
    }
}
