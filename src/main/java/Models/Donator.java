package Models;

public class Donator extends FeedexObjectWithLocation {

    public Donation donation;

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public Donator(String name) {
        super(name);

    }


    public Donator() {
        super();

    }
}
