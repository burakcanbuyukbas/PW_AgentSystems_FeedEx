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
import com.teamdev.jxbrowser.browser.Browser;
import javax.swing.*;
import java.awt.*;

import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import com.teamdev.jxbrowser.engine.EngineOptions;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;

public class FeedexMain {

    static ContainerController containerController;
    static AgentController agentController;


    public static void main(String[] args) throws InterruptedException {


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

        startMainContainer();
        System.out.println( "Main container started.");
        try {
            AgentController ag1, ag2, ag3, ag4, ag5, ag6, ag7, ag8, ag9;
            ag1 = containerController.acceptNewAgent("Bike 1", c1);
            ag2 = containerController.acceptNewAgent("Bike 2", c2);
            ag3 = containerController.acceptNewAgent("Car 1", c3);
            ag4 = containerController.acceptNewAgent("Truck 1", c4);

            ag5 = containerController.acceptNewAgent("Cafe 1", d1);
            ag6 = containerController.acceptNewAgent("Restaurant 1", d2);
            ag7 = containerController.acceptNewAgent("Restaurant 2", d3);

            ag8 = containerController.acceptNewAgent("Feed-Ex Center 1", f1);
            ag9 = containerController.acceptNewAgent("Feed-Ex Center 2", f2);
            System.out.println( "Agents added.");


            ag1.start();
            ag2.start();
            ag3.start();
            ag4.start();

            ag8.start();
            ag9.start();

            ag5.start();
            ag6.start();
            ag7.start();

        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
        //AIzaSyCzsVn9Lz4otxUOB5QPp4anDJ9U6xk8O0E
        //52.221196, 21.008069
        //"https://www.google.com/maps/embed/v1/place?q=place_id:ChIJF8u2SOnMHkcR7TrJJ2_WP80&key=AIzaSyCzsVn9Lz4otxUOB5QPp4anDJ9U6xk8O0E";
        GoogleMapsScene api = GoogleMapsScene.launch(new File("D:\\Users\\Burak\\IdeaProjects\\FeedEx\\src\\main\\java\\map.html"), args);

        JFrame frame = new JFrame("Google Maps");

        JFXPanel fxPanel = new JFXPanel();

        api.attach(fxPanel);

        frame.add(fxPanel);
        frame.setSize(1300, 820);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        api.addMarker(52.221196, 21.008069, "Bike-1", null, 1);
        /*api.addMarker(getMapLocation(52.221196, c1.getCourier().getX()), getMapLocation(21.008069, c1.getCourier().getY()), "Bike-1", null, 1);
        api.addMarker(getMapLocation(52.221196, c2.getCourier().getX()), getMapLocation(21.008069, c2.getCourier().getY()), "Bike-2", null, 1);
        api.addMarker(getMapLocation(52.221196, c3.getCourier().getX()), getMapLocation(21.008069, c3.getCourier().getY()), "Car-1", null, 2);
        api.addMarker(getMapLocation(52.221196, c4.getCourier().getX()), getMapLocation(21.008069, c4.getCourier().getY()), "Truck-1", null, 3);
        api.addMarker(getMapLocation(52.221196, d1.getDonator().getX()), getMapLocation(21.008069, d1.getDonator().getY()), "Bike-1", null, 5);
        api.addMarker(getMapLocation(52.221196, d2.getDonator().getX()), getMapLocation(21.008069, d2.getDonator().getY()), "Bike-1", null, 5);
        api.addMarker(getMapLocation(52.221196, d3.getDonator().getX()), getMapLocation(21.008069, d3.getDonator().getY()), "Bike-1", null, 5);
        api.addMarker(getMapLocation(52.221196, f1.getFeedexCenter().getX()), getMapLocation(21.008069, f1.getFeedexCenter().getY()), "Bike-1", null, 4);
        api.addMarker(getMapLocation(52.221196, f2.getFeedexCenter().getX()), getMapLocation(21.008069, f2.getFeedexCenter().getY()), "Bike-1", null, 4);*/


    }


    static void startMainContainer() {
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.CONTAINER_NAME, "MainContainer");
        profile.setParameter(Profile.MAIN_HOST, "localhost");

        containerController = runtime.createMainContainer(profile);
    }
    static double getMapLocation(double center, int loc){
        return center + (0.03 * (loc - 50));
    }
}