package Models.CourierTypes;

import Models.CourierType;

public class Bike extends CourierType {
    @Override
    public int getPayload() {
        return 2;
    }
}
