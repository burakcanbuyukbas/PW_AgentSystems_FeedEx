package Behaviours;
import Models.Donation;
import Models.Donator;
import Models.FeedexAgent;
import Models.FeedexCenter;
import com.fasterxml.jackson.databind.ObjectMapper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.ContainerController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeedexBehaviour extends SimpleBehaviour{

    public Agent _agent;
    public FeedexCenter _center;

    @Override
    public void action() {
        AID myID = myAgent.getAID();
        AMSAgentDescription [] agents = null;
        double distance = -1;
        double distanceOfCourier = -1;
        AID matchedCourier = null;
        int courierCounter = 0;
        List<AMSAgentDescription> bikes = new ArrayList<AMSAgentDescription>();
        List<AMSAgentDescription> cars = new ArrayList<AMSAgentDescription>();
        List<AMSAgentDescription> trucks = new ArrayList<AMSAgentDescription>();
        List<AMSAgentDescription> allCouriers = new ArrayList<AMSAgentDescription>();

        ACLMessage msg = myAgent.receive();

        if (msg!=null) {

            SearchConstraints c = new SearchConstraints();
            c.setMaxResults(new Long(-1));
            try {
                agents = AMSService.search(getAgent(), new AMSAgentDescription(), c);
            } catch (FIPAException e) {
                e.printStackTrace();
            }



            //event for message from courier
            if (msg.getContent().substring(0,8).equals("Distance")){
                String sender = msg.getSender().getName().split("@")[0];

                int amount = Integer.parseInt(msg.getContent().split(":")[4]);


                for (int i=0; i<agents.length;i++)
                {
                    AID agentID = agents[i].getName();
                    String agentName = agents[i].getName().toString();
                    //Find who the courier is

                    if (agentName.contains("Bike") || agentName.contains("Car") || agentName.contains("Truck")){
                        AMSAgentDescription courier = agents[i];
                        allCouriers.add(courier);
                    }
                    if (agentName.contains("Bike")){
                        AMSAgentDescription bike = agents[i];
                        bikes.add(bike);
                    }
                    //Four wheeled small things
                    if (agentName.contains("Car")){
                        AMSAgentDescription car = agents[i];
                        cars.add(car);
                    }
                    //Four wheeled big things
                    if (agentName.contains("Truck")){
                        AMSAgentDescription truck = agents[i];
                        trucks.add(truck);
                    }
                }
                int alternatives;
                if(amount > 0 && amount <= 3){
                    alternatives = bikes.size()+cars.size();
                }
                else if(amount > 3 && amount <= 10){
                    alternatives = cars.size();
                }
                else{
                    alternatives = trucks.size();
                }


                double potentialMatch = Double.parseDouble(msg.getContent().split(":")[1]);
                if (potentialMatch < distanceOfCourier || distanceOfCourier == -1){
                    System.out.println(myAgent.getName() + ": Courier("+potentialMatch+") is better than current match(" + distanceOfCourier + ")");
                    distanceOfCourier = potentialMatch;
                    matchedCourier = msg.getSender();
                }
                courierCounter++;

                if (courierCounter == alternatives) {
                    ACLMessage replyToCourier = new ACLMessage(ACLMessage.INFORM);


                    double distanceFromDonatorToCenter = Double.parseDouble(msg.getContent().split(":")[2]);
                    String donatorName = msg.getContent().split(":")[3];

                    replyToCourier.setContent("OK," + distanceOfCourier + "," + distanceFromDonatorToCenter + "," + donatorName);
                    replyToCourier.addReceiver(matchedCourier);
                    myAgent.send(replyToCourier);
                    System.out.println(myAgent.getName() + ": Best transportation option is " + matchedCourier.getName().split("@")[0] + ".");



                    double totalDistance = Double.parseDouble(msg.getContent().split(":")[1]) + Double.parseDouble(msg.getContent().split(":")[2]);
                    String ETA;
                    if(matchedCourier.getName().split("@")[0].contains("Car")){
                        //5 for loading time
                        //avg car speed considered as 600 meter per minute
                        ETA = String.valueOf((int)(totalDistance / 600) + 5);
                    }
                    else if (matchedCourier.getName().split("@")[0].contains("Truck")){
                        //15 for loading time
                        //avg truck speed considered as 500 meter per minute
                        ETA = String.valueOf((int)(totalDistance / 500) + 15);
                    }
                    else{
                        //5 for loading time
                        //avg bike speed considered as 250 meter per minute
                        ETA = String.valueOf((int)(totalDistance / 250) + 5);
                    }

                    ACLMessage msgToDonator = new ACLMessage(ACLMessage.INFORM);
                    msgToDonator.setContent("OIW," + matchedCourier.getName().split("@")[0] + "," + ETA  + "," + _center.getName().split("@")[0]);

                }



            }



            //event for message from donator which approves the donation terms
            else if (msg.getContent().split(",")[0].equals("OK")){
                String sender = msg.getSender().getName().split("@")[0];

                for (int i=0; i<agents.length;i++)
                {
                    AID agentID = agents[i].getName();
                    String agentName = agents[i].getName().toString();
                    //Find the bikes!
                    if (agentName.contains("Bike")){
                        AMSAgentDescription courier = agents[i];
                        bikes.add(courier);
                    }
                    //Four wheeled small things
                    else if (agentName.contains("Car")){
                        AMSAgentDescription courier = agents[i];
                        cars.add(courier);
                    }
                    //Four wheeled big things
                    else if (agentName.contains("Truck")){
                        AMSAgentDescription courier = agents[i];
                        trucks.add(courier);
                    }
                }

                int donationAmount = Integer.parseInt(msg.getContent().split(",")[1]);
                int donatorX = Integer.parseInt(msg.getContent().split(",")[2]);
                int donatorY = Integer.parseInt(msg.getContent().split(",")[3]);
                String donatorName = msg.getContent().split(",")[4];


                ACLMessage msgToCouriers = new ACLMessage(ACLMessage.INFORM);
                msgToCouriers.setContent(donationAmount + "," + donatorX + "," + donatorY  + "," + _center.getX() + "," + _center.getY() + "," + donatorName);

                //who can pick that donation up?
                if(donationAmount > 0 && donationAmount <= 3){
                    //donation can be picked up by bike or car
                    for (int i = 0; i < bikes.size(); i++)
                        msgToCouriers.addReceiver( new AID( bikes.get(i).getName().getName().split("@")[0], AID.ISLOCALNAME) );
                    for (int i = 0; i < cars.size(); i++)
                        msgToCouriers.addReceiver( new AID( cars.get(i).getName().getName().split("@")[0], AID.ISLOCALNAME) );
                }
                else if(donationAmount > 3 && donationAmount <= 10){
                    //this donation requires a car
                    for (int i = 0; i < cars.size(); i++)
                        msgToCouriers.addReceiver( new AID( cars.get(i).getName().getName().split("@")[0], AID.ISLOCALNAME) );
                }
                else if(donationAmount > 10){
                    //wow this is big. Bring the big boys!
                    for (int i = 0; i < trucks.size(); i++)
                        msgToCouriers.addReceiver( new AID( trucks.get(i).getName().getName().split("@")[0], AID.ISLOCALNAME) );
                }



                myAgent.send(msgToCouriers);
                System.out.println(myAgent.getName() + ": Searching for a courier.");
            }


            else {

                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Donation donation = objectMapper.readValue(msg.getContent(), Donation.class);
                    String sender = msg.getSender().getName().split("@")[0];



                    for (int i = 0; i < agents.length; i++) {

                        if (agents[i].getName().getName().equals(msg.getSender().getName())) {
                            distance = Math.hypot(_center.getX() - donation.getDonator().getX(), _center.getY() - donation.getDonator().getY());
                        }
                    }

                    if (distance > -1) {
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.INFORM);
                        reply.setContent(String.valueOf(distance));
                        myAgent.send(reply);
                        System.out.println(_agent.getName() + ": My distance to you(" + sender + ") is: " + distance);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        block();
    }

    @Override
    public boolean done() {
        return false;
    }

    //constructor
    public FeedexBehaviour(Agent agent, FeedexCenter center) throws IOException {
        super(agent);
        this._center = center;
        this._agent = agent;

    }


}
