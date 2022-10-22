package Menu.Commands;

import Accounts.Account;
import Accounts.Knight;
import Menu.Command;

import java.util.List;
import java.util.Scanner;

public class TrowAwayItem implements Command {
    private Knight knight;;

    public TrowAwayItem(Knight knight, List<Account> accounts) {
        this.knight = knight;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (int i = 0; i < knight.getAmmunitionItems().size(); i++) {
                System.out.println(i+1 + "." + knight.getAmmunitionItems().get(i));
            }
            System.out.println("Введіть номер елементу амуніції, який хочете викинути:");
            int key = scanner.nextInt();
            if(key >= 0 && key <= knight.getAmmunitionItems().size()) {
                knight.deleteItem(key - 1);
                break;
            }
            else System.out.println("Введіть коректне значення");
        }
    }
}
