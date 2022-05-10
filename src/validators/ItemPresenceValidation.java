package validators;

import Database.Database;
import model.OrderItem;

import java.util.ArrayList;

public class ItemPresenceValidation implements ValidationHandler{
    @Override
    public String validate(ArrayList<OrderItem> items) {
        Database database = Database.getInstance();
        for(OrderItem orderItem: items){
            if(!database.getStocksInventoryMap().containsKey(orderItem.getOrderName())){
                return String.format("Desired item %s is not in stock", orderItem.getOrderName());
            }
        }
        return "";
    }
}
