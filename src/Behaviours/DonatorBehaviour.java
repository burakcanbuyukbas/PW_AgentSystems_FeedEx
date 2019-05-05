package Behaviours;
import Models.Donation;
import Models.DonatorAgent;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.*;

import java.io.IOException;


public class DonatorBehaviour extends SimpleBehaviour {

    @Override
    public void action() {


    }

    @Override
    public boolean done() {
        return false;
    }
    //constructor
    public DonatorBehaviour(Agent agent, Donation donation) throws IOException {
        super(agent);

    }
}
