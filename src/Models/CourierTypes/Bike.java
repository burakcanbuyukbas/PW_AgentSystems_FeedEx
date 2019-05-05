package Models.CourierTypes;

import Models.CourierType;

public class Bike extends CourierType {
    public Bike(String name) {
        super(name);
    }

    @Override
    public int getPayload() {
        return 2;
    }
}
