package Models;

import Behaviours.DonatorBehaviour;
import Behaviours.FeedexBehaviour;
import jade.core.Agent;

import java.io.IOException;

public class FeedexAgent extends Agent {


    private FeedexObjectWithLocation feedexCenter;


    public FeedexObjectWithLocation getFeedexCenter() {
        return feedexCenter;
    }

    public void setFeedexCenter(FeedexObjectWithLocation feedexCenter) {
        this.feedexCenter = feedexCenter;
    }

    public FeedexAgent(String name, int capacity) {
        this.feedexCenter = new FeedexCenter(name, capacity);
    }

    public void setup()
    {
        try {
            addBehaviour( new FeedexBehaviour(this) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}