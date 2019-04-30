import Models.CourierTypes.Bike;
import Models.CourierTypes.Car;
import Models.CourierTypes.Truck;
import Models.DonationContents.Leftover;
import Models.DonationContents.Meat;
import Models.DonationContents.Vegetable;
import Models.DonatorAgent;
import Models.CourierAgent;
import Models.FeedexAgent;
import jade.core.Agent;

public class FeedexMain {
    public static void main(String[] args){

        CourierAgent c1 = new CourierAgent(new Bike());
        CourierAgent c2 = new CourierAgent(new Bike());
        CourierAgent c3 = new CourierAgent(new Car());
        CourierAgent c4 = new CourierAgent(new Truck());

        DonatorAgent d1 = new DonatorAgent(new Vegetable(), 1);
        DonatorAgent d2 = new DonatorAgent(new Meat(), 9);
        DonatorAgent d3 = new DonatorAgent(new Leftover(), 20);

        FeedexAgent f1 = new FeedexAgent(new Meat(), 20);
        FeedexAgent f2 = new FeedexAgent(new Leftover(), 100);



    }
}