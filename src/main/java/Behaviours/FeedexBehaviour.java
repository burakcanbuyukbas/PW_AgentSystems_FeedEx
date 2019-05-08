package Behaviours;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

import java.io.IOException;

public class FeedexBehaviour extends SimpleBehaviour{
    @Override
    public void action() {

    }

    @Override
    public boolean done() {
        return false;
    }

    //constructor
    public FeedexBehaviour(Agent agent) throws IOException {
        super(agent);

    }


}
