package Models.CourierTypes;

import Models.CourierType;

public class Truck extends CourierType {

    public Truck(String name) {
        super(name);
    }

    @Override
    public int getPayload() {
        return 100;
    }
}
