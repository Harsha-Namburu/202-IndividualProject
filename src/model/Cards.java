package model;

public class Cards {

    private String cardNumber;

    public Cards(String card){
        this.cardNumber = card;
    }

    public String getCardInfo() {
        return cardNumber;
    }

    public void setCardInfo(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
