package Menu.Commands;

import Accounts.Account;
import Accounts.Knight;
import Accounts.Seller;
import Ammunition.AmmunitionItem;
import Menu.Command;
import Logger.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReturnItem implements Command {
    private List<Account> accounts;
    private Knight knight;
    private List<AmmunitionItem> ammunitionItemList;
    private static final Logger logger = Logger.getLogger(ReturnItem.class.getName());

    public ReturnItem(Knight knight, List<AmmunitionItem> ammunitionItemList, List<Account> accounts) {
        this.knight = knight;
        this.ammunitionItemList = ammunitionItemList;
        this.accounts = accounts;
    }

    public void execute() {
        Log.setupLogger(logger);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (int i = 0; i < knight.getAmmunitionItems().size(); i++) {
                System.out.println(i+1 + "." + knight.getAmmunitionItems().get(i));
            }
            System.out.println("Введіть номер елементу, який хочете повернути:");
            try {
                int key = scanner.nextInt();
                if (key >= 0 && key <= ammunitionItemList.size()) {
                    ReturnItemSeller(key);
                    break;
                } else
                    System.out.println("Введіть коректне значення");
            }catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Хибний ввід");
            }
        }
    }

    private void ReturnItemSeller(int key){
        int itemCost = knight.getAmmunitionItems().get(key-1).getCost()/2;
        for (Account account: accounts) {
            if (account instanceof Seller) {
                if (account.getWallet() >= itemCost) {

                    account.setWallet(account.getWallet() - itemCost);
                    knight.setWallet(knight.getWallet() + itemCost);

                    knight.getAmmunitionItems().get(key-1).setCost(itemCost);
                    ammunitionItemList.add(knight.getAmmunitionItems().get(key-1));
                    knight.deleteItem(key - 1);
                    logger.log(Level.INFO, "Knight return ammunition item");
                }
                else{
                    System.out.println("Повернути неможливо.");
                    logger.log(Level.INFO, "ammunition item cannot be returned");
                }
            }
        }
    }
}
