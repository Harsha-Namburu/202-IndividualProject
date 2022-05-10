import controller.CardController;
import controller.StockInventoryController;
import controller.InputController;

import java.io.IOException;

public class Billing {
    public static void main(String[] args) throws IOException {
        if(args.length==0){
            System.out.println("Please input Stock Inventory file path as first parameter.");
            System.exit(0);
        }
        if(args.length==1){
            System.out.println("Please input Card data file path as second parameter.");
            System.exit(0);
        }
        if(args.length==2){
            System.out.println("Please input Orders file path as third parameter.");
            System.exit(0);
        }
        StockInventoryController stockInventoryController = new StockInventoryController(args[0]);
        CardController cardController = new CardController(args[1]);
        processOrderFile(args[2]);
    }

    private static void processOrderFile(String path){
        InputController input = new InputController(path);
        if(input.processOrder()){
            if(input.checkOrder()){
                input.calculateTotal();
                if(input.getTotal()>0){
                    input.checkoutOrder();
                    System.out.println("The total amount for the order is $" + input.getTotal());
                }
            }else {
                System.out.println("Error log created.");
                input.generateOutputFile();
            }
        }else {
            System.out.println("Order file not found");
        }
    }
}
