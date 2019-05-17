package Behaviours;
import Models.Donation;
import Models.DonationContents.Leftover;
import Models.DonationContents.Meat;
import Models.DonationContents.Vegetable;
import Models.Donator;
import Models.DonatorAgent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.*;

import javax.swing.text.AbstractDocument;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.fasterxml.jackson.core.*;

public class DonatorBehaviour extends SimpleBehaviour {

    public Agent _agent;
    public Donation _donation;
    public Donator _donator;
    private int counter= 0;
    private double freq= 0;
    private int messageCount = 0;
    private double distance = -1;
    private boolean donated = false;
    AID bestOffer;
    @Override
    public void action() {
        AMSAgentDescription [] agents = null;
        List<AMSAgentDescription> centers = new ArrayList<AMSAgentDescription>();

        freq = (Math.random() * ((10 - 5) + 1)) + 5;
        try {
            SearchConstraints c = new SearchConstraints();
            c.setMaxResults ( new Long(-1) );
            agents = AMSService.search( getAgent(), new AMSAgentDescription (), c );
            AID myID = _agent.getAID();
            for (int i=0; i<agents.length;i++)
            {
                AID agentID = agents[i].getName();
//                System.out.println(
//                        ( agentID.equals( myID ) ? "*** " : "    ")
//                                + i + ": " + agentID.getName()
//                );
                if (agents[i].getName().toString().contains("Feed-Ex Center")){
                    AMSAgentDescription center = agents[i];
                    centers.add(center);
                }
            }

            //create a random donation
            Donation donation;
            Random r = new Random();
            int randomContent = r.nextInt((3 - 1) + 1) + 1;
            int randomAmount= r.nextInt((3 - 1) + 1) + 1;
            int randomForTruck = r.nextInt((100 - 10) + 1) + 10;
            int randomForCar = r.nextInt((10 - 2) + 1) + 2;
            int randomForBike = r.nextInt((2 - 1) + 1) + 1;
            String contentName;
            switch (randomContent){
                case 1:
                    if (randomAmount == 1){
                        donation = new Donation(new Leftover(), randomForBike );
                    }
                    else if(randomAmount == 2){
                        donation = new Donation(new Leftover(), randomForCar );
                    }
                    else{
                        donation = new Donation(new Leftover(), randomForTruck );
                    }
                    contentName = "Leftover";
                    break;
                case 2:
                    if (randomAmount == 1){
                        donation = new Donation(new Meat(), randomForBike );
                    }
                    else if(randomAmount == 2){
                        donation = new Donation(new Meat(), randomForCar );
                    }
                    else{
                        donation = new Donation(new Meat(), randomForTruck );
                    }
                    contentName = "Meat";
                    break;
                case 3:
                    if (randomAmount == 1){
                        donation = new Donation(new Vegetable(), randomForBike );
                    }
                    else if(randomAmount == 2){
                        donation = new Donation(new Vegetable(), randomForCar );
                    }
                    else{
                        donation = new Donation(new Vegetable(), randomForTruck );
                    }
                    contentName = "Vegetable";
                    break;
                default:
                    donation = new Donation(new Leftover(), 0 );
                    contentName = "nothing";
            }
            donation.setDonator(_donator);


            if (!donated){


                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.setContent(ow.writeValueAsString(donation));
                for (int i = 0; i < centers.size(); i++)
                    msg.addReceiver( new AID( centers.get(i).getName().getName().split("@")[0], AID.ISLOCALNAME) );

                myAgent.send(msg);
                System.out.println(_agent.getName() + ": I want to donate " + donation.getDonationAmount() + " kg of " + contentName);
                donated = true;
            }



            //receive replies from centers
            ACLMessage msgFromCenter = myAgent.receive();
            if (msgFromCenter!=null) {
                if (msgFromCenter.getContent().substring(0,2).equals("OIW")){
                    System.out.println(_agent.getName().split("@")[0] + " waiting for courier "
                            + msgFromCenter.getContent().split(",")[1] + " in " + msgFromCenter.getContent().split(",")[2] + " minutes.");
                }
                else {
                    double offer = Double.parseDouble(msgFromCenter.getContent());
                    if (offer < distance || distance == -1) {
                        System.out.println(myAgent.getName() + ": Your offer(" + offer + ") is better than current match(" + distance + ")");
                        distance = offer;
                        bestOffer = msgFromCenter.getSender();
                    }
                    messageCount++;
                    if (messageCount == 2) {
                        ACLMessage replyToCenter = new ACLMessage(ACLMessage.INFORM);
                        Donator donator = donation.getDonator();
                        replyToCenter.setContent("OK," + donation.getDonationAmount() + "," + donator.getX() + "," + donator.getY() + "," + donator.getName().split("@")[0]);
                        replyToCenter.addReceiver(bestOffer);
                        myAgent.send(replyToCenter);
                        System.out.println(myAgent.getName() + ": Best matching is " + bestOffer.getName().split("@")[0] + ". Send a courier pls.");
                        counter = 10;
                    }
                }
            }


            counter++;
        }
        catch (Exception e) {
            System.out.println( e.toString());
        }

    }

    @Override
    public boolean done() {
        if (counter < 10){
            _agent.doWait((long)freq*1000);
            return false;
        }
        else{
            return true;
        }
    }
    //constructor
    public DonatorBehaviour(Agent agent, Donator donator) throws IOException {
        super(agent);
        this._agent = agent;
        this._donator = donator;
    }
}
