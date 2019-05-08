package Models;

import Behaviours.DonatorBehaviour;
import jade.core.Agent;

import java.io.IOException;

public class DonatorAgent extends Agent {

    private Donator donator;

    public Donator getDonator() {
        return donator;
    }

    public void setDonator(Donator donator) {
        this.donator = donator;
    }

    public DonatorAgent(String name) {
        this.donator = new Donator(name);
    }


    public void setup()
    {
        try {
            addBehaviour( new DonatorBehaviour(this, donator.getDonation()) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
