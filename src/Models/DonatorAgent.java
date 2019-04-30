package Models;

import Behaviours.DonatorBehaviour;
import jade.core.Agent;

import java.io.IOException;

public class DonatorAgent extends Agent {

    private FeedexObjectWithLocation donator;

    public FeedexObjectWithLocation getDonator() {
        return donator;
    }

    public void setDonator(FeedexObjectWithLocation donator) {
        this.donator = donator;
    }

    public DonatorAgent(FoodType content, int amount) {
        this.donator = new Donator(content, amount);
    }

    public void setup()
    {
        try {
            addBehaviour( new DonatorBehaviour(this) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
