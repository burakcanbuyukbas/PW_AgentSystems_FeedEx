package Models.CourierTypes;

import Models.CourierType;

public class Truck extends CourierType {
    @Override
    public int getPayload() {
        return 100;
    }
}
