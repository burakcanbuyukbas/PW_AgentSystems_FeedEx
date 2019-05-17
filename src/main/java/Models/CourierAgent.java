package Models;

import Behaviours.CourierBehaviour;
import Behaviours.DonatorBehaviour;
import jade.core.Agent;

import java.io.IOException;

public class CourierAgent extends Agent {

    public CourierAgent(CourierType type) {
        this.courier = new FeedexObjectWithLocation(type.getName());
        this.type = type;
    }

    private FeedexObjectWithLocation courier;
    private CourierType type;
    private Donator source;
    private FeedexCenter destination;

    public FeedexObjectWithLocation getCourier() {
        return courier;
    }

    public void setCourier(FeedexObjectWithLocation courier) {
        this.courier = courier;
    }

    public CourierType getType() {
        return type;
    }

    public void setType(CourierType type) {
        this.type = type;
    }

    public Donator getSource() {
        return source;
    }

    public void setSource(Donator source) {
        this.source = source;
    }

    public FeedexCenter getDestination() {
        return destination;
    }

    public void setDestination(FeedexCenter destination) {
        this.destination = destination;
    }

    public void setup()
    {
        try {
            addBehaviour( new CourierBehaviour(this, type) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}