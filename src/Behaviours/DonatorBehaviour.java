package Behaviours;
import Models.DonatorAgent;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

import java.io.IOException;


public class DonatorBehaviour extends SimpleBehaviour {

    @Override
    public void action() {

    }

    @Override
    public boolean done() {
        return false;
    }
    //constructor
    public DonatorBehaviour(Agent agent) throws IOException {
        super(agent);

    }
}
