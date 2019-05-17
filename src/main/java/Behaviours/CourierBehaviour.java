package Behaviours;
import Models.CourierType;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

public class CourierBehaviour extends SimpleBehaviour{

    public CourierType _courier;
    @Override
    public void action() {

        ACLMessage msg = myAgent.receive();


        if (msg!=null) {
            if (msg.getContent().substring(0,2).equals("OK")){
                double totalDistance = Double.parseDouble(msg.getContent().split(",")[1]) + Double.parseDouble(msg.getContent().split(",")[2]);
                String ETA;
                if(_courier.getName().contains("Car")){
                    //5 for loading time
                    //avg car speed considered as 600 meter per minute
                    ETA = String.valueOf((int)(totalDistance / 600) + 5);
                }
                else if (_courier.getName().contains("Truck")){
                    //15 for loading time
                    //avg truck speed considered as 500 meter per minute
                    ETA = String.valueOf((int)(totalDistance / 500) + 15);
                }
                else{
                    //5 for loading time
                    //avg bike speed considered as 250 meter per minute
                    ETA = String.valueOf((int)(totalDistance / 250) + 5);
                }

                System.out.println(_courier.getName() + ": Im on my way. ETA: " + ETA);
            }
            else {
                int donationAmount = Integer.parseInt(msg.getContent().split(",")[0]);
                int donatorX = Integer.parseInt(msg.getContent().split(",")[1]);
                int donatorY = Integer.parseInt(msg.getContent().split(",")[2]);
                int centerX = Integer.parseInt(msg.getContent().split(",")[3]);
                int centerY = Integer.parseInt(msg.getContent().split(",")[4]);
                String donatorName = msg.getContent().split(",")[5];

                double distanceToDonator = Math.hypot(_courier.getX() - donatorX, _courier.getY() - donatorY);
                double distanceFromDonatorToCenter = Math.hypot(donatorX - centerX, donatorY - centerY);

                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.INFORM);
                reply.setContent("Distance:" + String.valueOf(distanceToDonator) + ":" + String.valueOf(distanceFromDonatorToCenter) + ":" + donatorName + ":" + donationAmount);
                myAgent.send(reply);
                System.out.println(myAgent.getName() + ": My distance to " + donatorName + " is: " + distanceToDonator + ", then " + distanceFromDonatorToCenter + " to center.");
            }
        }

    }

    @Override
    public boolean done() {
        return false;
    }

    //constructor
    public CourierBehaviour(Agent agent, CourierType courier) throws IOException {
        super(agent);
        this._courier = courier;
    }
}
