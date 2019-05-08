package Models;

public class Request {

    private FoodType content;
    private int minAmount, maxAmount;

    public FoodType getContent() {
        return content;
    }

    public void setContent(FoodType content) {
        this.content = content;
    }

    public int getMinDonationAmount() {
        return minAmount;
    }

    public void setMinDonationAmount(int minDonationAmount) {
        this.minAmount = minDonationAmount;
    }

    public int getMaxDonationAmount() {
        return minAmount;
    }

    public void setMaxDonationAmount(int maxDonationAmount) {
        this.minAmount = maxDonationAmount;
    }

    public Request(FoodType content, int minAmount, int maxAmount) {
        this.content = content;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }
}
