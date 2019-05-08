package Models;

public class Donation {

    private FoodType content;
    private int amount;

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

    public Donation(FoodType content, int amount) {
        this.content = content;
        this.amount = amount;
    }
}
