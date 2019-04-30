package Models;

import Behaviours.DonatorBehaviour;
import jade.core.Agent;

import java.io.IOException;

public class FeedexAgent extends Agent {
    public void setup()
    {
        try {
            addBehaviour( new DonatorBehaviour(this) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}