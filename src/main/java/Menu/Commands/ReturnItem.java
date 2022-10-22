package Menu.Commands;

import Accounts.Account;
import Accounts.Knight;
import Accounts.Seller;
import Ammunition.AmmunitionItem;
import Menu.Command;

import java.util.List;
import java.util.Scanner;

public class ReturnItem implements Command {
    private List<Account> accounts;
    private Knight knight;
    private List<AmmunitionItem> ammunitionItemList;

    public ReturnItem(Knight knight, List<AmmunitionItem> ammunitionItemList, List<Account> accounts) {
        this.knight = knight;
        this.ammunitionItemList = ammunitionItemList;
        this.accounts = accounts;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (int i = 0; i < knight.getAmmunitionItems().size(); i++) {
                System.out.println(i+1 + "." + knight.getAmmunitionItems().get(i));
            }
            System.out.println("Введіть номер елементу, який хочете повернути:");
            int key = scanner.nextInt();
            if(key >= 0 && key <= ammunitionItemList.size()) {
                ReturnItemSeller(key);
                break;
            }
            else
                System.out.println("Введіть коректне значення");
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
                }
                else{
                    System.out.println("Повернути неможливо.");
                }
            }
        }
    }
}
