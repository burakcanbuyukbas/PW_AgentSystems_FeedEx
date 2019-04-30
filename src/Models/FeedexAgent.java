package Models;

import Behaviours.DonatorBehaviour;
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

    public FeedexAgent(FoodType content, int capacity) {
        this.feedexCenter = new FeedexCenter(content, capacity);
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