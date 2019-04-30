package Models.CourierTypes;

import Models.CourierType;

public class Car extends CourierType {
    @Override
    public int getPayload() {
        return 10;
    }
}
