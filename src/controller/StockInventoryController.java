package controller;
import Database.Database;
import helper.FileHelper;
import model.Items;

import java.io.IOException;

public class StockInventoryController {
    private final Database dbInstance = Database.getInstance();
    public StockInventoryController(String path) throws IOException {
        FileHelper fileHandler = new FileHelper(path);
        try{
            fileHandler.fileReader(true);
        }catch (Exception e){
            System.out.println("Dataset not found\n");
            System.exit(0);
        }
        for (String s : fileHandler.getFile()) {
            String[] splitItem = s.split(",");
            dbInstance.getStocksInventoryMap().put(splitItem[1], new Items(splitItem[0], splitItem[1], Integer.parseInt(splitItem[2]), Double.parseDouble(splitItem[3])));
        }
    }
}
