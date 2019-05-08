package Models.CourierTypes;

import Models.CourierType;

public class Car extends CourierType {
    public Car(String name) {
        super(name);
    }

    @Override
    public int getPayload() {
        return 10;
    }
}
