package Models;

public class Donation {

    private FoodType content;
    private int amount;
    private Donator donator;

    public Donator getDonator() {
        return donator;
    }

    public void setDonator(Donator donator) {
        this.donator = donator;
    }

    public FoodType getContent() {
        return content;
    }
    public void setContent(FoodType content) {
        this.content = content;
    }

    public int getDonationAmount() {
        return amount;
    }

    public void setDonationAmount(int donationAmount) {
        this.amount = donationAmount;
    }

    public Donation() {
    }

    public Donation(FoodType content, int amount) {
        this.content = content;
        this.amount = amount;
    }
}
