package Models;

import Behaviours.DonatorBehaviour;
import jade.core.Agent;

import java.io.IOException;

public class CourierAgent extends Agent {
    public void setup()
    {
        try {
            addBehaviour( new DonatorBehaviour(this) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}