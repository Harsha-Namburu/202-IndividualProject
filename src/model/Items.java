package model;

public class Items {
    private final String itemType;
    private final String itemName;
    private final double itemCost;
    private int itemCount;

    public Items(String itemType, String itemName, int itemCount, double itemCost){
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemCost = itemCost;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public String toString() {
        return "Items {" +
                "category: '" + itemType + '\'' +
                ", itemName: '" + itemName + '\'' +
                ", quantity: " + itemCount +
                ", price: " + itemCost +
                '}';
    }
}
