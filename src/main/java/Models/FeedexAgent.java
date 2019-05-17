package Models;

import Behaviours.DonatorBehaviour;
import Behaviours.FeedexBehaviour;
import jade.core.Agent;

import java.io.IOException;

public class FeedexAgent extends Agent {


    private FeedexCenter feedexCenter;


    public FeedexCenter getFeedexCenter() {
        return feedexCenter;
    }

    public void setFeedexCenter(FeedexCenter feedexCenter) {
        this.feedexCenter = feedexCenter;
    }

    public FeedexAgent(String name, int capacity) {
        this.feedexCenter = new FeedexCenter(name, capacity);
    }

    public void setup()
    {
        try {
            addBehaviour( new FeedexBehaviour(this, feedexCenter) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}