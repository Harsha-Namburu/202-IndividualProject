package Database;

import model.Items;
import model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Database {

    private static Database dbInstance;

    private final HashMap<String,Items> stocksInventoryMap = new HashMap<>();

    private final HashSet<String> cardsMap = new HashSet<>();

    private final ArrayList<Order> orderItemsMap = new ArrayList<>();

    private Database() {}

    public static Database getInstance() {
        if( dbInstance == null){
            dbInstance = new Database();
        }
        return dbInstance;
    }

    public HashMap<String,Items> getStocksInventoryMap(){
        return stocksInventoryMap;
    }

    public HashSet<String> getCardsMap() {
        return cardsMap;
    }

    public ArrayList<Order> getOrderItemsMap() {
        return orderItemsMap;
    }


}
