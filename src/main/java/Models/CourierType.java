package Models;

public class CourierType extends FeedexObjectWithLocation {
    private int payloadAmount;
    private String name;

    public CourierType(String _name) {
        super(_name);
        name = _name;
    }

    public int getPayload() {
        return payloadAmount;
    }

    public void setPayload(int payloadAmount) {
        this.payloadAmount = payloadAmount;
    }
}
