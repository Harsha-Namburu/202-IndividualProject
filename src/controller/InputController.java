package controller;

import Database.Database;
import helper.FileHelper;
import model.Items;
import model.Order;
import model.OrderItem;
import validators.ItemCategoryCapValidation;
import validators.ItemPresenceValidation;
import validators.ItemStockValidation;
import validators.ValidationHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class InputController {
    private final Database dbInstance = Database.getInstance();
    private final Order presentOrder = new Order();
    private FileHelper fileHandler;
    private final ArrayList<String> output = new ArrayList<>();
    private final ArrayList<OrderItem> items = new ArrayList<>();
    private final HashSet<String> cardsMap = dbInstance.getCardsMap();
    private double total = 0;

    public InputController() {}

    public InputController(String filePath){
        fileHandler = new FileHelper(filePath);
    }

    public void addToOutput(String str){
        output.add(str);
    }

    public boolean processOrder() {
        try{
            fileHandler.fileReader(true);
        }catch (Exception e){
            return false;
        }
        getItems(fileHandler.getFile());
        return true;
    }
    public boolean checkOrder() {
        checkItemStock();
        return output.size()==0;
    }

    public void calculateTotal() {
        for(OrderItem item: items){
            total += item.getOrderCount()* dbInstance.getStocksInventoryMap().get(item.getOrderName()).getItemCost();
        }
        presentOrder.setTotalCost(total);
    }

    public double getTotal() {
    return presentOrder.getTotalCost();
    }

    public void checkoutOrder() {
        for(OrderItem orderItem: items){
            Items item = dbInstance.getStocksInventoryMap().get(orderItem.getOrderName());
            item.setItemCount(item.getItemCount()-orderItem.getOrderCount());
        }
        for(String credit: cardsMap){
            if(!dbInstance.getCardsMap().contains(credit)){
                dbInstance.getCardsMap().add(credit);
            }
        }
        generateOutputFile();
    }

    public void printMessage() {
        for(String line: output){
            System.out.println(line);
        }
    }

    public void getItems(ArrayList<String> fileContent){
        for(String line: fileContent){
            String[] item = line.split(",");
            if(dbInstance.getStocksInventoryMap().containsKey(item[0])){
                items.add(new OrderItem(item[0],Integer.parseInt(item[1]),item[2].replaceAll("\\s+","")));
            }else{
                output.add("Requested item " + item[0] + " does not exist in the stocks");
            }
        }
        if(!output.isEmpty()){
            items.clear();
        }
    }

    public void checkItemStock() {
        dbInstance.getOrderItemsMap().add(presentOrder);
        ValidationHandler itemPresence = new ItemPresenceValidation();
        ValidationHandler itemStock = new ItemStockValidation();
        ValidationHandler itemCategory = new ItemCategoryCapValidation();
        String itemPresenceValidation = itemPresence.validate(items);
        String itemQuantityValidation = itemStock.validate(items);
        String itemCategoryLimitValidation = itemCategory.validate(items);
        if(!itemPresenceValidation.isEmpty()){
            output.add("Error in itemPresenceValidation: ");
            output.add(itemCategoryLimitValidation);
        }
        if(!itemQuantityValidation.isEmpty()){
            output.add("Error in itemQuantityValidation: ");
            output.add(itemQuantityValidation);
        }
        if(!itemCategoryLimitValidation.isEmpty()){
            output.add("Error in itemCategoryLimitValidation: ");
            output.add(itemCategoryLimitValidation);
        }
        if(output.size()==0) {
            for (OrderItem orderItem : items) {
                if (!cardsMap.contains(orderItem.getCardInfo()))
                    cardsMap.add(orderItem.getCardInfo());
            }
        }
    }

    public void generateOutputFile(){
        //System.out.println("Zing Zing Amazing");
        if(output.size()==0){
            output.add("Amount Paid");
            output.add(Double.toString((presentOrder.getTotalCost())));
            try{
                fileHandler.writeOutput(output,false);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            try{
                fileHandler.writeOutput(output,true);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
