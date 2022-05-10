package validators;

import Database.Database;
import model.OrderItem;

import java.util.ArrayList;

public class ItemStockValidation implements ValidationHandler{
    @Override
    public String validate(ArrayList<OrderItem> items) {
        Database database = Database.getInstance();
        for(OrderItem orderItem: items){
            if(database.getStocksInventoryMap().get(orderItem.getOrderName()).getItemCount()<orderItem.getOrderCount()){
                return String.format("Desired quantity %d of %s is not in stock, available stock quantity: %d items", orderItem.getOrderCount(), orderItem.getOrderName(), database.getStocksInventoryMap().get(orderItem.getOrderName()).getItemCount());
            }
        }
        return "";
    }
}
