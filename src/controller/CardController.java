package controller;

import Database.Database;
import helper.FileHelper;

import java.io.IOException;
import java.util.HashSet;

public class CardController {
    private final Database db1 = Database.getInstance();

    public CardController(String path) throws IOException {
        FileHelper fileHandler = new FileHelper(path);
        HashSet<String> cardInfo = db1.getCardsMap();
        try {
            fileHandler.fileReader(true);
        } catch (Exception e){
            System.out.println("Not able to read Card data\n");
            System.exit(0);
        }
        cardInfo.addAll(fileHandler.getFile());
    }
}
