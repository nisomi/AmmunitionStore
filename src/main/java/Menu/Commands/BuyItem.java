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

public class BuyItem implements Command {
    private static final Logger logger = Logger.getLogger(BuyItem.class.getName());

    private List<Account> accounts;
    private Knight knight;
    private List<AmmunitionItem> ammunitionItemList;

    public BuyItem(Knight knight, List<AmmunitionItem> ammunitionItemList, List<Account> accounts) {
        this.knight = knight;
        this.ammunitionItemList = ammunitionItemList;
        this.accounts = accounts;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (int i = 0; i < ammunitionItemList.size(); i++) {
                System.out.println(i+1 + "." + ammunitionItemList.get(i));
            }
            System.out.println("Введіть номер елементу, який хочете купити:");
            try {
                int key = scanner.nextInt();
                if (key >= 0 && key <= ammunitionItemList.size()) {
                    PaySeller(key);
                    break;
                } else
                    System.out.println("Введіть коректне значення");
            }catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Хибний ввід");
            }
        }
    }

    private void PaySeller(int key){
        Log.setupLogger(logger);
        int itemCost = ammunitionItemList.get(key-1).getCost();

        for (Account account: accounts) {
            if (account instanceof Seller){
                if (knight.getWallet()>=itemCost) {
                    account.setWallet(account.getWallet() + itemCost);
                    knight.setWallet(knight.getWallet() - itemCost);

                    knight.addItem(ammunitionItemList.get(key - 1));

                    if (knight.checkIfAlreadySelected(ammunitionItemList.get(key - 1)))
                        logger.log(Level.INFO, "Knight bought ammunition item");
                    else
                        logger.log(Level.INFO, "Knight already bought this item");
                    ammunitionItemList.remove(key - 1);

                }
                else {
                    logger.log(Level.INFO, "Knight did not have enough money to buy ammunition item");
                    System.out.println("Недостатньо коштів.");
                }
            }
        }
    }
}
