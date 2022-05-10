package validators;

import Database.Database;
import model.OrderItem;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemCategoryCapValidation implements ValidationHandler{
    private final int essentialMax = 40;
    private final int luxuryMax = 30;
    private final int miscMax = 60;
    @Override
    public String validate(ArrayList<OrderItem> items) {
        HashMap<String,Integer> itemsMap = new HashMap<>();
        Database database = Database.getInstance();
        for(OrderItem orderItem: items){
            itemsMap.put(database.getStocksInventoryMap().get(orderItem.getOrderName()).getItemType(),itemsMap.getOrDefault(database.getStocksInventoryMap().get(orderItem.getOrderName()).getItemType(),0)+orderItem.getOrderCount());
        }
        if(itemsMap.getOrDefault("Luxury",0)> luxuryMax){
            return String.format("Desired quantity %d for the category %s exceeds the maximum of %d items.", itemsMap.getOrDefault("Luxury",0), "Luxury", luxuryMax);
        }else if(itemsMap.getOrDefault("Essential",0)> essentialMax){
            return String.format("Desired quantity %d for the category %s exceeds the maximum of %d items.", itemsMap.getOrDefault("Essential",0), "Essential", essentialMax);
        }else if(itemsMap.getOrDefault("Misc",0)> miscMax){
            return String.format("Desired quantity %d for the category %s exceeds the maximum of %d items.", itemsMap.getOrDefault("Misc",0), "Misc", miscMax);
        }
        return "";
    }
}
