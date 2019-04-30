package Models;

public class Donator extends FeedexObjectWithLocation {
    private FoodType content;
    private int donationAmount;

    public FoodType getContent() {
        return content;
    }

    public void setContent(FoodType content) {
        this.content = content;
    }

    public int getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(int donationAmount) {
        this.donationAmount = donationAmount;
    }


    protected Donator(FoodType content, int donationAmount) {
        this.content = content;
        this.donationAmount = donationAmount;
    }
}
