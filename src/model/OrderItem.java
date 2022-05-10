package model;

public class OrderItem {
    private String orderName;
    private int orderCount;
    private String cardInfo;

    public OrderItem(String orderName, int orderCount, String cardInfo){
        this.orderName = orderName;
        this.orderCount = orderCount;
        this.cardInfo = cardInfo;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "name='" + orderName + '\'' +
                ", quantity=" + orderCount +
                ", cardDetails='" + cardInfo + '\'' +
                '}';
    }
}
