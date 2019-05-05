import Models.CourierType;
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
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class FeedexMain {

    static ContainerController containerController;
    static AgentController agentController;


    public static void main(String[] args){





        CourierAgent c1 = new CourierAgent(new Bike("Bike 1"));
        CourierAgent c2 = new CourierAgent(new Bike("Bike 2"));
        CourierAgent c3 = new CourierAgent(new Car("Car 1"));
        CourierAgent c4 = new CourierAgent(new Truck("Truck 1"));

        DonatorAgent d1 = new DonatorAgent("Cafe 1");
        DonatorAgent d2 = new DonatorAgent("Restaurant 1");
        DonatorAgent d3 = new DonatorAgent("Restaurant 2");

        FeedexAgent f1 = new FeedexAgent("Feed-Ex Center 1", 20);
        FeedexAgent f2 = new FeedexAgent("Feed-Ex Center 2", 100);

        System.out.println( c1.getCourier().getName() + " -- Location: (" + c1.getCourier().getX() + ", " + c1.getCourier().getY() + ")");
        System.out.println( c2.getCourier().getName() + " -- Location: (" + c2.getCourier().getX() + ", " + c2.getCourier().getY() + ")");
        System.out.println( c3.getCourier().getName() + " -- Location: (" + c3.getCourier().getX() + ", " + c3.getCourier().getY() + ")");
        System.out.println( c4.getCourier().getName() + " -- Location: (" + c4.getCourier().getX() + ", " + c4.getCourier().getY() + ")");

        System.out.println( d1.getDonator().getName() + " -- Location: (" + d1.getDonator().getX() + ", " + d1.getDonator().getY() + ")");
        System.out.println( d2.getDonator().getName() + " -- Location: (" + d2.getDonator().getX() + ", " + d2.getDonator().getX() + ")");
        System.out.println( d3.getDonator().getName() + " -- Location: (" + d3.getDonator().getX() + ", " + d3.getDonator().getX() + ")");

        System.out.println( f1.getFeedexCenter().getName() + " -- Location: (" + f1.getFeedexCenter().getX() + ", " + f1.getFeedexCenter().getX() + ")");
        System.out.println( f2.getFeedexCenter().getName() + " -- Location: (" + f2.getFeedexCenter().getX() + ", " + f2.getFeedexCenter().getX() + ")");

        startMainContainer("127.0.0.1", Profile.LOCAL_PORT, "mainContainer");
        System.out.println( "Main container started.");
        addAgent(containerController, "Bike 1", CourierAgent.class.getName(), null );
        addAgent(containerController, "Bike 2", CourierAgent.class.getName(), null );
        addAgent(containerController, "Car 1", CourierAgent.class.getName(), null );
        addAgent(containerController, "Truck 1", CourierAgent.class.getName(), null );

        addAgent(containerController, "Cafe 1", DonatorAgent.class.getName(), null );
        addAgent(containerController, "Restaurant 1", DonatorAgent.class.getName(), null );
        addAgent(containerController, "Restaurant 2", DonatorAgent.class.getName(), null );

        addAgent(containerController, "Feed-Ex Center 1", FeedexAgent.class.getName(), null );
        addAgent(containerController, "Feed-Ex Center 2", FeedexAgent.class.getName(), null );

    }

    static void startMainContainer(String host, String port, String name) {
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, host);
        profile.setParameter(Profile.MAIN_PORT, port);
        profile.setParameter(Profile.PLATFORM_ID, name);

        containerController = runtime.createMainContainer(profile);
    }

    static void addAgent(ContainerController cc, String agent, String classe, Object[] args) {
        try {
            agentController = cc.createNewAgent(agent, classe, args);
            agentController.start();
        } catch (StaleProxyException s) {
            s.printStackTrace();
        }
    }
}